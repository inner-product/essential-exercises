package essentialexercises.section6

object ErrorHandling {
  final case class User(name: String, password: String, age: Int)

  final case class FormError(field: String, message: String)

  def stringOfLengthAtLeast(
      n: Int,
      fieldName: String,
      string: String
  ): Either[FormError, String] =
    if (string.size >= n) Right(string)
    else Left(FormError(fieldName, s"Must be at least $n characters long"))

  def integerAtLeast(
      n: Int,
      fieldName: String,
      string: String
  ): Either[FormError, Int] =
    string.toIntOption match {
      case Some(value) =>
        if (value >= n) Right(value)
        else Left(FormError(fieldName, s"Must be at least ${n}"))
      case None =>
        Left(FormError(fieldName, s"Must be an integer at least ${n}"))
    }

  def validateName(name: String): Either[FormError, String] =
    stringOfLengthAtLeast(8, "name", name)

  def validatePassword(name: String): Either[FormError, String] =
    stringOfLengthAtLeast(8, "password", name)

  def validateAge(age: String): Either[FormError, Int] =
    integerAtLeast(18, "age", age)
}
