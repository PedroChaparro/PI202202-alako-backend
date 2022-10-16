#!/bin/bash

if [ ! $2 ]; then
cat << EOF
install-to-shh: Install alako files to ssh host

Usage
./install-to-ssh.sh <user> <host>
Example
./install-to-ssh.sh azureuser 10.0.0.5
EOF
fi

# archive git tracked files and pkg files

if [ ! -f ./alakoback.tar ]; then
	tar -cvf alakoback.tar `git ls-files && find files/pkg/ -type f`
fi

## copy to destination

scp alakoback.tar $1@$2:/data/. 
