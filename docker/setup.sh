# pkgs
./downloadPackages.sh

# containers
docker build -f ./hadoop/Dockerfile -t alako/hadoop .
docker build -f ./sparkmaster/Dockerfile -t alako/sparkmaster .
docker build -f ./sparkworker/Dockerfile -t alako/sparkworker .
docker build -f ./vectorize_api/Dockerfile -t alako/vectorize_api .
docker build -f ./scala_api/Dockerfile -t alako/scala_api .

# jar files
docker-compose -f compose-build-scala-api.yml up
docker-compose -f compose-build-cosine.yml up

# Create HDFS and import file
docker-compose -f compose-addfile.yml up
