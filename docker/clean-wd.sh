#!/bin/sh
# removes HDFS directories blocking docker build

sudo rm -r ./hdfs/master/namenode
sudo rm -r ./hdfs/master/datanode
sudo rm -r ./hdfs/worker/datanode
