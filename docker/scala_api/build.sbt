name := """restApiScala"""
organization := "alako"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.13.9"

libraryDependencies += guice
libraryDependencies += "org.scalatestplus.play" %% "scalatestplus-play" % "5.0.0" % Test
libraryDependencies ++= Seq(ws)

val sparkVersion = "3.3.0"
libraryDependencies += "org.scala-lang" % "scala-library" % "2.12.16"
libraryDependencies ++= Seq(
  "org.apache.spark" %% "spark-core" % sparkVersion
)

assemblyMergeStrategy in assembly := {
 case PathList("META-INF", _*) => MergeStrategy.discard
 case "reference.conf" => MergeStrategy.concat
 case _                        => MergeStrategy.first
}

// Custom jar name
assemblyJarName in assembly := "scala-api-1.0.jar"

// Adds additional packages into Twirl
//TwirlKeys.templateImports += "sp.controllers._"

// Adds additional packages into conf/routes
// play.sbt.routes.RoutesKeys.routesImport += "sp.binders._"
