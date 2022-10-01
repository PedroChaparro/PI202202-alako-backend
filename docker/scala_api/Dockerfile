FROM debian:bullseye

ENV DEBIAN_FRONTEND=noninteractive
RUN apt update && apt install -y procps #&& rm -rf /var/lib/apt/lists/*

# get resources

RUN mkdir /root/Downloads
WORKDIR /root/Downloads
COPY ./files/pkg/openjdk-11.0.2_linux-x64_bin.tar.gz /root/Downloads/.
COPY ./files/pkg/sbt-1.7.1.tgz                       /root/Downloads/.

# extract

RUN tar -xzf openjdk-11.0.2_linux-x64_bin.tar.gz -C /opt/.
RUN tar -xzf sbt-1.7.1.tgz                       -C /opt/.
RUN rm -r /root/Downloads # clean

# copy config files

COPY ./files/.bashrc          /root/.bashrc
COPY ./scala_api/buildjars.sh /opt/buildjars.sh
COPY ./scala_api/init.sh      /opt/init.sh

# environment variables

# path
ENV JAVA_HOME=/opt/jdk-11.0.2
ENV PATH=$PATH:$JAVA_HOME/bin
ENV PATH=$PATH:/opt/sbt/bin

# play framework
ENV PLAY_HTTP_PORT=9090
ENV PLAY_HTTPS_PORT=9091

