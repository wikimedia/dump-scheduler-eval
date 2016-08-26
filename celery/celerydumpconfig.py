BROKER_URL = 'amqp://dumpuser:somepasswd@localhost:5672//dumpvhost'
CELERY_MAX_CACHED_RESULTS = 2000
CELERY_ENABLE_UTC = True
CELERY_TASK_SERIALIZER = 'json'
CELERY_RESULT_SERIALIZER = 'json'
CELERY_RESULT_BACKEND =	'redis://localhost:6379/0'
BROKER_TRANSPORT_OPTIONS = {'visibility_timeout': 600} # 5 minutes
CELERY_ACCEPT_CONTENT = ['json', 'msgpack', 'yaml']
CELERY_REDIRECT_STDOUTS_LEVEL = 'INFO'
# CELERY_REDIRECT_STDOUTS = False
# CELERYD_LOG_FILE = "/home/ariel/wmf/dumps/dumps2.0/schedulers/mylog.txt"

