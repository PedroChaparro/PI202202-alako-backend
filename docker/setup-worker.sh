#!/bin/sh
# setup worker
# only tested in debian 11

# docker
sudo apt update
sudo apt install docker docker.io docker-compose -y
sudo usermod -aG docker $USER

# build containers
docker build -f ./hadoop/Dockerfile -t alako/hadoop .
docker build -f ./sparkworker/Dockerfile -t alako/sparkworker .

