#!/bin/bash

# ====== scala api =======

cd /data/scala_api

# build
sbt assembly

# set in jars
mv /data/scala_api/target/scala*/scala-api-1.0.jar /data/jar/.

