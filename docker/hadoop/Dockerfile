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

RUN tar -xzf hadoop-3.3.3.tar.gz -C /opt/.
RUN tar -xzf openjdk-17.0.2_linux-x64_bin.tar.gz -C /opt/.

# clean

RUN rm -r /root/Downloads

# copy config files

COPY ./files/.bashrc /root/.bashrc
COPY ./hadoop/core-site.xml /opt/hadoop-3.3.3/etc/hadoop/core-site.xml
COPY ./hadoop/hdfs-site.xml /opt/hadoop-3.3.3/etc/hadoop/hdfs-site.xml
COPY ./hadoop/init.sh /opt/init.sh
COPY ./hadoop/start-dfs.sh /opt/hadoop-3.3.3/sbin/start-dfs.sh

RUN mkdir -p /var/opt/hdfs/namenode
RUN mkdir -p /var/opt/hdfs/datanode
RUN chmod u+x /opt/init.sh

# env

ENV HADOOP_HOME=/opt/hadoop-3.3.3
ENV PATH=$PATH:$HADOOP_HOME/bin
ENV HDFS_NAMENODE_USER=root
ENV HDFS_SECONDARYNAMENODE_USER=root
ENV HDFS_DATANODE_USER=root
ENV PDSH_RCMD_TYPE=ssh
# path spark
ENV SPARK_HOME=/opt/spark-3.3.0
ENV PATH=$PATH:$SPARK_HOME/bin
# path scala
ENV SCALA_HOME=/opt/scala-2.12.16
ENV PATH=$PATH:$SCALA_HOME/bin
# path java
ENV JAVA_HOME=/opt/jdk-17.0.2
ENV PATH=$PATH:$JAVA_HOME/bin

# setup HDFS (Hadoop file system)

# RUN hdfs namenode -format

# init script

CMD ["/opt/init.sh"]

