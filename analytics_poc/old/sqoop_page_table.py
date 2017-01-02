#!/usr/bin/env python
# -*- coding: utf-8 -*-

"""
Sqoops a list of tables from a list of wikis into a target HDFS location

Usage:
  sqoop-mediawiki-tables --jdbc-host HOST --output-dir HDFS_PATH
          [--verbose] --wiki-file WIKIS
          [--mappers NUM] --user NAME --password-file FILE
          [--job-name HADOOP_JOB_NAME]

Options:
    -h --help                           Show this help message and exit.
    -v --verbose                        Turn on verbose debug logging.

    -H HOST --jdbc-host HOST            Domain name of the mysql db
    -d HDFS_PATH --output-dir HDFS_PATH Target hdfs directory to write to

    -w FILE --wiki-file FILE            File with list of wiki dbs to sqoop
                                          A csv file of the form:

                                          dbname,parallel-group,...

                                          where all wiki dbs that will be
                                          sqooped in parallel with this one
                                          share the same parallel-group

    -u NAME --user NAME                 mysql user to use
    -p FILE --password-file FILE        File with mysql password to use

    -m NUM --mappers NUM                The number of mappers to use to sqoop
                                        [optional] default is 1
    -j JOB_NAME --job-name JOB_NAME     The yarn job name, only one job of a
                                        certain name can run at a time.
                                        [optional] default is
                                        sqoop-mediawiki-tables
"""
import csv
import logging
import sys
import os
import MySQLdb

from itertools import groupby
from subprocess import check_call
from traceback import format_exc

from docopt import docopt

from concurrent import futures


__author__ = 'Dan Andreesu <milimetric@wikimedia.org>'
# shamelessly stolen and hacked into pieces by atglenn

# Licensed under the Apache License, Version 2.0 (the "License");
# you may not use this file except in compliance with the License.
# You may obtain a copy of the License at
#
# http://www.apache.org/licenses/LICENSE-2.0
#
# Unless required by applicable law or agreed to in writing, software
# distributed under the License is distributed on an "AS IS" BASIS,
# WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
# See the License for the specific language governing permissions and
# limitations under the License.

# Note: needs python3 to run unless we backport concurrent and urllib.parse
# NOT any more I think, there is
# http://packages.ubuntu.com/trusty/python/python-concurrent.futures
# for trusty...
#
#   python3 sqoop_page_table \
#     --jdbc-host analytics-store.eqiad.wmnet \
#     --output-dir testing \
#     --wiki-file \
# "/home/ariel/testing/grouped_wikis.csv" \
#     --user research \

# FIXME we likely want an abs path. anyways...
#     --password-file /user/hdfs/mysql-analytics-research-client-pw.txt

# NOTE: labsdb excludes fields according to:
# https://github.com/wikimedia/operations-software-redactatron/blob/master/scripts/cols.txt
# But it's more complicated, rows are also redacted,
# some reading and background:
# https://wikitech.wikimedia.org/wiki/MariaDB/Sanitarium_and_Labsdbs
# https://phabricator.wikimedia.org/T103011#2296587
# https://phabricator.wikimedia.org/T138450
# TODO: follow up with labs and DBAs to figure out how to redact this load


# stolen from analytics refinery so we don't import all that
def is_yarn_application_running(job_name):
    '''
    Returns true if job_name is found in the output
    of yarn application -list and has a status of
    RUNNING or ACCEPTED.  Returns false otherwise.

    Note:  Error checking on the yarn shell command is not good.
    This command will return false on any command failure.
    '''
    command = r'/usr/bin/yarn application -list 2>/dev/null | ' + \
        r'grep -q  "\({0}\).*\(RUNNING\|ACCEPTED\)"'.format(job_name)
    logging.debug('Running: %s', command)
    retval = os.system(command)
    return retval == 0


