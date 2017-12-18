package io.github.torsteinvik.zetatypes.algebra.parsing

import io.github.torsteinvik.zetatypes.algebra.structures._

/** Parses an algebraic [[io.github.torsteinvik.zetatypes.algebra.structures.Integer]] from an input String */
object IntegerParser {
    import AlgebraicParser._
    /** Parses an algebraic [[io.github.torsteinvik.zetatypes.algebra.structures.Integer]] from an input String */
    def apply : Parser[Integer] = """-?\d+""".r ^^ (str => Integer(BigInt(str)))
}
