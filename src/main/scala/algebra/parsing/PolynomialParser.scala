package io.github.torsteinvik.zetatypes.algebra.parsing

import scala.util.parsing.combinator._

import io.github.torsteinvik.zetatypes.algebra.structures._
import io.github.torsteinvik.zetatypes.algebra._

/** Parses an algebraic [[io.github.torsteinvik.zetatypes.algebra.structures.Polynomial]] from an input String */
object PolynomialParser {
    import AlgebraicParser._
    /** Parses an algebraic [[io.github.torsteinvik.zetatypes.algebra.structures.Polynomial]] from an input String */
    def apply[T <: RingElement[T]](element : Parser[T], ring : Ring[T]) : Parser[Polynomial[T]] = rep1sep(term(paren(element), ring), "+") ^^ (new Polynomial(_)(ring))
    
    private def term[T <: RingElement[T]](element : Parser[T], ring : Ring[T]) : Parser[(T, Int)] = 
        (linear(element, ring) | constant(element, ring)) |
        (nocoefflinear(ring) ||| nocoeffpower(ring)) |||
        power(element, ring)
        
    private def constant[T <: RingElement[T]](element : Parser[T], ring : Ring[T]) : Parser[(T, Int)] = element ^^ ((_, 0))
    private def linear[T <: RingElement[T]](element : Parser[T], ring : Ring[T]) : Parser[(T, Int)] = element <~ "x" ^^ ((_, 1))
    private def power[T <: RingElement[T]](element : Parser[T], ring : Ring[T]) : Parser[(T, Int)] = element ~ ("x^" ~> """\d+""".r) ^^ {
        case elem ~ exp => (elem, exp.toInt)
    }
    
    private def nocoefflinear[T <: RingElement[T]](ring : Ring[T]) : Parser[(T, Int)] = "x" ^^^ ((ring.one, 1))
    private def nocoeffpower[T <: RingElement[T]](ring : Ring[T]) : Parser[(T, Int)] = "x^" ~> """\d+""".r ^^ (str => (ring.one, str.toInt))
    
}