def get_password(passwordfile):
    """
    retrieve password from a file; the
    file should have one line with the password and nothing else.
    tbh another part of this script needs this same file and it has
    to be without a traling newline, though this method here
    doesn't care
    """
    # FIXME should do this in a way that guarantees a close of fd
    try:
        contents = open(passwordfile, "r").read().splitlines()
        if len(contents) > 1:
            logging.error('ERROR, What kind of garbage'
                          'is in the password file?')
            return ""
        return contents[0].strip()
    except:
        logging.error('ERROR, %s, %s', passwordfile, format_exc())


def get_field_info(tablename, host, user, password, dbname):
    """
    given a tablename and a wiki database name, do
    "SHOW FIELDS FROM" tablename and return the results
    """
    try:
        connection = MySQLdb.connect(host=host, user=user,
                                     passwd=password, db=dbname)
        cursor = connection.cursor()
        cursor.execute('show fields from ' + tablename)
        results = cursor.fetchall()
        field_info = [(field_name, field_type)
                      for (field_name, field_type, _, _, _, _) in results]
        return field_info
    except:
        logging.error('ERROR, %s, %s', dbname, format_exc())
        return None


def is_numeric_type(ftype):
    """
    given a field type from mysql, return True if
    it is a numeric type as defined by mysql/mariadb,
    otherwise False
    """
    ftype = ftype.lower()
    ftype = ftype.split()[0].split('(')[0]
    return ftype in ['int', 'tinyint', 'smallint',
                     'mediumint', 'bigint',
                     'bit', 'boolean', 'decimal',
                     'float', 'double', 'real']


def get_string_fields(field_info):
    """
    given output from a SHOW FIELDS FROM <table> query,
    return a list of the fields that are string data types
    as described at https://mariadb.com/kb/en/mariadb/data-types/
    by excluding all numeric fields, that list is shorter
    """
    string_fields = [fname for (fname, ftype) in field_info
                     if not is_numeric_type(ftype)]
    return string_fields


def populate_queries(host, user, password, flat_wikis):
    """
    return one query per table, for the given db,
    to dump all fields

    NOTES on queries:
      convert(... using utf8) is used to decode varbinary fields into strings
      type mapping is used to handle some databases having booleans in
      tinyint(1) and others in tinyint(3,4) (newer databases like wikivoyage)
    """
    queries = {}
    for dbname in flat_wikis:

        queries[dbname] = {}
        field_info = get_field_info('page', host, user, password, dbname)
        string_fields = get_string_fields(field_info)
        field_retrieval_text = ','.join([
            'convert({0} using utf8)'.format(fieldname)
            if fieldname in string_fields else fieldname
            for (fieldname, _) in field_info])
        print "retrieval text is", field_retrieval_text
        queries[dbname]['page'] = {
            'query': 'select {0} from page where $CONDITIONS'.format(
                field_retrieval_text),
            'map-types': '"{}"'.format(','.join([
                'page_is_redirect=Boolean',
                'page_is_new=Boolean',
            ])),
        }
    return queries


