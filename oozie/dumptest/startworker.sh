#!/bin/bash

# run worker.py:
# cd to directory where it lives
# pass in relevant args from command line, if any

echo "ha ha ha" > /tmp/worker.py.out.3
echo $@ >> /tmp/worker.py.out.3

while [ $# -gt 0 ]; do
    if [ $1 == "--configfile" ]; then
        configfile="$2"
        shift; shift
    elif [ $1 == "--scriptpath" ]; then
        scriptpath="$2"
        shift; shift
    elif [ $1 == "--job" ]; then
        job="$2"
        shift; shift
    elif [ $1 == "--date" ]; then
        date="$2"
        shift; shift
    elif [ $1 == "--wikiname" ]; then
        wikiname="$2"
        shift; shift
    else
	exit 1
    fi
done

cd ${scriptpath}
echo python ${scriptpath}/worker.py --configfile ${scriptpath}/confs/$configfile --job $job --date $date $wikiname --log >> /tmp/worker.py.out.3
python ${scriptpath}/worker.py --configfile ${scriptpath}/confs/$configfile --job $job --date $date $wikiname --log >> /tmp/worker.py.out.3
echo "done" >> /tmp/worker.py.out.3
