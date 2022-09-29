package controllers

import javax.inject.Inject
import play.api.mvc.{Action, AnyContent, BaseController, ControllerComponents}
import play.api.libs.json.{JsValue, Json}
import play.api.libs.ws._

import scala.concurrent._
import ExecutionContext.Implicits.global
import scala.language.postfixOps


class MyClass @Inject() (val controllerComponents: ControllerComponents, ws: WSClient) extends BaseController {

  // Empty / initial map
  var responses: Map[String, JsValue] = Map()

  //this function allows request user input from web page, request API python an receive the vector
  def fromUserInputToResponseVector(): Action[AnyContent] = Action { request =>
    //todo pending to change
    val jsonBody: Option[JsValue] = request.body.asJson

    try {
      //obtain user input from input in search bar
      val userInput = request.body.asFormUrlEncoded.get("search-criteria")(0)
      //transform input in json
      val inputToJson = Json.obj("criteria" -> s"${userInput}")

      // #### #### Python #### ####
      //send json to API python into the body
      val sentPython = ws.url("http://localhost:5050/vectorize")
      //processing response
      sentPython.post(inputToJson).map({ response =>
        println(("response", response.json))
      })

      // #### #### Scala #### ####
      val uuid: String = java.util.UUID.randomUUID().toString
      println("Current UUID: " + uuid)
    } catch {
      case e: Exception => println("-------------------------------------Error-------------------------------------")
    }

    // todo pending to change
    jsonBody
      .map { json =>
        Ok("Got: " + (json \ "search-criteria"))
      }
      .getOrElse {
        BadRequest("Expecting application/json request body")
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
              "message" -> "Key was not finded. Resource might still be processing.",
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