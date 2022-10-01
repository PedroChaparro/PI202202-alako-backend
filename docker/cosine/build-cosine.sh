#!/bin/bash

faketty() {
	script -qfc "$(printf "%q " "$@")" /dev/null
}

# ====== cosine similarity =======

cd /data/cosine

ls -l
sbt --version

# build
faketty sbt assembly --verbose

# set in jars
mv /data/cosine/target/scala*/cosine-similarity-job-1.0.jar /data/jar/.

