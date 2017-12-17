package io.github.torsteinvik.zetatypes.algebra.parsing

import scala.util.parsing.combinator._

import io.github.torsteinvik.zetatypes.algebra.structures._

/** Parses an algebraic [[io.github.torsteinvik.zetatypes.algebra.structures.Integer]] from an input String */
object IntegerParser{
    import AlgebraicParser._
    /** Parses an algebraic [[io.github.torsteinvik.zetatypes.algebra.structures.Integer]] from an input String */
    def apply = """-?\d+""".r ^^ (str => Integer(BigInt(str)))
}
