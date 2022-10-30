#!/bin/sh

export SPARK_NO_DAEMONIZE="true"
/opt/spark-3.3.0/sbin/start-master.sh 2>&1
