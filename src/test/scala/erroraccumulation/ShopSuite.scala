package erroraccumulation

import cats.data.NonEmptyList
import munit._

class ShopSuite() extends FunSuite {
  test("checkCatalogue succeeds with valid products") {
    val allValidProducts = Catalogue.productList.map(_.name)

    assertEquals(
      Shop.checkCatalogue(allValidProducts.map(_ -> 1)),
      Right(Catalogue.productList.map(_ -> 1))
    )
  }

  test("checkCatalogue fails with invalid products, accumulating errors") {
    assertEquals(
      Shop.checkCatalogue(List("Lingonberries" -> 100, "Rollmops" -> 100)),
      Left(
        NonEmptyList.of(
          ProductDoesNotExist("Lingonberries"),
          ProductDoesNotExist("Rollmops")
        )
      )
    )
  }

  test("checkWarehouse succeeds with valid products and quantities") {
    val validOrder = Warehouse.inventory.toList

    assertEquals(Shop.checkWarehouse(validOrder), Right(validOrder))
  }

  test("checkWarehouse fails with invalid quantities, accumulating errors") {
    val product1 = Product("Brunost", 12.99)
    val product2 = Product("Kvikk Lunsj", 7.99)
    assertEquals(
      Shop.checkWarehouse(List(product1 -> 30, product2 -> 15)),
      Left(
        NonEmptyList.of(
          InsufficientProductAvailable(product1, 30, 3),
          InsufficientProductAvailable(product2, 15, 10)
        )
      )
    )
  }

  test("processOrder succeeds on valid order") {
    assertEquals(
      Shop.processOrder(List("Peer Gynt" -> 1, "Brunost" -> 2)),
      Right(14.99 + (12.99 * 2))
    )
  }

  test("processOrder fails on invalid products, accumulating errors") {
    assertEquals(
      Shop.processOrder(List("Cheddar" -> 3, "Stilton" -> 7)),
      Left(
        NonEmptyList.of(
          ProductDoesNotExist("Cheddar"),
          ProductDoesNotExist("Stilton")
        )
      )
    )
  }

  test("processOrder fails on insufficient quantity, accumulating errors") {
    val product1 = Product("Brunost", 12.99)
    val product2 = Product("Kvikk Lunsj", 7.99)
    assertEquals(
      Shop.processOrder(
        List("Peer Gynt" -> 1, product1.name -> 30, product2.name -> 10)
      ),
      Left(
        NonEmptyList.of(
          InsufficientProductAvailable(product1, 30, 3),
          InsufficientProductAvailable(product2, 15, 10)
        )
      )
    )
  }

  test(
    "processOrder fails on both invalid product and insufficient quantity, accumulating errors"
  ) {
    val product1 = Product("Brunost", 12.99)
    assertEquals(
      Shop.processOrder(List("Cheddar" -> 3, product1.name -> 30)),
      Left(
        NonEmptyList.of(
          ProductDoesNotExist("Cheddar"),
          InsufficientProductAvailable(product1, 30, 3)
        )
      )
    )
  }
}
