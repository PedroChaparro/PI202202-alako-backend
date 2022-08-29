name := "Spark Sample"

version := "1.0"

// scalaVersion := "2.12.16"
// scalaVersion := "2.10.3"

// libraryDependencies += "org.apache.spark" %% "spark-core"
// libraryDependencies += "org.apache.spark" %% "spark-core" % "1.1.1"
// libraryDependencies += "org.apache.spark" %% "spark-core" % "1.1.1"

libraryDependencies += "org.scala-lang" % "scala-library" % "2.12.16"
libraryDependencies += "org.apache.hadoop" % "hadoop-client" % "3.3.3" // hadoop client

val sparkVersion = "3.3.0"

libraryDependencies ++= Seq(
  "org.apache.spark" %% "spark-core" % sparkVersion,
  "org.apache.spark" %% "spark-sql" % sparkVersion,
  "org.apache.spark" %% "spark-mllib" % sparkVersion
)

