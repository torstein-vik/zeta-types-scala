package io.github.torsteinvik.zetatypes.algebra

import io.github.torsteinvik.zetatypes.algebra.structures._

/** Provides implicit parsers of various algerbraic structures, such as [[ComplexNumber]]
 *  , [[Fractions]] (for any ring), and the [[Integers]]. Also has the 
 *  subpackage [[DSL]], which houses various implicit converters for simplifying
 *  syntax. Also provides type synonyms [[Rational]] = [[Fraction]] of [[Integer]],
 *  and [[Complex]] = [[ComplexNumber]] of [[Rational]]
 *  @author Torstein Vik
 */
package object parsing {
    /** Synonym for [[AlgebraicParser.parse]] */
    def parse[T <: AlgebraicElement] (s : String)(implicit parser : AlgebraicParser.Parser[T]) = AlgebraicParser.parse(s)(parser)
    
    implicit def integerparser : AlgebraicParser.Parser[Integer] = ???
    implicit def fractionparser[T <: RingElement[T]] : AlgebraicParser.Parser[Fraction[T]] = ???
    implicit def complexparser[T <: RingElement[T]] : AlgebraicParser.Parser[ComplexNumber[T]] = ???
    
}
