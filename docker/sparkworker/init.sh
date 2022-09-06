#!/bin/sh

/opt/spark-3.3.0/bin/spark-class org.apache.spark.deploy.worker.Worker spark://sparkmaster:7077 2>&1 &

# workaround to keep it alive
/bin/sleep infinity
