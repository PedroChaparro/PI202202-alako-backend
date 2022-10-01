#!/bin/bash

# remove previous pid if any
rm /root/Downloads/RUNNING_PID

java -Dplay.http.secret.key="$PLAY_SECRET" \
	-jar /data/jar/scala-api-1.0.jar 
