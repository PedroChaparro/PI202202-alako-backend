
## Requirements

- Docker 

## Setup

Before running any command make sure you are in the `/docker`directory.

1. Download packages. This script will automatically download the required packages and store them in the `files/pkg` directory:

	```
	./downloadPackages.sh
	```
	> Windows script not available yet. If you are on windows manually download the files with the links inside this file and place them in `files/pkg`.

2. Building the images. Make sure you already have the required files in `files/pkg` before building:


	To build __Hadoop Master Node__:

	```
	docker build -f ./hadoop/Dockerfile -t alako/hadoopmaster .
	```

	To build __Spark Master Node__:

	```
	docker build -f ./spark/Dockerfile -t alako/sparkmaster .
	```

## Run

1. Start the services by running docker compose as follows:

	```
	docker-compose up
	```

2. Check Hadoop and Spark state by browsing the web monitors:
	- Hadoop http://localhost:9870/
	- Spark http://localhost:8080/

