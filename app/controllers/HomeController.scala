package controllers

import javax.inject._

import models.{Value1, Value2}
import play.api._
import play.api.mvc._

/**
 * This controller creates an `Action` to handle HTTP requests to the
 * application's home page.
 */
@Singleton
class HomeController @Inject() extends Controller {
  
  def show = Action {

    val urls = Map(
      "valueAsString" -> routes.HomeController.getValueT1("foo-%2C-fo o").url,
      "valueAsClass1" -> routes.HomeController.getValueT2(Value1("foo-%2C-fO o")).url,
      "valueAsClass2" -> routes.HomeController.getValueT3(Value2("foo-%2C-fO o")).url
    )

    urls.foreach { case(k, v) =>
      Logger.info(s"name: $k value: $v")
    }

    Ok(urls.map { case (k, v) => s"$k=$v" }.mkString("\n"))
    /*
    valueAsString=/val/foo-%252C-fo%20o
    valueAsClass1=/val2/foo-%2C-fO o
    valueAsClass2=/val3/foo-%252C-fO%20o
    */
  }

  // generic controllers
  def getValueT1[T](f: T) = Action { Ok(s"got file: $f") }
  def getValueT2[T](f: T) = Action { Ok(s"got file: $f") }
  def getValueT3[T](f: T) = Action { Ok(s"got file: $f") }
  def getValueT4[T](f: T) = Action { Ok(s"got file: $f") }

}

