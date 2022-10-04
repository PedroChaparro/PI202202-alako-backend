#!/bin/bash

FAIL=0
NC="\033[0m"
RED="\033[0;31m"
CYAN="\033[0;36m"
GREEN="\033[0;32m"

# SIGTERM-handler
term_handler() {

	printf "\nfinishing master `ps -aux | wc -l`... " >> /var/opt/hdfs/finishlog
	/opt/hadoop-3.3.3/sbin/stop-all.sh
	printf "finished master `ps -aux | wc -l`.\n" >> /var/opt/hdfs/finishlog
	exit 143; # 128 + 15 -- SIGTERM
}

# setup handlers
# on callback, kill the last background process, which is `tail -f /dev/null` and execute the specified handler
trap 'kill ${!}; term_handler' SIGTERM

# ssh daemon
/usr/sbin/sshd 2>&1 &

# namenode
/opt/hadoop-3.3.3/sbin/start-dfs-namenode.sh 2>&1 | while read line; do printf "$CYAN%s$NC\n" "[namenode] $line"; done &
pidlist="$pidlist $!"

# secondarynamenode
/opt/hadoop-3.3.3/sbin/start-dfs-secondary.sh 2>&1 | while read line; do printf "$GREEN%s$NC\n" "[secondary] $line"; done &
pidlist="$pidlist $!"

# datanode
/opt/hadoop-3.3.3/sbin/start-dfs-datanode.sh 2>&1 | while read line; do printf "$RED%s$NC\n" "[datanode] $line"; done &
pidlist="$pidlist $!"

# wait forever
while true
do
  tail -f /dev/null & wait ${!}
done

## jeremy.zawodny.com/blog/archives/010717.html
## https://medium.com/@gchudnov/trapping-signals-in-docker-containers-7a57fdda7d86
