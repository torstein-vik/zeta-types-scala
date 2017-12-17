package io.github.torsteinvik.zetatypes.algebra.parsing

import scala.util.parsing.combinator._

import io.github.torsteinvik.zetatypes.algebra.structures._
import io.github.torsteinvik.zetatypes.algebra._

object PolynomialParser {
    import AlgebraicParser._
    def apply[T <: RingElement[T]](element : Parser[T], ring : Ring[T]) : Parser[Polynomial[T]] = ???
    
    
}
