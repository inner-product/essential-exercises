package errorhandling

import munit._

class ShopSuite() extends FunSuite {
  test("checkCatalogue succeeds with valid products") {
    val allValidProducts = Catalogue.productList.map(_.name)

    assertEquals(
      Shop.checkCatalogue(allValidProducts.map(_ -> 1)),
      Right(Catalogue.productList.map(_ -> 1))
    )
  }

  test("checkCatalogue fails with invalid product") {
    assertEquals(
      Shop.checkCatalogue(List("Lingonberries" -> 100)),
      Left(ProductDoesNotExist("Lingonberries"))
    )
  }


  test("checkWarehouse succeeds with valid products and quantities") {
    val validOrder = Warehouse.inventory.toList

    assertEquals(Shop.checkWarehouse(validOrder), Right(validOrder))
  }

  test("checkWarehouse fails with invalid quantity") {
    val product = Product("Brunost", 12.99)
    assertEquals(
      Shop.checkWarehouse(List(product -> 30)),
      Left(InsufficientProductAvailable(product, 30, 3))
    )
  }


  test("processOrder succeeds on valid order"){
    assertEquals(
      Shop.processOrder(List("Peer Gynt" -> 1, "Brunost" -> 2)),
      Right(14.99 + (12.99 * 2))
    )
  }

  test("processOrder fails on invalid product"){
    assertEquals(
      Shop.processOrder(List("Cheddar" -> 3)),
      Left(ProductDoesNotExist("Cheddar"))
    )
  }

  test("processOrder fails on insufficient quantity"){
    val product = Product("Brunost", 12.99)
    assertEquals(
      Shop.processOrder(List("Peer Gynt" -> 1, product.name -> 30)),
      Left(InsufficientProductAvailable(product, 30, 3))
    )
  }
}
