#!/usr/bin/python
import time
from dumps.celery import dumptask


def do_one_run(name):
    result = dumptask.delay("somewhere_" + name, "elwiki",
                            "configs/wikidump.conf.big",
                            "xmlstubs", {'date': '20160509'},
                            ['skipdone', 'prereqs'])
    print "and result is", result, "in state", result.state
    while not result.ready():
        print "waiting 5 seconds, in state", result.state
        time.sleep(5)
    print "done with " + name, "result is", result.info

    
def do_main():
    for name in ["one", "two", "three", "four", "five"]:
        do_one_run(name)


if __name__ == "__main__":
    do_main()

