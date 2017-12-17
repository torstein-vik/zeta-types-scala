package io.github.torsteinvik.zetatypes.algebra.parsing

import scala.util.parsing.combinator._

import io.github.torsteinvik.zetatypes.algebra.structures._
import io.github.torsteinvik.zetatypes.algebra._

/** Parses an algebraic [[io.github.torsteinvik.zetatypes.algebra.structures.Polynomial]] from an input String */
object PolynomialParser {
    import AlgebraicParser._
    /** Parses an algebraic [[io.github.torsteinvik.zetatypes.algebra.structures.Polynomial]] from an input String */
    def apply[T <: RingElement[T]](element : Parser[T], ring : Ring[T]) : Parser[Polynomial[T]] = rep1sep(term(element, ring), "+") ^^ (new Polynomial(_)(ring))
    
    private def term[T <: RingElement[T]](element : Parser[T], ring : Ring[T]) : Parser[(T, Int)] = ???
}
