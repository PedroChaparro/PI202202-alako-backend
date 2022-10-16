#!/bin/sh
# setup master

# build containers
docker build -f ./hadoop/Dockerfile -t alako/hadoop .
docker build -f ./sparkworker/Dockerfile -t alako/sparkworker .

