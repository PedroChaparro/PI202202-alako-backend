#!/bin/bash

if [ ! $2 ]; then
cat << EOF
install-to-shh: Install alako files to ssh host

Usage
./install-to-ssh.sh <user> <host>
Example
./install-to-ssh.sh azureuser 10.0.0.5
EOF
exit
fi

# archive git tracked files and pkg files

if [ ! -f ./alakoback.tar ]; then
	# delete previous clone
	if [ -d /tmp/alakoclone ]; then
		echo "Cleaning /tmp/alakoclone"
		rm -rf /tmp/alakoclone
	fi

	# clone
	echo "Creating /tmp/alakoclone"
	git clone -b `git branch --show-current` `git rev-parse --show-toplevel` /tmp/alakoclone 

	# copy pkg files
	cp ./files/pkg/* /tmp/alakoclone/docker/files/pkg/.

	# package
	echo "Creating alakoclone.tar"
	tar -cvf alakoback.tar -C /tmp/alakoclone/ .

	# clean
	echo "Cleaning /tmp/alakoclone"
	rm -rf /tmp/alakoclone

fi

## copy to destination

echo "Copying to destination"
ssh $1@$2 "rm -rf ~/alakoback && mkdir -p ~/alakoback"
scp alakoback.tar $1@$2:~/alakoback/. 

