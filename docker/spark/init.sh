#!/bin/sh

/opt/spark-3.3.0/sbin/start-master.sh 2>&1 &

# workaround to keep it alive
/bin/sleep infinity
