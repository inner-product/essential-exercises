package errorhandling

sealed trait OrderError
final case class ProductDoesNotExist(name: String) extends OrderError
final case class InsufficientProductAvailable(
    product: Product,
    quantityAskedFor: Int,
    quantityAvailable: Int
) extends OrderError
