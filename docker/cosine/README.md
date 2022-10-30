## Building "fat .jar"

**Note:** Fat .jar includes the required code for all the dependencies (Except the provided ones).

From `/docker/cosine` folder:

```
sbt assembly
```

## Submiting a job

Base command is:

```
spark-submit --class cosine_similarity_job --master spark://sparkmaster:7077 ./target/scala-2.12/cosine-similarity-job-1.0.jar
```

But you must send the video vector and result key as a parameter (e.g):

```
spark-submit --class cosine_similarity_job --master spark://sparkmaster:7077 ./target/scala-2.12/cosine-similarity-job-1.0.jar e625fdf0-5181-4393-909d-c47a4a963e54 -0.406119167804718 -0.21913136541843414 -0.09069749712944031 -0.02023126743733883 ...
```
