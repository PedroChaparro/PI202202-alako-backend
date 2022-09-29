## Building "fat .jar"

**Note:** Fat .jar includes the required code for all the dependencies (Except the provided ones).

```
sbt assembly
```

## Submiting a job

Base command is:

```
spark-submit --class scala_test --master spark://sparkmaster:7077 ./target/scala-2.12/'Spark Sample-assembly-1.0.jar'
```

But you have to send the video vector as a parameter (e.g):

```
spark-submit --class scala_test --master spark://sparkmaster:7077 ./target/scala-2.12/'Spark Sample-assembly-1.0.jar' -0.406119167804718 -0.21913136541843414 -0.09069749712944031 -0.02023126743733883 ...
```