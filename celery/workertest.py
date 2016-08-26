#!/usr/bin/python
import time
from dumps.celery import dumptask


def do_one_run(job):
    result = dumptask.delay("/home/ariel/wmf/dumps/testing/xmldumps", "wikidatawiki",
                            "/home/ariel/wmf/dumps/testing/xmldumps/confs/wikidump.conf.multi",
                            job, {'date': '20160811'},
                            ['skipdone', 'prereqs', 'exclusive'])
    print "and result is", result, "in state", result.state
    while not result.ready():
        print "waiting 1 minute, in state", result.state
        time.sleep(60)
    print "done with " + job, "result is", result.info

    
def do_main():
    for job in ["xmlstubsdump", "usertable", "articlesdump"]:
        do_one_run(job)


if __name__ == "__main__":
    do_main()

