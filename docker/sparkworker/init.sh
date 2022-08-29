#!/bin/sh

#/opt/spark-3.3.0/sbin/start-master.sh 2>&1 &
spark-class org.apache.spark.deploy.worker.Worker spark://sparkmaster:7077 2>&1 &
