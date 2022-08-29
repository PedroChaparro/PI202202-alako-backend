
# Develop a Spark Application

### Prerequisites
- Spark Master and Hadoop Master are running.
- At least one Spark Worker running.

### Building and running 

1. Install __sbt__:

	```
	wget https://github.com/sbt/sbt/releases/download/v1.7.1/sbt-1.7.1.tgz
	tar -xzf sbt-1.7.1.tgz -C /opt/.
	```

2. Project structure, your program goes in `src/main/scala/yourprogram.scala`:

	```
	scalaProject
	├── build.sbt
	├── target/
	├── project/
	│   └── build.properties
	└── src/
	    └── main/
		    └── scala/
	            └── Search.scala
	```

3. Install dependencies and build:

	```
	sbt package
	```

	A __.jar__ file should be create inside `target/scala-2.12/` directory.

4. Run:

	```
	spark-submit --class CLASS --master SPARKMASTER FILE
	```

	- CLASS. Must be a class in the scala file.
	- SPARKMASTER. Spark url to master. This url can be found in http://localhost:8080
	- FILE. Compiled jar file

	Example:

	```
	spark-submit --class "SparkPi" --master spark://deb11spark:7077 ./target/scala-2.12/spark-sample_2.12-0.1.jar
	```

