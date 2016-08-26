from __future__ import absolute_import
import sys

from celery import Celery
from celery.utils.log import get_task_logger
from celery.signals import setup_logging
from dumps.dumptasks import do_dumptask
import dumps.logging

logger = get_task_logger(__name__)

app = Celery('dumps.dumptasks')
app.config_from_object('celerydumpconfig')

@app.task(bind=True)
def dumptask(self, scriptpath, wiki, configfile, taskname, options, flags):
    old_outs = sys.stdout, sys.stderr
    rlevel = self.app.conf.CELERY_REDIRECT_STDOUTS_LEVEL

    retval = None
    try:
        self.app.log.redirect_stdouts_to_logger(logger, rlevel)
        retval = do_dumptask(scriptpath, wiki, configfile, taskname, options, flags)
    except Exception as e:
        self.retry(countdown=2, exc=e)
    finally:
        pass
        sys.stdout, sys.stderr = old_outs
    return retval

if __name__ == '__main__':
    app.start()
