#!/bin/bash

FAIL=0
NC="\033[0m"
RED="\033[0;31m"
CYAN="\033[0;36m"
GREEN="\033[0;32m"
PURPLE="\033[0;35m"

# ssh daemon
/usr/sbin/sshd 2>&1 &

# datanode
/opt/hadoop-3.3.3/sbin/start-dfs-datanode.sh 2>&1 | while read line; do printf "$PURPLE%s$NC\n" "[datanode] $line"; done &
pidlist="$pidlist $!"

# wait
for job in $pidlist
do
	echo $job
	wait $job || let "FAIL+=1"
done

# end
if [ "$FAIL" == "0" ];
then
	echo "succeded ($FAIL)"
else
	echo "failed ($FAIL)"
fi

