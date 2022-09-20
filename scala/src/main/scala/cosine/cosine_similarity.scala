import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.types._

object scala_test {

	def main(args: Array[String]): Unit = {

		// *** *** *** *** *** ***
		// Create spark session and context
		val sparksession = SparkSession.builder().appName("dot_product").master("local").getOrCreate()
		val sc = sparksession.sparkContext;

		// *** *** *** *** *** ***
		// Create accumulators
		val dot_product = sc.doubleAccumulator("DotProductAccumulator");
		val video_length = sc.doubleAccumulator("VideoLengthAccumulator");
		val query_length = sc.doubleAccumulator("QueryLenghtAccumulator");

		// *** *** *** *** *** ***
		// Read json
		val customSchema = StructType(Array(
			StructField("url", StringType, false),
			StructField("title", StringType, false),
			StructField("description", StringType, false),
			StructField("tags", StringType, false),
			StructField("thumbnail", StringType, false),
			StructField("vector", ArrayType(DoubleType, false), false)
		))

		val sparkDataFrame = sparksession.read.schema(customSchema)
		  .option("multiline", true)
		  .json("hdfs://hadoopmaster:9000/data/data.json")

		// *** *** *** *** *** ***
		// Read query from CLI
		var query: Array[Double] = Array[Double]();

		for (arg <- args) {
			// Remove comma
			if (arg.charAt(arg.length() - 1) == ',') {
				query = query :+ arg.substring(0, arg.length() - 1).toDouble;
			} else {
				query = query :+ arg.toDouble;
			}
		}

		// *** *** *** *** *** ***
		// Algorithm
		def CS(query: Array[Double]): Unit = {
			sc.parallelize(sparkDataFrame.collect()).foreach(row => {
				// Reset accumulators
				dot_product.reset();
				video_length.reset();
				query_length.reset();

				// Get current video data
				val currvid_title = row.getAs[String]("title")
				val currvid_vector = row.getAs[scala.collection.mutable.ArraySeq[Double]]("vector").toArray;

				// Zipped function creates a collection of pairs
				var ccs = (currvid_vector, query).zipped.map { (v, q) => {
						dot_product.add(v * q);
						video_length.add(v * v);
						query_length.add(q * q);
					}
				}

				// Get results
				val cosine_similatiry = dot_product.value / (Math.sqrt(video_length.value) * Math.sqrt(query_length.value));

				if (cosine_similatiry >= 0.5) {
					println("FIND!: " + currvid_title + " : " + Math.round(cosine_similatiry * 100) + "%");
				}

			})
		}

		CS(query);
	}

}
