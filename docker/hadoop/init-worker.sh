#!/bin/bash

FAIL=0
NC="\033[0m"
RED="\033[0;31m"
CYAN="\033[0;36m"
GREEN="\033[0;32m"
PURPLE="\033[0;35m"

# SIGTERM-handler
term_handler() {

	printf "\nfinishing worker `ps -aux | wc -l`... " >> /var/opt/hdfs/finishlog
	/opt/hadoop-3.3.3/sbin/stop-all.sh
	printf "finished worker `ps -aux | wc -l`.\n" >> /var/opt/hdfs/finishlog
	exit 143; # 128 + 15 -- SIGTERM
}

# setup handlers
# on callback, kill the last background process, which is `tail -f /dev/null` and execute the specified handler
trap 'kill ${!}; term_handler' SIGTERM

# ssh daemon
/usr/sbin/sshd 2>&1 &

# datanode
/opt/hadoop-3.3.3/sbin/start-dfs-datanode.sh 2>&1 | while read line; do printf "$PURPLE%s$NC\n" "[datanode] $line"; done &
pidlist="$pidlist $!"

# wait forever
while true
do
  tail -f /dev/null & wait ${!}
done
