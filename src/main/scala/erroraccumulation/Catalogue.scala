package erroraccumulation

object Catalogue {
  val productList = List(
    Product("Peer Gynt", 14.99),
    Product("Kvikk Lunsj", 7.99),
    Product("Linie Aquavit", 25.99),
    Product("Bjorøy Damegenser", 250.00),
    Product("Salt Lakris", 8.99),
    Product("Ostehøvel", 15.99),
    Product("Brunost", 12.99)
  )

  val products = productList.map(p => (p.name -> p)).toMap

  def getProduct(name: String): Option[Product] =
    products.get(name)
}
