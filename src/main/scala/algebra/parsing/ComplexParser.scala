package io.github.torsteinvik.zetatypes.algebra.parsing

import scala.util.parsing.combinator._

import io.github.torsteinvik.zetatypes.algebra.structures._
import io.github.torsteinvik.zetatypes.algebra._

/** Parses an algebraic [[io.github.torsteinvik.zetatypes.algebra.structures.ComplexNumber]] from an input String */
object ComplexParser {
    import AlgebraicParser._
    /** Parses an algebraic [[io.github.torsteinvik.zetatypes.algebra.structures.ComplexNumber]] from an input String */
    def apply[T <: RingElement[T]](element : Parser[T], ring : Ring[T]) : Parser[ComplexNumber[T]] = (
        imaginary(element, ring) |
        addition(element, ring) | 
        subtraction(element, ring) |
        real(element, ring)
        ) ^^ {
                case (real, imag) => new ComplexNumber(real, imag)(ring)
        }
    
    private def addition[T <: RingElement[T]](element : Parser[T], ring : Ring[T]) : Parser[(T, T)] = paren(element) ~ ("+" ~> paren(element) <~ "i") ^^ {
        case real ~ imag => (real, imag)
    }
    
    private def subtraction[T <: RingElement[T]](element : Parser[T], ring : Ring[T]) : Parser[(T, T)] = paren(element) ~ ("-" ~> paren(element) <~ "i") ^^ {
        case real ~ imag => (real, -imag)
    }
    
    private def real[T <: RingElement[T]](element : Parser[T], ring : Ring[T]) : Parser[(T, T)] = paren(element) ^^ {
        case real => (real, ring.zero)
    }
    
    private def imaginary[T <: RingElement[T]](element : Parser[T], ring : Ring[T]) : Parser[(T, T)] = paren(element) <~ "i" ^^ {
        case imag => (ring.zero, imag)
    }
    
}
