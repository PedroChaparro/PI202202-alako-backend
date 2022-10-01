
## Requirements

- Docker installed.
- Linux-like file system. Windows users can use www.cygwin.com or similar to run the commands.

## Setup

Before running any command make sure you are in the `/docker` directory.

1. Download packages. This script will automatically download the required packages and store them in the `files/pkg` directory:

	```
	./downloadPackages.sh
	```

2. Building the images. Make sure you already have the required files in `files/pkg` before building:


	To build __alako/hadoop__ (same image for master & worker):

	```
	docker build -f ./hadoop/Dockerfile -t alako/hadoop .
	```

	To build __alako/sparkmaster__:

	```
	docker build -f ./sparkmaster/Dockerfile -t alako/sparkmaster .
	```

	To build __alako/sparkworker__:

	```
	docker build -f ./sparkworker/Dockerfile -t alako/sparkworker .
	```

	To build __alako/vectorize_api__:

	```
	docker build -f ./vectorize_api/Dockerfile -t alako/vectorize_api .
	```

	To build __alako/scala_api__:

	```
	docker build -f ./scala_api/Dockerfile -t alako/scala_api .
	```

3. Build jar files:
	
	To build `scala-api.jar`:
	
	```
	docker-compose -f compose-build-scala-api.yml up
	```

	To build `cosine-similarity-job.jar`:

	```
	docker-compose -f compose-build-cosine.yml up
	```

## Create HDFS and import file

1. Your file must be named `data.json`, place it as `files/data/data.json`.

2. Execute the command below to import the file. Wait until it finishes:

	```
	docker-compose -f compose-addfile.yml up
	```

	> Note that all previous files will be destroyed.

	> The HDFS is stored in the local `files/hdfs` directory. And it's inaccessible by default. Windows users must use a linux-like file system, such as __cygwin__.

## Run All

> Run all is only for development

1. Start the services by running docker compose as follows:

	```
	docker-compose -f compose-all.yml up
	```

2. Check Hadoop and Spark state by browsing the web monitors:
	- Hadoop http://localhost:9870/
	- Spark http://localhost:8080/

## Run master

```
docker-compose -f compose-master.yml up
```

## Run worker

Change the IP in compose-worker.yml for the master machine IP. Then run:

```
docker-compose -f compose-worker.yml up
```


