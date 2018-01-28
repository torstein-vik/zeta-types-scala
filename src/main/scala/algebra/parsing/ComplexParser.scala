package io.github.torsteinvik.zetatypes.algebra.parsing

import io.github.torsteinvik.zetatypes.algebra.structures._
import io.github.torsteinvik.zetatypes.algebra._

/** Parses an algebraic [[io.github.torsteinvik.zetatypes.algebra.structures.ComplexNumber]] from an input String */
object ComplexParser {
    import AlgebraicParser._
    /** Parses an algebraic [[io.github.torsteinvik.zetatypes.algebra.structures.ComplexNumber]] from an input String */
    def apply[T <: FieldElement[T]](element : Parser[T], ring : Field[T]) : Parser[ComplexNumber[T]] = (
        imaginary(element, ring) ^^ ((ring.zero, _)) |
        addition(element, ring) | 
        subtraction(element, ring) |
        real(element) ^^ ((_, ring.zero))
        ) ^^ {
                case (real, imag) => new ComplexNumber(real, imag)(ring)
        }
    
    private def addition[T <: FieldElement[T]](element : Parser[T], ring : Field[T]) : Parser[(T, T)] = real(element) ~ ("+" ~> imaginary(element, ring)) ^^ {
        case real ~ imag => (real, imag)
    }
    
    private def subtraction[T <: FieldElement[T]](element : Parser[T], ring : Field[T]) : Parser[(T, T)] = real(element) ~ ("-" ~> imaginary(element, ring)) ^^ {
        case real ~ imag => (real, -imag)
    }
    
    private def real[T <: FieldElement[T]](element : Parser[T]) : Parser[T] = paren(element)
    
    private def imaginary[T <: FieldElement[T]](element : Parser[T], ring : Field[T]) : Parser[T] = paren(element) <~ "i" | "i" ^^^ ring.one | "-i" ^^^ -ring.one
    
}
