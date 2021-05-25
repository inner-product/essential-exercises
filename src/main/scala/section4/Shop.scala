package essentialexercises.section4

object Shop {
  // map from product name to price
  val products: Map[String, Double] =
    Map(
      "Apples" -> 0.99,
      "Blueberries" -> 3.99,
      "Lettuce" -> 0.49,
      "Lentils" -> 1.50,
      "Potatoes" -> 0.25
    )

  // map from product name to quantity available
  val stock: Map[String, Int] =
    Map(
      "Apples" -> 10,
      "Blueberries" -> 0,
      "Lettuce" -> 24,
      "Lentils" -> 5,
      "Potatoes" -> 32
    )

  // Returns the price of the product, if we sell it
  def price(product: String): Option[Double] =
    ???

  // True if we sell the product and it is in stock. False otherwise.
  def available(product: String): Boolean =
    ???

  // True if we sell all the products and they are in stock
  def allAvailable(products: List[String]): Boolean =
    ???

  // If we sell all the products and they are available return the total cost,
  // otherwise return None
  def totalPrice(products: List[String]): Option[Double] =
    ???
}
