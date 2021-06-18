package json

// Representation of Json as an algebraic data type
sealed trait Json
final case class JObject(fields: Map[String, Json]) extends Json
final case class JArray(values: List[Json]) extends Json
final case class JNumber(value: Double) extends Json
final case class JString(value: String) extends Json
final case class JBoolean(value: Boolean) extends Json
case object JNull extends Json

// Type class
trait JsonWriter[A] {
  def write(a: A): Json
}

// Implement type class instances to write as Json
// - String
// - Boolean
// - a List of any type that can be written as Json
//   I.e. List[A] where there is a JsonWriter[A]
// - an Option of any type that can be written as Json
//
// The below should all work
// object Example {
//   def write[A](a: A)(implicit j: JsonWriter[A]): Json =
//     j.write(a)
//
//   println(write("Hello"))
//   println(write(true))
//   println(write(List("A", "string")))
//   println(write(Option(List(true, false))))
// }
