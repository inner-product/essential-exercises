package adt

import doodle.core._
import doodle.interact.animation._
import doodle.java2d._
import doodle.syntax._

object Fireworks extends App {

  // A Firework is either
  //
  // A Rocket, which has
  // - a position of type Point
  // - a velocity of type Vec
  // - a lifeSpan of type Double
  //
  // OR
  //
  // An Explosion, which has
  // - a position of type Point
  // - a size of type Double
  // - a lifespan of type Double
  //
  // Implement this, replacing the type declaration below
  sealed trait Firework
  final case class Rocket(position: Point, velocity: Vec, lifeSpan: Double)
      extends Firework
  final case class Explosion(position: Point, size: Double, lifeSpan: Double)
      extends Firework

  // Gravity drags you down
  val gravity = Vec(0.0, -0.5)

  // True if this life is over and we should move on to the next stage
  def isFinished(lifeSpan: Double): Boolean = lifeSpan < 0.001

  object Interpreter {
    // Implement this method:
    //
    // If the firework is a rocket and is finished, return Some(explosion)
    // where explosion is an explosion at the same position as the firework,
    // with a size of 10.0 and lifespan of 1.0
    //
    // Otherwise, create a new rocket with:
    // - an updated position that is the old position plus the velocity
    // - a velocity that is the old velocity plus gravity
    // - a lifespan decreased by multiplying the lifespan by a fraction close
    // to 1.0 (try 0.9 to start)
    // Return Some(the new rocket)
    //
    // If the firework is an explosion and is finished, return None
    // Otherwise, create a new explosion with:
    // - the same position
    // - a size that is the previous size multiplied by some fraction greater
    // than 1.0 (try 1.1 to start)
    // - a lifespan decreased by multiplying the lifespan by a fraction close
    // to 1.0 (try 0.9 to start)
    // Return Some(the new explosion)
    //
    // The code should be much shorter than this description :-D
    def transition(firework: Firework): Option[Firework] =
      firework match {
        case Rocket(position, velocity, lifeSpan) =>
          if (isFinished(lifeSpan)) Some(Explosion(position, 10.0, 1.0))
          else
            Some(
              Rocket(position + velocity, velocity + gravity, lifeSpan * 0.9)
            )

        case Explosion(position, size, lifeSpan) =>
          if (isFinished(lifeSpan)) None
          else Some(Explosion(position, size * 1.1, lifeSpan * 0.9))
      }
  }

  object Renderer {
    // Implement this method using drawRocket and drawExplosion below
    def draw(firework: Firework): Picture[Unit] =
      firework match {
        case Rocket(position, _, _) => drawRocket(position)
        case Explosion(position, size, lifeSpan) =>
          drawExplosion(position, size, lifeSpan)
      }

    def drawRocket(position: Point): Picture[Unit] =
      circle(5.0).noStroke
        .fillColor(Color.gold)
        .on(circle(6.0).fillColor(Color.goldenrod))
        .on(circle(7.0).fillColor(Color.darkGoldenrod))
        .scale(0.75, 1.25)
        .at(position)

    def drawExplosion(
        position: Point,
        size: Double,
        lifeSpan: Double
    ): Picture[Unit] =
      circle(size).noFill
        .strokeColor(Color.hotpink.alpha(lifeSpan.normalized))
        .strokeWidth(9.0)
        .at(position)
  }

  // Implement this as a Rocket starting at Point(0, -300), with initial
  // velocity Vec(0, 25.0) and lifespan 1.0
  val start: Firework = Rocket(Point(0, -300), Vec(0, 25.0), 1.0)

  val animation: Transducer[Picture[Unit]] =
    new Transducer[Picture[Unit]] {
      type State = Option[Firework]

      val initial = Some(start)

      def next(state: State): State =
        state.flatMap(Interpreter.transition _)

      def output(state: State): Picture[Unit] =
        state.map(Renderer.draw _).getOrElse(empty)

      def stopped(state: State): Boolean =
        state.isEmpty
    }

  animation
    .repeat(5)
    .animate(Frame.size(400, 800).background(Color.midnightBlue))
}
