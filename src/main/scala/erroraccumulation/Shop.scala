package erroraccumulation

import cats.data.NonEmptyList

object Shop {

  /** Given an order listing product name and quantity, check that each product
    * exists in the product catalogue and if so return products and quantity.
    * Otherwise return an error.
    */
  def checkCatalogue(
      order: List[(String, Int)]
  ): Either[NonEmptyList[OrderError], List[(Product, Int)]] =
    ???

  /** Given an order listing product and quantity, check that each product is
    * available in sufficent quantity in the warehouse and if so return products
    * and quantity. Otherwise return an error.
    */
  def checkWarehouse(
      order: List[(Product, Int)]
  ): Either[NonEmptyList[OrderError], List[(Product, Int)]] =
    ???

  /** Given an order listing product name and desired quantity, return the total
    * cost of the order if it can be fulfilled and other return an appropriate
    * error.
    */
  def processOrder(
      order: List[(String, Int)]
  ): Either[NonEmptyList[OrderError], Double] =
    ???
}
