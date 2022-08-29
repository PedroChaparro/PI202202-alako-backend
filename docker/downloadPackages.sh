#!/bin/bash

# get packages from the internet
wget https://dlcdn.apache.org/spark/spark-3.3.0/spark-3.3.0-bin-hadoop3.tgz -P files/pkg
wget https://downloads.lightbend.com/scala/2.12.16/scala-2.12.16.tgz -P files/pkg
wget https://archive.apache.org/dist/hadoop/common/hadoop-3.3.3/hadoop-3.3.3.tar.gz -P files/pkg
wget https://download.java.net/java/GA/jdk17.0.2/dfd4a8d0985749f896bed50d7138ee7f/8/GPL/openjdk-17.0.2_linux-x64_bin.tar.gz -P files/pkg

# get required files from an lxd server
# REMOTE="root@10.70.178.213"
# DESTDIR="./configfiles/"

# scp $REMOTE:/root/.bashrc $DESTDIR
# scp $REMOTE:/opt/hadoop-3.3.3/etc/hadoop/hdfs-site.xml $DESTDIR
# scp $REMOTE:/opt/hadoop-3.3.3/etc/hadoop/core-site.xml $DESTDIR

# scp $REMOTE:/root/Downloads/openjdk-17.0.2_linux-x64_bin.tar.gz $DESTDIR
# scp $REMOTE:/root/Downloads/spark-3.3.0-bin-hadoop3.tgz $DESTDIR
# scp $REMOTE:/root/Downloads/scala-2.12.16.tgz $DESTDIR
# scp $REMOTE:/root/Downloads/hadoop-3.3.3.tar.gz $DESTDIR

