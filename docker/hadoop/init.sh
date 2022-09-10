#!/bin/bash

FAIL=0
RED="\033[0;31m"
GREEN="\033[0;32m"
CYAN="\033[0;36m"
NC="\033[0m"

# ssh daemon
/usr/sbin/sshd 2>&1 &

# namenode
/opt/hadoop-3.3.3/sbin/start-dfs-namenode.sh 2>&1 | while read line; do printf "$CYAN%s$NC\n" "[namenode] $line"; done &
pidlist="$pidlist $!"

# secondarynamenode
/opt/hadoop-3.3.3/sbin/start-dfs-secondary.sh 2>&1 | while read line; do printf "$GREEN%s$NC\n" "[secondary] $line"; done &
pidlist="$pidlist $!"

# datanode
#/opt/hadoop-3.3.3/sbin/custom-start-dfs-3.sh 2>&1 & | while read line; do echo "[] $line"; done

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

# jeremy.zawodny.com/blog/archives/010717.html
