package erroraccumulation

import scala.collection.mutable

object Warehouse {
  val inventory = mutable.Map(
    Product("Peer Gynt", 14.99) -> 2,
    Product("Kvikk Lunsj", 7.99) -> 10,
    Product("Linie Aquavit", 25.99) -> 1,
    Product("Bjorøy Damegenser", 250.00) -> 0,
    Product("Salt Lakris", 8.99) -> 7,
    Product("Ostehøvel", 15.99) -> 2,
    Product("Brunost", 12.99) -> 3
  )

  /** True if a product is available in the warehouse */
  def available(product: Product, quantity: Int): Boolean =
    inventory.get(product).map(_ >= quantity).getOrElse(false)
}
