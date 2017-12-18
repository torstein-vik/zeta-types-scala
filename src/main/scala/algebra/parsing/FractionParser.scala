package io.github.torsteinvik.zetatypes.algebra.parsing

import io.github.torsteinvik.zetatypes.algebra.structures._
import io.github.torsteinvik.zetatypes.algebra._

/** Parses an algebraic [[io.github.torsteinvik.zetatypes.algebra.structures.Fraction]] from an input String */
object FractionParser {
    import AlgebraicParser._
    /** Parses an algebraic [[io.github.torsteinvik.zetatypes.algebra.structures.Fraction]] from an input String */
    def apply[T <: RingElement[T]](element : Parser[T], ring : Ring[T]) : Parser[Fraction[T]] = fraction(element, ring) | (element ^^ (new Fraction(_, ring.one)(ring)))
    
    private def fraction[T <: RingElement[T]](element : Parser[T], ring : Ring[T]) : Parser[Fraction[T]] = paren(element) ~ ("/" ~> paren(element)) ^^ {
        case numerator ~ denominator => new Fraction(numerator, denominator)(ring)
    }
}
