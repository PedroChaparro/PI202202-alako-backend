FROM debian:bullseye

# ssh service

ENV DEBIAN_FRONTEND=noninteractive
RUN apt update && apt install -y procps #&& rm -rf /var/lib/apt/lists/*

# get resources

RUN mkdir /root/Downloads
WORKDIR /root/Downloads

COPY ./files/pkg/openjdk-17.0.2_linux-x64_bin.tar.gz /root/Downloads/.
# COPY ./files/pkg/scala-2.12.16.tgz                   /root/Downloads/.
COPY ./files/pkg/spark-3.3.0-bin-hadoop3.tgz         /root/Downloads/.

# extract

RUN tar -xzf spark-3.3.0-bin-hadoop3.tgz -C /opt/.
RUN mv /opt/spark-3.3.0-bin-hadoop3 /opt/spark-3.3.0
# RUN tar -xzf scala-2.12.16.tgz -C /opt/.
RUN tar -xzf openjdk-17.0.2_linux-x64_bin.tar.gz -C /opt/.

# clean

RUN rm -r /root/Downloads

# copy config files

COPY ./files/.bashrc /root/.bashrc
COPY ./sparkWorker/init.sh /opt/init.sh
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

# init script

CMD ["/opt/init.sh"]

