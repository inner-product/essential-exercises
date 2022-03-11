package ca

import doodle.core._
import doodle.java2d._
import doodle.syntax.all._

/** An elementary automata is a one dimensional automata with binary states */
final case class ElementaryAutomata(
    rule: (Boolean, Boolean, Boolean) => Boolean,
    grid: Grid[Boolean]
) {
  def update: ElementaryAutomata =
    ElementaryAutomata(
      rule,
      grid.mapView { view =>
        val left = view(0, -1)
        val here = view(0, 0)
        val right = view(0, 1)

        rule(left, here, right)
      }
    )

  def render: Picture[Unit] =
    grid.values
      .map(elt =>
        square[Algebra, Drawing](20).fillColor(
          if (elt) Color.black else Color.white
        )
      )
      .allBeside

  def iterate(n: Int): List[ElementaryAutomata] =
    (0.until(n)).scanLeft(this)((automata, _) => automata.update).toList

}
object ElementaryAutomata {
  val rule30 =
    ElementaryAutomata(
      (l, m, r) =>
        (l, m, r) match {
          case (true, false, false) => true
          case (false, true, true)  => true
          case (false, true, false) => true
          case (false, false, true) => true
          case _                    => false
        },
      Grid(
        1,
        19,
        Array(false, false, false, false, false, false, false, false, false,
          true, false, false, false, false, false, false, false, false, false)
      )
    )

}
