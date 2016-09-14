import os
import sys
from subprocess import Popen, PIPE
import luigi

class DumpTest(luigi.Task):
    basedir = '/home/ariel/wmf/dumps/testing/xmldumps/dumpruns/public'
    wikiname = luigi.Parameter()
    date = luigi.Parameter()
    configfile = luigi.Parameter(default="wikidumps.conf")
    scriptpath = luigi.Parameter(default="/home/ariel/wmf/dumps/testing/xmldumps")
    taskname = luigi.Parameter()

    @property
    def retry_count(self):
        return 2

    def output(self):
        # fixme this is for one stub file only, we should check that they
        # all exist. ah well
        return luigi.LocalTarget(os.path.join(
            DumpTest.basedir, self.wikiname, self.date,
            '%s-%s-stub-articles1.xml.gz' %(self.wikiname, self.date)))

    def run(self):
        command = ["/usr/bin/python", os.path.join(self.scriptpath, "worker.py"),
                   "--configfile", self.configfile, "--job", self.taskname,
                   "--date", self.date, self.wikiname]
        # NOTE: we must capture output from the command ourselves and
        # print or log it, for all tasks
        print "doing command", command
        env_to_use = os.environ.copy()
        pythonpath = sys.path[:]
        pythonpath.append(self.scriptpath)
        os.chdir(self.scriptpath)
        proc = Popen(command, stdout=PIPE, env=env_to_use)
        output = proc.communicate()
        if output:
            print "here is some output", output
        retval = proc.returncode
        print "and the retval btw is..", retval
        if retval:
            raise Exception("bummer. this task failed")
