package io.github.torsteinvik.zetatypes.algebra

import io.github.torsteinvik.zetatypes.algebra.structures._
import io.github.torsteinvik.zetatypes.tannakiansymbols._

/** Provides implicit parsers of various algerbraic structures, such as [[io.github.torsteinvik.zetatypes.algebra.structures.ComplexNumbers]]
 *  , [[io.github.torsteinvik.zetatypes.algebra.structures.Fractions]] (for any ring), and the [[io.github.torsteinvik.zetatypes.algebra.structures.Integers]]. 
 *  @author Torstein Vik
 */
package object parsing {
    /** Synonym for [[AlgebraicParser.parse[T](str:String)*]] */
    def parse[T <: AlgebraicElement] (s : String)(implicit parser : AlgebraicParser.Parser[T]) = AlgebraicParser.parse(s)(parser)

    /** Implicit synonym for [[IntegerParser]]*/
    implicit def integerparser : AlgebraicParser.Parser[Integer] = IntegerParser.apply
    /** Implicit synonym for [[FractionParser]]*/
    implicit def fractionparser[T <: RingElement[T]](implicit element : AlgebraicParser.Parser[T], ring : Ring[T]) : AlgebraicParser.Parser[Fraction[T]] = FractionParser.apply(element, ring)
    /** Implicit synonym for [[ComplexParser]]*/
    implicit def complexparser[T <: FieldElement[T]](implicit element : AlgebraicParser.Parser[T], ring : Field[T]) : AlgebraicParser.Parser[ComplexNumber[T]] = ComplexParser.apply(element, ring)
    /** Implicit synonym for [[PolynomialParser]]*/
    implicit def polynomialparser[T <: RingElement[T]](implicit element : AlgebraicParser.Parser[T], ring : Ring[T]) : AlgebraicParser.Parser[Polynomial[T]] = PolynomialParser.apply(element, ring)
    /** Implicit synonym for [[TannakianSymbolParser]]*/
    implicit def tannakiansymbolparser[T <: MonoidElement](implicit element : AlgebraicParser.Parser[T], monoid : Monoid[T]) : AlgebraicParser.Parser[TannakianSymbol[T]] = TannakianSymbolParser.apply(element, monoid)
    
}
