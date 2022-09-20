FROM debian:bullseye

# ssh service

ENV DEBIAN_FRONTEND=noninteractive
RUN apt update && apt install -y procps #&& rm -rf /var/lib/apt/lists/*

# get resources

RUN mkdir /root/Downloads
WORKDIR /root/Downloads
COPY ./files/pkg/openjdk-17.0.2_linux-x64_bin.tar.gz /root/Downloads/.
COPY ./files/pkg/spark-3.3.0-bin-hadoop3.tgz         /root/Downloads/.

# extract

RUN tar -xzf spark-3.3.0-bin-hadoop3.tgz         -C /opt/.
RUN tar -xzf openjdk-17.0.2_linux-x64_bin.tar.gz -C /opt/.
RUN mv /opt/spark-3.3.0-bin-hadoop3 /opt/spark-3.3.0
RUN rm -r /root/Downloads # clean

# copy config files

COPY ./files/.bashrc       /root/.bashrc
COPY ./sparkworker/init.sh /opt/init.sh
RUN chmod u+x /opt/init.sh

# environment variables

# spark
ENV SPARK_HOME=/opt/spark-3.3.0
ENV PATH=$PATH:$SPARK_HOME/bin
# java
ENV JAVA_HOME=/opt/jdk-17.0.2
ENV PATH=$PATH:$JAVA_HOME/bin

# init script

CMD ["/opt/init.sh"]

