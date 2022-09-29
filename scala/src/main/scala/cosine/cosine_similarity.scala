import org.apache.spark.sql.{SparkSession, Row}
import org.apache.spark.sql.types._
import play.api.libs.json.{JsArray, Json, JsObject}
import scalaj.http._

object scala_test {

	def main(args: Array[String]): Unit = {

		// *** *** *** *** *** ***
		// Create spark session and context
		val sparksession = SparkSession.builder().appName("dot_product").getOrCreate()
		val sc = sparksession.sparkContext

		// *** *** *** *** *** ***
		// Create accumulators
		// val dot_product = sc.doubleAccumulator("DotProductAccumulator")
		// val video_length = sc.doubleAccumulator("VideoLengthAccumulator")
		// val query_length = sc.doubleAccumulator("QueryLenghtAccumulator")

		// *** *** *** *** *** ***
		// Read json
		val jsonSchema = StructType(Array(
			StructField("url", StringType, false),
			StructField("title", StringType, false),
			StructField("tags", StringType, false),
			StructField("thumbnail", StringType, false),
			StructField("vector", ArrayType(DoubleType, false), false)
		))

		val sparkDataFrame = sparksession.read.schema(jsonSchema)
		  .option("multiline", true)
		  .json("hdfs://hadoopmaster:9000/data/data.json")

		// sparkDataFrame.show()

		// *** *** *** *** *** ***
		// Read query from CLI
		var key: String = ""
		var query: Array[Double] = Array[Double]()

		for(index <- 0 to 384){
			if(index == 0){// Key
				key = args(0)
			}else{
				// Remove comma
				if (args(index).charAt(args(index).length() - 1) == ',') {
					query = query :+ args(index).substring(0, args(index).length() - 1).toDouble
				} else {
					query = query :+ args(index).toDouble
				}
			}
		}

		// *** *** *** *** *** ***
		// Algorithm
		def CS(query: Array[Double]): JsArray = {

			// Json response
			var arr: JsArray = new JsArray()

			// Testing with filter
			// val rdd = sc.parallelize(sparkDataFrame.collect()

			sparkDataFrame.collect().foreach(row => {
				// Reset accumulators
				// dot_product.reset()
				// video_length.reset()
				// query_length.reset()
				var dot_product:Double = 0.0
				var video_length:Double = 0.0
				var query_length:Double = 0.0

				// Get current video data
				val currvid_title = row.getAs[String]("title")
				val currvid_url = row.getAs[String]("url")
				val currvid_tags =  row.getAs[String]("tags")
				val currvid_thumbnail = row.getAs[String]("thumbnail")
				val currvid_vector = row.getAs[scala.collection.mutable.ArraySeq[Double]]("vector").toArray

				// Zipped function creates a collection of pairs
				var ccs = (currvid_vector, query).zipped.map { (v, q) => {
						//dot_product.add(v * q)
						//video_length.add(v * v)
						//query_length.add(q * q)
						dot_product += v * q
						video_length += v * v
						query_length += q * q
					}
				}

				// Get results
				//val cosine_similatiry = dot_product.value / (Math.sqrt(video_length.value) * Math.sqrt(query_length.value))
				//sparkDataFrame = sparkDataFrame.withColumn("cosine_similarity", when(col("url") === currvid_url, cosine_similatiry).otherwise(col("cosine_similarity")))
				val cosine_similarity = dot_product / (Math.sqrt(video_length) * Math.sqrt(query_length))

				if (cosine_similarity >= 0.5) {
					//println("FIND!: " + currvid_title + " : " + Math.round(cosine_similarity * 100) + "%")
					val vid_object = Json.obj( // Create json object
						"url" -> currvid_url,
						"title" -> currvid_title,
						"thumbnail" -> currvid_thumbnail,
						"tags" -> currvid_tags,
						"similarity" -> cosine_similarity
					)

					arr = arr ++ Json.arr(vid_object)
				}

			})

			return arr

		}

		val response: JsArray =  CS(query)

		val body: JsObject = Json.obj(
			"key" -> key,
			"result" -> response
		)

		// *Change to scala API ip*
		val apiCall = Http("http://localhost:9090/result/save")
		  .postData(body.toString())
		  .header("content-type", "application/json")
		  .asString

		if(apiCall.code == 200){
			println(key + " job finished successfully")
		}else{
			println("Unable to finish " + key + " job")
		}

	}

}