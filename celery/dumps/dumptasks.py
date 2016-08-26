import os
import time
from subprocess import Popen, PIPE


def do_dumptask(scriptpath, wiki, configfile, taskname, options, flags):
    '''
    run commands like:
       python ./worker.py --configfile /etc/dumps/confs/wikidump.conf.bigwikis \
                          --log --job articlesmultistreamdump --skipdone \
                          --exclusive --prereqs --date 20160820 wikiname

    scriptpath: path to directory containing the worker.py script
    wiki: name of the wiki on which to run
    taskname: name of the dump step
    configfile: path to configuration file for worker.py
    options: dict of options vs values, options do not have the leading '--'
    flags: list of options which take no values, without the leading '--'
    '''
    print "sleeping 10 seconds first"
    time.sleep(10)
#    command = ["echo", "/usr/bin/python", os.path.join(scriptpath, "worker.py"), "--configfile", configfile, "--job", taskname]
    command = ["/usr/bin/python", os.path.join(scriptpath, "worker.py"), "--configfile", configfile, "--job", taskname]
    print "doing command", command
    for option, value in options.items():
        command.extend(['--' + option, value])
    command.extend(['--' + flag for flag in flags])
    command.append(wiki)
    proc = Popen(command)
    proc.wait()
    retval = proc.returncode
    randomstuff = str(time.time())
    if randomstuff[-1] in ["2", "4", "5", "7"]:
        print "going to fail out on this run"
        raise ValueError
    else:
        return retval
