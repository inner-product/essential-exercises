# Map-Reduce Case Study

In this case study we'll implement a few different versions of map-reduce. Map-reduce, in the form of Hadoop, was one of the first widely available parallel data processing architectures. More expressive systems, such as Spark, have largely replaced map-reduce, but it still captures a lot of the core concepts and it's particularly easy to implement a toy system---which is exactly what we'll do here.

There are a few different versions of map-reduce we can implement (exactly which ones we'll be doing will be decided by your instructor). Since the main purpose of parallel processing is to speed up computation we also have benchmarks that you can use to optimize your implementation, and to compare different implementations.


## Map Reduce

The idea behind map-reduce is that the key operations in a lot of parallel jobs are:

- map, an `A => B` operation, which should be familiar from Scala's collection classes; and
- reduce, a `(B, B) => B` operations---in other words an operation to combine elements.

In our implementation we also require an element of type `B` in case we're operating on an empty data set. We also represent the data as an `Array[A]`, instead of a more abstract type, so you can squeeze out maximum performance if you so desire.


## Running Benchmarks

Change to the `benchmarks` project in sbt (use the command `project benchmarks`) and then run `Jmh / run`. To run just a single benchmark you can use `Jmh / run TheBenchmarkName`, replacing `TheBenchmarkName` with the name of the class containing the benchmark (for example `MapReduceBenchmark`).

Each benchmark file contains two benchmarks: one "small" and one "medium". You might want to comment out the mediums benchmarks while you're developing, and then uncomment medium and comment out the small benchmark when you want more accurate performance figures.

## Mission One: Basic Map-Reduce

To get warmed up, implement the basic map-reduce in `MapReduce.scala`. This version doesn't involve any parallelism and as such provides baseline results.

(You can pretend the `ClassTag` doesn't exist, and delete it if the compiler complains that it is not used in your implementation.)


## Mission Two: Map-Reduce from the Future!

Use `Futures` to speed-up map-reduce. Implement `MapReduceFuture.scala`. You might find it easier to implement this if you implement `mapReduce` and `parallelMap` first, but this is your choice. You might need to add a `ClassTag`, as in `MapReduce.scala`, if the compiler tells you that one is missing.

How much of a speed-up can you get relative to non-parallel version you implemented in Mission One?

Pro-tip: you might find it useful to use `Future.sequence`, which transforms a `F[Future[A]]` to `Future[F[A]]`, where `F` is some container type such as `List`.


## Mission Three: Map-Reduce from the IO!

Use `IO` to implement a parallel map-reduce, as in Mission Two. The code is in `MapReduceIO.scala`.


## Mission Four: Error-Handling

How does error handling work in your implementation of map-reduce? I.e. what happens if either the map or reduce function causes an error. Is this good? What error handling strategy would be improve this, if any? Can you implement it?
