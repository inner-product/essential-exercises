package wordle

import scala.io.Source

object WordList {
  def read: List[String] =
    Source
      .fromFile(getClass.getResource("/wordle.txt").getFile())
      .getLines()
      .toList
}
