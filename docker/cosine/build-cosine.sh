#!/bin/bash

faketty() {
	script -qfc "$(printf "%q " "$@")" /dev/null
}

# ====== cosine similarity =======

cd /data/cosine

# build
faketty sbt assembly --verbose -java-home /opt/jdk-11.0.2/

# set in jars
mv /data/cosine/target/scala*/cosine-similarity-job-1.0.jar /data/jar/.

