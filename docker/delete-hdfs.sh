#!/bin/sh
# removes HDFS directories blocking docker build

sudo rm -r ./files/hdfs/master/namenode
sudo rm -r ./files/hdfs/master/datanode
sudo rm -r ./files/hdfs/worker/datanode
