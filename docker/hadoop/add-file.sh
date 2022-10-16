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
rm -r /var/opt/hdfsworker/*
/opt/hadoop-3.3.3/bin/hdfs namenode -format -force

message "Starting HDFS"
/opt/hadoop-3.3.3/sbin/start-dfs.sh 2>&1
/bin/sleep 10

message "Create HDFS /data directory"
/opt/hadoop-3.3.3/bin/hdfs dfs -mkdir /data
message "Add file as /data/data.json"
/opt/hadoop-3.3.3/bin/hdfs dfs -put /data/data.json /data/data.json

message "Create HDFS /jar directory"
/opt/hadoop-3.3.3/bin/hdfs dfs -mkdir /jar
message "Add jar cosine-similarity-job-1.0.jar in /jar"
/opt/hadoop-3.3.3/bin/hdfs dfs -put /data/jar/cosine-similarity-job-1.0.jar /jar/cosine-similarity-job-1.0.jar

/opt/hadoop-3.3.3/bin/hdfs dfs -ls /
/opt/hadoop-3.3.3/bin/hdfs dfs -ls /data/
/opt/hadoop-3.3.3/bin/hdfs dfs -ls /jar/

message "Stopping HDFS"
/opt/hadoop-3.3.3/sbin/stop-all.sh 2>&1

ps -aux

message "Finished"
