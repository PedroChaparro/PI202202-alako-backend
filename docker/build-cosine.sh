#!/bin/bash

# ====== scala api =======

cd /data/cosine

# build
sbt assembly

# set in jars
mv /data/scala_api/target/scala*/cosine-similarity-job-1.0.jar /data/jar/.

