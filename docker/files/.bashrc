# ~/.bashrc: executed by bash(1) for non-login shells.

# Note: PS1 and umask are already set in /etc/profile. You should not
# need this unless you want different defaults for root.
# PS1='${debian_chroot:+($debian_chroot)}\h:\w\$ '
PS1='${debian_chroot:+($debian_chroot)}\[\033[01;32m\]\u@\h\[\033[00m\]:\[\033[01;34m\]\w\[\033[00m\]\$ '

# umask 022

# You may uncomment the following lines if you want `ls' to be colorized:
# export LS_OPTIONS='--color=auto'
# eval "$(dircolors)"
# alias ls='ls $LS_OPTIONS'
# alias ll='ls $LS_OPTIONS -l'
# alias l='ls $LS_OPTIONS -lA'
#
# Some more alias to avoid making mistakes:
# alias rm='rm -i'
# alias cp='cp -i'
# alias mv='mv -i'

TERM=xterm

# hadoop
export HADOOP_HOME=/opt/hadoop-3.3.3
export PATH=$PATH:$HADOOP_HOME/bin
export HDFS_NAMENODE_USER=root
export HDFS_SECONDARYNAMENODE_USER=root
export HDFS_DATANODE_USER=root # use a different user
export PDSH_RCMD_TYPE=ssh

# spark
export SPARK_HOME=/opt/spark-3.3.0
export PATH=$PATH:$SPARK_HOME/bin

# scala
export SCALA_HOME=/opt/scala-2.12.16
export PATH=$PATH:$SCALA_HOME/bin

# sbt
export PATH=$PATH:/opt/sbt/bin

# java
export JAVA_HOME=/opt/jdk-11.0.2
export PATH=$PATH:$JAVA_HOME/bin
export JAVA_HOME=/opt/jdk-17.0.2
export PATH=$PATH:$JAVA_HOME/bin

