#!/bin/bash

faketty() {
	script -qfc "$(printf "%q " "$@")" /dev/null
}

# ====== scala api =======

cd /data/scala_api

# build
faketty sbt assembly --verbose

# set in jars
mv /data/scala_api/target/scala*/scala-api-1.0.jar /data/jar/.

