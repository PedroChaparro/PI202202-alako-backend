name := "Cosine Similarity"

version := "1.0"

// scalaVersion := "2.12.16"
// scalaVersion := "2.10.3"

// libraryDependencies += "org.apache.spark" %% "spark-core"
// libraryDependencies += "org.apache.spark" %% "spark-core" % "1.1.1"
// libraryDependencies += "org.apache.spark" %% "spark-core" % "1.1.1"

// Solve merge errors (Deduplicateds) when use sbt assembly
assemblyMergeStrategy in assembly := {
 case PathList("META-INF", _*) => MergeStrategy.discard
 case _                        => MergeStrategy.first
}

// Custom jar name
assemblyJarName in assembly := "cosine-similarity-job-1.0.jar"

libraryDependencies += "org.scala-lang" % "scala-library" % "2.12.16" % "provided"
libraryDependencies += "org.apache.hadoop" % "hadoop-client" % "3.3.3" % "provided" // hadoop client

val sparkVersion = "3.3.0"

libraryDependencies ++= Seq(
  "org.apache.spark" %% "spark-core" % sparkVersion % "provided",
  "org.apache.spark" %% "spark-sql" % sparkVersion % "provided",
  "com.typesafe.play" %% "play-json" % "2.9.3", 
  "org.scalaj" %% "scalaj-http" % "2.4.2"
)

