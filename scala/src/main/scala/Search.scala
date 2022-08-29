import scala.math.random

import org.apache.spark._

/** Computes an approximation to pi */
/*object SparkPi {
  def main(args: Array[String]) {
    val conf = new SparkConf().setAppName("Spark Pi")
    val spark = new SparkContext(conf)
    val slices = if (args.length > 0) args(0).toInt else 2
    val n = 100000 * slices
    val count = spark.parallelize(1 to n, slices).map { i =>
      val x = random * 2 - 1
      val y = random * 2 - 1
      if (x*x + y*y < 1) 1 else 0
    }.reduce(_ + _)
    println("Pi is roughly " + 4.0 * count / n)
    spark.stop()
  }
}*/

object myobj1 {
  def main(args: Array[String] ){
    val conf = new SparkConf().setAppName("MyObj1")
    val spark = new SparkContext(conf)

    val lines = spark.textFile("hdfs://deb11spark:9000/data.json")
    val lineLengths = lines.map(s => s.length)
    val totalLength = lineLengths.reduce((a, b) => a + b)

    println("OUT")
    println(lineLengths)
    println(totalLength)
    spark.stop()
  }
}
