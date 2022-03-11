# Cellular Automata

The goal of this exercise is to use parallelism to speed up updating a cellular automata.


## Cellular Automata

Cellular automata (CA) are a model of computation based on discrete cells interacting with their local environment. In our case the cells are arranged in a one- or two-dimensional grid. Each cell holds some state, and state changes depending on the state of the surrounding cells.

There is a runnable example in `Main.scala` which demonstrates a one-dimensional cellular automata where each cell interacts with the cell to the left and right.


## Tasks

1. Implement [Rule 110][rule-110], an elementary (one-dimensional with binary states) CA. The update rule is defined in terms of the left, current, and right cells and is given in the following table.

|-----------------|-----|-----|-----|-----|-----|-----|-----|-----|
| Current pattern | 111 | 110 | 101 | 100 | 011 | 010 | 001 | 000 |
| New state       | 0   | 1   | 1   | 0   | 1   | 1   | 1   | 0   |
|-----------------|-----|-----|-----|-----|-----|-----|-----|-----|


2. Let's speed up the cellular automata! The main loop of the cellular automata is the `mapView` method in `Grid`. The current implementation is sequential, but it doesn't have to be. The update for any given cell only depends on the current state of the CA, and so is independent of the update of all other cells. This means the updates can all be done in parallel.

To make this change we'll want `mapView` to return `IO[Grid[B]]`, and then we can use the parallelism support in `IO` to make the updates run in parallel.


3. (Optional) Implement a two-dimensional CA, such as the Game of Life.

[rule-110]: https://en.wikipedia.org/wiki/Rule_110
