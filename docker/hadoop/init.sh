#!/bin/sh

/usr/sbin/sshd 2>&1 &
/opt/hadoop-3.3.3/sbin/start-dfs.sh 2>&1 &

# workaround to keep it alive
/bin/sleep infinity
