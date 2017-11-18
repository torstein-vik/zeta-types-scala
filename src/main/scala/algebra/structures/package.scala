package org.torsteinv.zetatypes.algebra

/** Provides instances of various algerbraic structures, such as [[ComplexNumber]]
 *  , [[Fractions]] (for any ring), and the [[Integers]]. Also has the 
 *  subpackage [[DSL]], which houses various implicit converters for simplifying
 *  syntax. Also provides type synonyms [[Rational]] = [[Fraction]] of [[Integer]],
 *  and [[Complex]] = [[ComplexNumber]] of [[Rational]]
 *  @author Torstein Vik
 */
package object structures {
    /** Type synonym for rational numbers */
    type Rational = Fraction[Integer]
    
    /** Type synonym for Complex numbers with rational coefficients */
    type Complex  = ComplexNumber[Rational]
}
