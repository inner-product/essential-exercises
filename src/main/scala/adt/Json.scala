package adt

// Your mission is to implement a data type to represent JSON.
//
// If you know about the built-in collection types such as List and Map it's ok
// to use them as part of your model.
//
// Need some help? At json.org you'll find a "railway diagram" that defines the
// syntax of JSON. If you look past the syntax you'll find the data model is
// described there as well.
//
// If you complete this, and still have time, implement a method called `print`
// that prints your JSON data structure to a `String` as valid (textual) JSON.
sealed trait Json {
  import Json._

  def print: String =
    this match {
      case JObject(elts) =>
        elts.map { case (k, v) => s"$k: ${v.print}" }.mkString("{", ",", "}")
      case JArray(elts)    => elts.map(_.print).mkString("[", ",", "]")
      case JString(value)  => s""""${value}""""
      case JNumber(value)  => value.toString
      case JBoolean(value) => value.toString
      case JNull           => "null"
    }
}
object Json {
  // To avoid polluting the name space I've put all the leaves of the algebraic data type in here
  final case class JObject(elts: Map[String, Json]) extends Json
  final case class JArray(elts: List[Json]) extends Json
  final case class JString(value: String) extends Json
  final case class JNumber(value: Double) extends Json
  final case class JBoolean(value: Boolean) extends Json
  case object JNull extends Json

  // Constructors
  val jNull: Json = JNull
  val jTrue: Json = JBoolean(true)
  val jFalse: Json = JBoolean(false)
  def boolean(value: Boolean): Json = if (value) jTrue else jFalse
  def number(value: Double): Json = JNumber(value)
  def string(value: String): Json = JString(value)
  def array(values: Json*): Json = JArray(values.toList)
  def jObject(values: (String, Json)*): Json = JObject(values.toMap)
}

object JsonExample extends App {
  val json = Json.jObject(
    "name" -> Json.string("Nils Olav III"),
    "rank" -> Json.string("Brigadier"),
    "knighted" -> Json.jTrue,
    "see-also" -> Json.array(
      Json.string("William Windsor"),
      Json.string("Wojtek")
    )
  )

  println(json.print)
}
