FROM debian:bullseye

# ssh service

ENV DEBIAN_FRONTEND=noninteractive
RUN apt update && apt install -y ssh #&& rm -rf /var/lib/apt/lists/*
RUN mkdir /run/sshd

# passwordless ssh localhost

RUN mkdir /root/.ssh
RUN ssh-keygen -t rsa -f /root/.ssh/id_rsa -N ''
RUN cat /root/.ssh/id_rsa.pub >> /root/.ssh/authorized_keys
RUN chmod og-wx /root/.ssh/authorized_keys

# get resources

RUN mkdir /root/Downloads
WORKDIR /root/Downloads
COPY ./files/pkg/hadoop-3.3.3.tar.gz                 /root/Downloads/.
COPY ./files/pkg/openjdk-17.0.2_linux-x64_bin.tar.gz /root/Downloads/.

# extract

RUN tar -xzf hadoop-3.3.3.tar.gz                 -C /opt/.
RUN tar -xzf openjdk-17.0.2_linux-x64_bin.tar.gz -C /opt/.
RUN rm -r /root/Downloads # clean

# copy config files

COPY ./files/.bashrc        /root/.bashrc
COPY ./hadoop/core-site.xml /opt/hadoop-3.3.3/etc/hadoop/core-site.xml
COPY ./hadoop/hdfs-site.xml /opt/hadoop-3.3.3/etc/hadoop/hdfs-site.xml

COPY ./hadoop/start-dfs-namenode.sh  /opt/hadoop-3.3.3/sbin/start-dfs-namenode.sh
COPY ./hadoop/start-dfs-datanode.sh  /opt/hadoop-3.3.3/sbin/start-dfs-datanode.sh
COPY ./hadoop/start-dfs-secondary.sh /opt/hadoop-3.3.3/sbin/start-dfs-secondary.sh

COPY ./hadoop/init-master.sh /opt/init-master.sh
COPY ./hadoop/init-worker.sh /opt/init-worker.sh
COPY ./hadoop/add-file.sh    /opt/add-file.sh

# environment variables

# hadoop
ENV HADOOP_HOME=/opt/hadoop-3.3.3
ENV PATH=$PATH:$HADOOP_HOME/bin
ENV HDFS_NAMENODE_USER=root
ENV HDFS_SECONDARYNAMENODE_USER=root
ENV HDFS_DATANODE_USER=root
ENV PDSH_RCMD_TYPE=ssh
# java
ENV JAVA_HOME=/opt/jdk-17.0.2
ENV PATH=$PATH:$JAVA_HOME/bin

