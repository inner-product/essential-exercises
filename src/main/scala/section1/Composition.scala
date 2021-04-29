package section1

import doodle.core._
import doodle.interact.animation._
import doodle.interact.easing.Easing
import doodle.interact.syntax._
import doodle.java2d._
import doodle.syntax._

object Composition extends App {
  // In this exercise we're interested in composition: when we combine two
  // things to create something more complex. Composition is one of the
  // fundamental ideas of functional programming. With this exercise we're
  // trying to sharpen our instinct for composition so we can find more
  // opportunities to use it in our code.
  //
  // Most interesting uses of composition are "closed", which means we get back
  // the same type of thing we started with. For example, integers are closed
  // under addition (1 + 1 = 2, which is still an integer) but not under
  // division (1 / 2 is not an integer). We're specifically looking at closed
  // composition here.
  //
  // Your Missions:
  //
  // 1. Read the code below. Run the code.
  //
  // 2. Modify the code to try the other example (you'll have to read the code
  // to find out what that is.)
  //
  // 3. Modify the code to add your personal flair. Don't take too much
  // time here but don't be afraid to get creative either.
  //
  // 4. The main abstraction is the `Transducer` type. You should be able to
  // identify at least one example of composition of transducers. What is it?
  //
  // 5. Can you think you think of other possible ways to compose transducers?
  //
  //
  // Optional Missions:
  //
  // 1. Can you modify the code to use one of the other composition methods you
  // identified in 5 above?
  //
  // 2. If you have studied theory of computation (sometimes called models of
  // computation), what model does the transducer type correspond to?
  //
  // 3. Taking one of the ways to compose transducers, can you think of a
  // transducer that would act as an identity under that operation? In other
  // words a transducer that when you compose with any other transducer does not
  // change the transducer it is composed with? (An example for integers: 1 is
  // the identity under multiplication: 1 * n = n for any integer n)
  //
  // 4. Associativity means that for some binary operation + it doesn't matter
  // where we write brackets. For example (a + b) + c = a + (b + c). If you have
  // discovered a binary composition operator for tranducers, it is associative?


  // A value that grows smoothly from 10 to 200. We'll use it as the radius of our shapes
  val growingRadius: Transducer[Double] =
    (10.0).upTo(200.0).withEasing(Easing.cubic).forSteps(240)

  // A value that moves from 0 to 360 degrees in a kinda jerky way
  val rotation: Transducer[Angle] =
    (0.degrees).upTo(360.degrees).withEasing(Easing.circle).forSteps(60)

  // Creates an appealing Bezier curve between two points
  def curve(start: Point, end: Point): PathElement = {
    import doodle.core.PathElement._
    // Define the two control points on the vector connecting the start and end points
    val vec = end - start
    val cp1 = (start + (vec * 0.3)).scaleLength(0.8)
    val cp2 = (start + (vec * 0.6)).scaleLength(1.2)

    curveTo(cp1, cp2, end)
  }

  val growingCircle =
    growingRadius
      .map(radius =>
        circle[Algebra, Drawing](radius * 2.0)
          .strokeWidth(9.0)
          .strokeColor(Color.midnightBlue)
      )

  val spinningCurvygon =
    growingRadius.product(rotation.repeat(4)).map { case (radius, angle) =>
      val pt1 = Point(radius, 0.degrees)
      val pt2 = Point(radius, 90.degrees)
      val pt3 = Point(radius, 180.degrees)
      val pt4 = Point(radius, 270.degrees)

      val curvyGon = List(
        PathElement.moveTo(pt1),
        curve(pt1, pt2),
        curve(pt2, pt3),
        curve(pt3, pt4),
        curve(pt4, pt1)
      )

      ClosedPath(curvyGon).path[Algebra, Drawing]
        .rotate(angle)
        .strokeWidth(9.0)
        .strokeColor(Color.paleTurquoise)
    }

  growingCircle.animate(Frame.size(600, 600))
  // Try commenting out growingCircle above and uncommenting spinningCurvygon
  // below
  //
  // spinningCurvygon.animate(Frame.size(600, 600))
}
