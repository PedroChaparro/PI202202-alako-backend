#!/bin/bash

FAIL=0
NC="\033[0m"
RED="\033[0;31m"
CYAN="\033[0;36m"
GREEN="\033[0;32m"

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
