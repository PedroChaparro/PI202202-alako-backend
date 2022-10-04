#!/bin/bash

# remove previous pid if any
rm /root/Downloads/RUNNING_PID

/opt/jdk-11.0.2/bin/java -Dplay.http.secret.key="$PLAY_SECRET" \
	-jar /data/jar/scala-api-1.0.jar 
