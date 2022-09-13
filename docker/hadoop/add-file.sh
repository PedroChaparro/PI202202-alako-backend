#!/bin/bash

message()
{
	printf "\n\033[01;31m===================== $1 =====================\n"
}

message "Starting ssh daemon"
/usr/sbin/sshd 2>&1
/bin/sleep 2

message "Cleaning HDFS"
rm -r /var/opt/hdfs/*
/opt/hadoop-3.3.3/bin/hdfs namenode -format -force

message "Starting HDFS"
/opt/hadoop-3.3.3/sbin/start-dfs.sh 2>&1
/bin/sleep 10

message "Create HDFS /data directory"
/opt/hadoop-3.3.3/bin/hdfs dfs -mkdir /data

message "Add file as /data/data.json"
/opt/hadoop-3.3.3/bin/hdfs dfs -put /data/data.json /data/data.json

message "Stopping HDFS"
/opt/hadoop-3.3.3/sbin/stop-dfs.sh 2>&1

message "Finished"
