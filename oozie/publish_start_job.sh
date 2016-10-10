#!/bin/bash

# create config file for job, publish it,
# make sure the xml file for the job itself is published
# as well as the python script (worker.py, eww does
# that mean all the subsidiary scripts need to be there
# too? probably, yuck)
# and then run it

# setup defaults
configfile=wikidump.conf.multi
scriptpath=/home/ariel/wmf/dumps/testing/xmldumps
taskname=pagetitlesdump
date=20161010
wikiname=elwikt
ooziedir=/var/lib/oozie/dumptest

while [ $# -gt 0 ]; do
    if [ $1 == "--config" ]; then
        configfile="$2"
        shift; shift
    elif [ $1 == "--scriptpath" ]; then
        scriptpath="$2"
        shift; shift
    elif [ $1 == "--taskname" ]; then
        taskname="$2"
        shift; shift
    elif [ $1 == "--date" ]; then
        date="$2"
        shift; shift
    elif [ $1 == "--wikiname" ]; then
        wikiname="$2"
        shift; shift
    elif [ $1 == "--ooziedir" ]; then
        ooziedir="$2"
        shift; shift
    else
        echo "$0: Unknown option $1"
        usage
    fi
done

python generate_workflow_files.py -c $configfile -s $scriptpath -j $taskname -d $date -w $wikiname -o $ooziedir
if [ $? ]; then
    hdfs dfs -mkdir dumptest
    hdfs dfs -rm dumptest/job.properties
    hdfs dfs -rm dumptest/workflow.xml
    hdfs dfs -rm dumptest/startworker.sh

    hdfs dfs -put dumptest/job.properties dumptest/job.properties
    hdfs dfs -put dumptest/workflow.xml dumptest/workflow.xml
    hdfs dfs -put dumptest/startworker.sh dumptest/startworker.sh
    oozie job --oozie http://localhost:11000/oozie --config dumptest/job.properties -run
else
    echo "Failed to generate workflow files, not running job"
    exit 1
fi
