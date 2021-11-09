package typeclasses

// A representation of Json as an algebraic data type
sealed trait Json
case object JNull extends Json
final case class JBoolean(value: Boolean) extends Json
final case class JString(value: String) extends Json
final case class JNumber(value: Double) extends Json
final case class JObject(value: Map[String, Json]) extends Json
final case class JArray(value: List[Json]) extends Json

// Implement a type class for writing a type A to Json
//
// What language feature implements a type class in Scala?

final case class Product(name: String, description: String, price: Double)
final case class Order(items: List[Product])
// Implement type class instances to write Product and Order to Json. The Json
// representation is not particularly important but at least make it sensible.
//
// What Scala language feature implements a type class instance?

// Implement syntax `toJson` that converts any type to Json that has a Json writer instance.

// Implement a type class instance that writes a List of any type that can be be
// written to Json.

// Implement a type class instance that writes an Option of any type that can be be
// written to Json.
