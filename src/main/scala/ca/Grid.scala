package ca

import scala.reflect.ClassTag

/** Two-dimensional grid that supports wrap-around indexing. For example, an
  * index of -1 will wrap around to the end of the row or column.
  */
final case class Grid[A](rows: Int, cols: Int, data: Array[A]) {

  /** Get the element at the given row and column. */
  def apply(r: Int, c: Int): A = {
    def computeIndex(idx: Int, max: Int): Int = {
      val normalized = idx % max
      if (normalized < 0) normalized + max else normalized
    }

    val normalizedR = computeIndex(r, rows)
    val normalizedC = computeIndex(c, cols)

    val offset = normalizedR * cols + normalizedC
    data(offset)
  }

  /** Transform the cells based on a function from the surrounding cells. */
  def mapView[B: ClassTag](f: GridView[A] => B): Grid[B] = {
    val result: Array[B] = Array.ofDim(data.size)
    var r = 0
    while (r < rows) {
      var c = 0
      while (c < cols) {
        result(r * cols + c) = f(GridView(r, c, this))

        c = c + 1
      }
      r = r + 1
    }

    Grid(rows, cols, result)
  }

  /** Get the values in the cells. */
  def values: List[A] =
    data.toList

}

/** Provides a view into a grid that is centered on the current cell. So, for
  * example, the point (0, -1) is the cell to the left of the current cell
  * (remember that indexing is done based on row and column, not x and y.)
  */
final case class GridView[A](row: Int, col: Int, grid: Grid[A]) {
  def apply(r: Int, c: Int): A =
    grid(row + r, col + c)
}
