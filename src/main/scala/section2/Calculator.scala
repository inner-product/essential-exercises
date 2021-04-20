package essentialscala.section2

// Your mission is to implement a simple calculator
//
// Your missions:
//
// Mission the First is to implement a data structure to represent arithmetic expressions.
//
// An Expression is:
//
// - A Literal, which has a Double value
// - An Addition, which has a left and right Expression
// - A Division, which has a left and right Expression
// - A Multiplication, which has a left and right Expression
// - A Subtraction, which has a left and right Expression
//
//
// Mission the Second is to implement two "interpreters". The first interpreter
// will take an expression and produce a Double using the usual rules of
// mathematics. The second interpreter will take an expression and produce a
// String representing that expression, with correct parentheses to indicate
// precedence.
//
// Learning points:
// - Appreciate the utility of algebraic data types and structural recursion. Most
// of the code should follow from the programming strategies.
//
// - Understand how representing the expressions as data ("reifying" them)
// decouples the data representation for the functions that act on it, and
// allows us to implement different interpreters.
