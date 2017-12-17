package io.github.torsteinvik.zetatypes.algebra.parsing

import scala.util.parsing.combinator._

import io.github.torsteinvik.zetatypes.algebra.structures._
import io.github.torsteinvik.zetatypes.algebra._

object ComplexParser {
    private def addition[T <: RingElement[T]](element : Parser[T], ring : Ring[T]) : Parser[ComplexNumber[T]] = paren(element) ~ ("+" ~> paren(element) <~ "i") ^^ {
        case real ~ imag => new ComplexNumber(real, imag)(ring)
    }
    
    private def subtraction[T <: RingElement[T]](element : Parser[T], ring : Ring[T]) : Parser[ComplexNumber[T]] = paren(element) ~ ("-" ~> paren(element) <~ "i") ^^ {
        case real ~ imag => new ComplexNumber(real, -imag)(ring)
    }
    
    private def real[T <: RingElement[T]](element : Parser[T], ring : Ring[T]) : Parser[ComplexNumber[T]] = paren(element) ^^ {
        case real => new ComplexNumber(real, ring.zero)(ring)
    }
    
    private def imaginary[T <: RingElement[T]](element : Parser[T], ring : Ring[T]) : Parser[ComplexNumber[T]] = paren(element) <~ "i" ^^ {
        case imag => new ComplexNumber(ring.zero, imag)(ring)
    }
    
}
