package scalatest

object Partition {

  /** Return the absolute value of the input. The input can positive, negative,
    * or zero. The output must be non-negative.
    */
  def abs(id: Int): Int =
    math.abs(id)

  /** Assign an Id to a Partition. Given an Id (represented by an Int) and the
    * number of partitions, assign the Id to a partition. The result is the
    * partition, a non-negative integer in the range 0 to numberOfPartitions.
    */
  def partition(id: Int, numberOfPartitions: Int): Int =
    abs(id) % numberOfPartitions
}
