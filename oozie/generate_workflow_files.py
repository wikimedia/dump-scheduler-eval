#!/usr/bin/python
'''
write a workflow.xml file for oozie from the workflow.xml.tmpl file
in the specified path.  this file will include commands to copy all
dumps-related library scripts over to hdfs.

note that since this is only for testing purposes, it will cheerfully
overwrite any workflow files some other process has written. Too bad.
'''

import os
import sys
import time
import getopt


def generate_workflow_xml(opts, verbose):
    '''
    from the options passed in, and the workflow.xml template,
    produce a workflow.xml file for the corresponding oozie dump job
    '''
#    libpath = os.path.join(opts['dump_scriptpath'], "dumps")
    libpath = "dumps"
#    files = [os.path.join(opts['dump_scriptpath'], filename)
    files = [filename
             for filename in os.listdir(opts['dump_scriptpath'])
             if filename.endswith(".py")]
    files.extend([os.path.join(libpath, filename)
                  for filename in os.listdir(
                      os.path.join(opts['dump_scriptpath'], libpath))
                  if filename.endswith(".py")])
    files_text = '\n'.join(['<file>' + os.path.join(
        opts['dump_scriptpath'], filename) +
                            '#' + filename + '</file>' for filename in files])
    if verbose:
        print "files to include are", files
    workflow_xml = os.path.join(opts['ooziedir'], "workflow.xml")
    workflow_tmpl = workflow_xml + ".tmpl"
    contents = open(workflow_tmpl, "r").read()
    workflow_text = contents % {"files": files_text,
                                "dump_date": opts['dump_date'],
                                "dump_wiki": opts['dump_wiki'],
                                "dump_job": opts['dump_job']}
    if verbose:
        print "workflow_text to write:", workflow_text
    workflow_out = open(workflow_xml, "w")
    workflow_out.write(workflow_text)
    workflow_out.close()
    if verbose:
        print "done"


def merge_opts(args):
    '''
    return a dict of option names and values, with default
    values filled in where there were no values given in the
    passed in args
    '''
    opts_to_return = {}
    defaults = get_arg_defaults()
    # start with a copy of the defaults
    for key in defaults:
        opts_to_return[key] = defaults[key]
    # overwrite with the passed in opts
    for key in args:
        opts_to_return[key] = args[key]
    return opts_to_return


def generate_job_properties(opts, verbose):
    '''
    from the options passed in, and the job.properties template,
    produce a job.properties file for the corresponding oozie dump job
    '''
    job_properties = os.path.join(opts['ooziedir'], "job.properties")
    properties_tmpl = job_properties + ".tmpl"
    contents = open(properties_tmpl, "r").read()

    properties_text = contents % {"dump_scriptpath": opts['dump_scriptpath'],
                                  "dump_configfile": opts['dump_configfile'],
                                  "dump_date": opts['dump_date'],
                                  "dump_wiki": opts['dump_wiki'],
                                  "dump_job": opts['dump_job']}
    if verbose:
        print "properties_text to write:", properties_text
    properties_out = open(job_properties, "w")
    properties_out.write(properties_text)
    properties_out.close()
    if verbose:
        print "done"


def get_arg_defaults():
    '''
    return a dict of all dump job property defaults
    '''
    today = time.strftime("%Y%m%d", time.gmtime())
    basedir = '/home/ariel/wmf/dumps/testing/xmldumps'
    return ({
        'ooziedir': '/var/lib/oozie/dumptest',
        'dump_scriptpath': basedir,
        'dump_configfile': os.path.join(basedir, 'confs',
                                        'wikidump.conf.multi'),
        'dump_date': today,
        'dump_job': 'pagetitlesdump',
        'dump_wiki': 'elwikt'
    })


def get_options():
    '''
    retrieve options given on the command line and
    return a dict with them filled in
    '''
    args = {}
    try:
        (options, remainder) = getopt.gnu_getopt(
            sys.argv[1:], "w:d:c:s:o:j:h",
            ["wiki=", "date=", "config=",
             "job=", "scriptpath=",
             "ooziedir=", "help"])

    except getopt.GetoptError as err:
        usage("Unknown option specified: " + str(err))
    for (opt, val) in options:
        if opt in ["-w", "--wiki"]:
            args['dump_wiki'] = val
        elif opt in ["-d", "--date"]:
            args['dump_date'] = val
        elif opt in ["-c", "--config"]:
            args['dump_configfile'] = val
        elif opt in ["-j", "--job"]:
            args['job'] = val
        elif opt in ["-s", "scriptpath"]:
            args['dump_scriptpath'] = val
        elif opt in ["-o", "--ooziedir"]:
            args['ooziedir'] = val
        elif opt in ["-h", "--help"]:
            usage('Help for this script\n')
        else:
            usage("Unknown option specified: <%s>" % opt)
    if len(remainder) > 0:
        usage("Unknown option(s) specified: <%s>" % remainder[0])
    return merge_opts(args)


def usage(message=None):
    '''
    print whining instructions on how to use this script, with
    an optional message first
    '''
    if message:
        sys.stderr.write(message + "\n")
    usage_message = """
Usage: python generate_workflow_files.py [--config path] [--date YYYYMMDD]
           [--job string] [--scriptpath path] [--wiki wikiname]
           [--ooziedir path]

Arguments:

--config      (-c): path to dumps config file
--date        (-d): date for dump run job, format YYYYMMDD
--job         (-j): name of job for dump run
--scriptpath  (-s): path to directory with worker.py
--wiki        (-w): name of wiki for which to run dump job
--ooziedir    (-o): oozie directory, I forget what this is :-)
"""
    sys.stderr.write(usage_message)
    sys.exit(1)


def do_main(verbose=False):
    '''
    main entry point, does all the work
    '''
    opts = get_options()
    #    generate_workflow_xml(opts, verbose)
    generate_job_properties(opts, verbose)


if __name__ == "__main__":
    do_main(verbose=True)
