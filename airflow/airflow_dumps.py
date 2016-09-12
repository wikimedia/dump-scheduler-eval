import os
from datetime import datetime, timedelta
from airflow import DAG
from airflow.operators.bash_operator import BashOperator

default_args = {
    'owner': 'ariel',
    'depends_on_past': False,
    'start_date': datetime(2016, 9, 1),
    'email': ['ariel@localhost'],
    'email_on_failure': False,
    'email_on_retry': False,
    'retries': 1,
    'retry_delay': timedelta(minutes=5),
    # 'queue': 'bash_queue',
    # 'pool': 'backfill',
    # 'priority_weight': 10,
    # 'end_date': datetime(2016, 1, 1),
}

dag = DAG(
    'dumpstest', default_args=default_args, schedule_interval=timedelta(1))

worker_templated = "cd {{ params.basedir }}; /usr/bin/python {{ params.workerscript }} --configfile {{ params.configfile }} --job {{ params.taskname }} {{ params.wiki }}"

scriptsdir = '/home/ariel/wmf/dumps/testing/xmldumps'

t1 = BashOperator(
    task_id='run_worker',
    bash_command=worker_templated,
    params={'basedir': scriptsdir,
            'workerscript': os.path.join(scriptsdir, 'worker.py'),
            'configfile': os.path.join(scriptsdir, 'confs/wikidump.conf.multi'),
            'taskname': 'xmlstubsdump',
            'wiki': 'tenwiki'},
    dag=dag)
