package controllers

import javax.inject.Inject
import play.api.mvc.{Action, AnyContent, BaseController, ControllerComponents}
import play.api.libs.json.{JsArray, JsValue, Json, JsSuccess, JsError, JsResult}
import play.api.libs.ws._
import org.apache.spark.launcher.SparkLauncher
import org.apache.spark.launcher.SparkAppHandle

import scala.concurrent._
import ExecutionContext.Implicits.global
import scala.language.postfixOps

class restApiScala @Inject() (val controllerComponents: ControllerComponents, ws: WSClient) extends BaseController {

  // Empty / initial map
  var responses: Map[String, JsValue] = Map()

  //this function allows request user input from web page, request API python an receive the vector
  def fromUserInputToResponseVector(): Action[AnyContent] = Action {
    request => {
      //obtaining body from request of user from search bar and validating it
      val jsonBody: Option[JsValue] = request.body.asJson

      if(jsonBody != None) {
        try {
          //obtaining json
          val userInput: JsValue = jsonBody.get("search-criteria")

          val payload: JsValue = Json.obj(
              "search-criteria" -> userInput
          )

          // #### #### Scala #### ####
          val uuid: String = java.util.UUID.randomUUID().toString
          println("Current UUID: " + uuid)

          // #### #### Python #### ####
          //send json to API python into the body
          val sentPython = ws.url("http://vectorize_api:5050/vectorize")
          //processing response
          sentPython.post(payload).map({ response =>
            val jsonBody: JsValue = response.json
            val vectorResult: JsResult[Array[Double]] = (jsonBody \ "vector").validate[Array[Double]]
            val vector: Array[Double] = vectorResult.get

            // create spark job
            val sparkjob = new SparkLauncher()
              .setJavaHome("/opt/jdk-11.0.2")
              .setSparkHome("/opt/spark-3.3.0")
              .setMaster("spark://sparkmaster:7077")
              .setAppResource("/data/jar/cosine-similarity-job-1.0.jar")
              .setMainClass("cosine_similarity_job")
              .setDeployMode("client")
              .addAppArgs(uuid)

            // pass vector
            for (vect <- vector) {
              sparkjob.addAppArgs(vect.toString)
            }

            // run
            val appHandle : SparkAppHandle = sparkjob.startApplication();
            println("Running spark job ");

          })




          Ok(Json.obj(
            "error" -> false,
            "message" -> "Request received.",
            "uuid" -> uuid
          ))
        } catch {
          case e: NoSuchElementException => {
            BadRequest(Json.obj(
              "error" -> true,
              "message" -> "search-criteria (\"search-criteria\") field is required."
            ))
          }
        }
      } else {
        //Not json
        BadRequest(Json.obj(
          "error" -> true,
          "message" -> "Json body was not provided"
        ))
      }
    }
  }

  // Save final response from spark job
  def saveResponse(): Action[AnyContent] = Action {
    // Get request
    request => {
      // Validate request
      val jsonBody: Option[JsValue] = request.body.asJson

      if (jsonBody != None) {
        try {
          // Get from json
          val jobResults: JsValue = jsonBody.get("result")
          val jobKey: JsValue = jsonBody.get("key")
          responses = responses.+(jobKey.toString() -> jobResults)

          println("Response was saved successfully.")

          Ok(Json.obj(
            "error" -> false,
            "message" -> "Response was saved successfully."
          ))

        } catch {
          case e: NoSuchElementException => {
            BadRequest(Json.obj(
              "error" -> true,
              "message" -> "Result (\"result\") and key (\"key\") fields are required."
            ))
          }
        }
      } else {
        // Not json
        BadRequest(Json.obj(
          "error" -> true,
          "message" -> "Json body was not provided."
        ))
      }

    }
  }

  // Get final response from client
  def getResponse(): Action[AnyContent] = Action {
    request => {
      // Validate request
      val jsonBody: Option[JsValue] = request.body.asJson

      if (jsonBody != None) {
        try {
          // Get from json
          val jobKey: JsValue = jsonBody.get("key")

          if (responses.contains(jobKey.toString())) {
            // Make response
            val response: JsValue = Json.obj(
              "response" -> responses.get(jobKey.toString()),
              "message" -> "OK",
              "error" -> false
            )

            // Remove from map
            responses = responses.-(jobKey.toString())

            Ok(response)
          } else {
            NotFound(Json.obj(
              "message" -> "Key was not found. Resource might still be processing.",
              "error" -> true
            ))
          }
        } catch {
          case e: NoSuchElementException => {
            BadRequest(Json.obj(
              "message" -> "Key (\"key\") field is required.",
              "error" -> true
            ))
          }
        }
      } else {
        // Not json
        BadRequest(Json.obj(
          "message" -> "Json body was not provided",
          "error" -> true
        ))
      }
    }
  }

}