def sqoop_wiki_page_table(args):
    """
    TODO: pass global values in a config object
    Imports a pre-determined list of tables from dbname

    Parameters
        dbname: Name of a mediawiki database from which to import data

    Returns
        True if the sqoop worked
        False if the sqoop errored or failed in any way
    """
    # dbname, target_hdfs_directory, yarn_job_name,
    # user, password_file, jdbc_host, num_mappers, queries):
    logging.info('STARTING: %s', args['dbname'])
    queries = args['queries']
    try:
        for table in queries.keys():
            target_directory = '{hdfs}/{table}/wiki_db={db}'.format(
                hdfs=args['target_hdfs_directory'],
                table=table,
                db=args['dbname']
            )
            sqoop_arguments = [
                'sqoop',
                'import',
                '-D', "mapred.job.name='{}'".format(args['yarn_job_name']),
                '--username', args['user'],
                '--password-file', 'file://' + args['password_file'],
                '--connect',
                'jdbc:mysql://' + args['jdbc_host'] + '/' + args['dbname'],
                # NOTE: using columns/map/where doesn't let you
                # properly decode varbinary
                # '--table', table,
                # '--columns', queries[table].get('columns') or '',
                # '--where', queries[table].get('where') or '1=1',
                '--query', queries[table].get('query'),
                '--num-mappers', args['num_mappers'],
                '--target-dir', target_directory,
                '--as-avrodatafile',
            ]

            if 'map-types' in queries[table]:
                sqoop_arguments += ['--map-column-java',
                                    queries[table]['map-types']]

            logging.info('Sqooping with: %s', sqoop_arguments)
            check_call(sqoop_arguments)
        return True
    except:
        logging.error('ERROR, %s, %s', args['dbname'], format_exc())
        return False
    finally:
        logging.info('FINISHED: %s', args['dbname'])


def merge_dicts(*dicts):
    '''
    merge any number of dicts into a new one;
    assume later args take precedence over earlier ones
    and that values are not themselves complex objects
    '''
    merged = {}
    for item in dicts:
        merged.update(item)
    return merged


def get_cli_args():
    """
    get and return command line args
    """
    arguments = docopt(__doc__)
    sqargs = {}
    verbose = arguments.get('--verbose')
    sqargs['yarn_job_name'] = arguments.get('--job-name')

    sqargs['jdbc_host'] = arguments.get('--jdbc-host')
    sqargs['target_hdfs_directory'] = arguments.get('--output-dir')
    sqargs['db_list_file'] = arguments.get('--wiki-file')
    sqargs['user'] = arguments.get('--user')
    sqargs['password_file'] = arguments.get('--password-file')
    sqargs['num_mappers'] = arguments.get('--mappers') or '1'
    sqargs['yarn_job_name'] = sqargs['yarn_job_name'] or 'sqoop-mediawiki-tables'
    return sqargs, verbose, arguments


def do_main():
    """
    main entry point

    get args, generate queries to grab all fields from
    tables on specified wikis, run queries via sqoop
    """
    sqoop_args, verbose, arguments = get_cli_args()

    if is_yarn_application_running(sqoop_args['yarn_job_name']):
        logging.warn('%s is already running, exiting.', sqoop_args['yarn_job_name'])
        sys.exit(1)

    logging.basicConfig(level=logging.DEBUG if verbose else logging.INFO,
                        format='%(asctime)s %(levelname)-6s %(message)s',
                        datefmt='%Y-%m-%dT%H:%M:%S')

    logging.info('Started Shell with with %s', ' '.join(arguments))

    # read in the wikis to process and sqoop each one
    content = open(sqoop_args['db_list_file'], "r").read().splitlines()
    wikis = [line.split(',')[1] for line in content]
    queries = populate_queries(sqoop_args['jdbc_host'],
                               sqoop_args['user'],
                               get_password(sqoop_args['password_file']),
                               wikis)

    with open(sqoop_args['db_list_file']) as dbs_file:

        flat_wikis = csv.reader(dbs_file)
        groups_done = []
        for dummy_group, wikis in groupby(flat_wikis, lambda w: w[1]):
            # sqoop all wikis in this group, wait for them all to finish
            with futures.ProcessPoolExecutor() as executor:
                successes = executor.map(
                    sqoop_wiki_page_table,
                    [merge_dicts(
                        sqoop_args,
                        {'dbname': w[1], 'queries': queries[w[1]]})
                     for w in wikis])
                groups_done.append(all(successes) and any(successes))

        # if there were no failures at all, write a success flag
        # to this dataset
        if all(groups_done) and any(groups_done):
            check_call([
                'hdfs', 'dfs', '-touchz',
                sqoop_args['target_hdfs_directory'] + '/_SUCCESS',
            ])


if __name__ == '__main__':
    do_main()
