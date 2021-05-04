# L-System Case Study

This case study uses algebraic data types and collection classes to implement an [L-System][lsystem].

L-systems consist of an alphabet of symbols and a set of rewrite rules that turn symbols into other symbols. They were originally developed as a simple model of biological growth. In a biological example we might have symbols representing stem, leaf, flower and fruit, and rewrite rules saying that a stem turns into two stems, a flower turns into fruit, and so on.

We're going to implement an L-system and produce some interesting graphical output.


## Mission 1: Instructions

Implement an `Instruction` data type. An `Instruction` is either:

- move forward
- alternate move forward (we need two different instructions with the same meaning)
- turn left
- turn right


## Mission 2: Rewrite Rules

A rewrite rule is a function from `Instruction` to a sequence of `Instruction`. Implement a rewrite rule with the following rules:

- move forward is rewritten to move forward, turn left, and alternate move forward;
- alternate move forward is rewritten to move forward, turn right, and alternate move forward;
- other instructions are not changed.


## Mission 3: Rewriting

Implement a method `rewrite` that given a sequence of instructions and a rewrite rule, applies the rewrite rule to the instructions and returns a sequence of instructions.


## Mission 4: Iteration

Now write a method `iterate` that is given a natural number (an integer greater than or equal to zero), a rewrite rule, and a sequence of instructions, and returns a sequence of instructions that results from applying the rewrite rule the given number of times to the instructions.


## Mission 5: Rendering

Now we're going to finish up by rendering.our instructions. The instructions already suggest graphical output. To render it we need two things:

- understand the `PathElement` data type; and
- implement turtle graphics to convert instructions to `PathElements`. 

Turtle graphics define lines in terms of the current position and direction of a "turtle". For example, if the turtle is at position (1, 1) and facing at 45 degrees, and we want to move forward 10 steps, the ending position is calculated using the code below.

``` scala
import doodle.core._  // For Point and Vec
import doodle.syntax._ // For degrees syntax

// The initial values for the turtle
val start = Point(1, 1)
val direction = 45.degrees

// The amount we've moving the turtle, specified as a vector in polar form
val displacement = Vec(10, direction)

// The final location of the turtle (note the direction is unchanged)
val end = start + displacement
```

To convert the turtle movement into graphics we need to construct a sequence of `doodle.core.PathElement`. There are methods on the `PathElement` companion object to construct path elements---`lineTo` is the one you'll want (except for a call to `moveTo` right at the start, to set the initial position). `lineTo` draws a straight line from the current location to the given point. Only moving the turtle forward will result in output. Turning changes the turtle's internal state but doesn't result in visible output.

To help you get started there is a small example in `Turtle.scala`. Run it and you should see a square appear on screen.


## Mission 6: Finale

Using the initial instruction that is just "move forward", iterate your L-system and draw the result.


## Option Missions

If you complete the missions above with time here are some additional missions you can undertake:

- Make your code as generic as you can. For example, rewrite rules can be generic in the type of instructions they work on.

- Right now the turtle is probably tied to the instructions we chose for our L-system. For greater reuse the turtle instruction set could be made independent of the L-system. The minimal turtle needs to move forward and turn. These instructions might be parameterized by the distance (for moving forward) and angle (for turns). Then we need a separate step---a compiler---to tranform our L-system instruction set into our turtle instruction set.

- If your turtle has the ability to branch you can create more interesting pictures. Branching means saving the current state of the turtle, executing some instructions, and returning to the saved state. Add branching and then try the "Fractal plant" given on the [Wikipedia page][lsystem] for L-systems.

[lsystem]: https://en.wikipedia.org/wiki/L-system
