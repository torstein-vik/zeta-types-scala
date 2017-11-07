package org.torsteinv.zetatypes.algebra

/** Provides instances of various algerbraic structures, such as [[ComplexNumber]]
 *  , [[Fractions]] (for any ring), and the [[Integers]]. Also has the 
 *  subpackage [[DSL]], which houses various implicit converters for simplifying
 *  syntax. Also provides type synonyms [[Rational]] = [[Fraction[Integer]]],
 *  and [[Complex]] = [[ComplexNumber]][[[Rational]]]
 */
package object structures {
    type Rational = Fraction[Integer]
    type Complex  = ComplexNumber[Rational]
}
