package models

import play.api.mvc.PathBindable

case class Value1(value: String) extends AnyVal

object Value1 {

  implicit def pathBinder(implicit strBinder: PathBindable[String]) = new PathBinder[Value1](Value1.apply, _.value)
}

case class Value2(value: String) extends AnyVal

object Value2 {

  import play.core.routing.dynamicString

  implicit def urlBinder = new PathBinder[Value2](
    Value2.apply,
    cc => dynamicString(cc.value)
  )
}

class PathBinder[T](bind: String => T, unbind: T => String)(implicit strBinder: PathBindable[String]) extends PathBindable[T] {
  override def bind(key: String, value: String): Either[String, T] = {
    strBinder.bind(key, value).right.map(e => bind(e))
  }

  override def unbind(key: String, value: T): String = {
    unbind(value)
  }
}
