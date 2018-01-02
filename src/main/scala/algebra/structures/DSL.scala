package io.github.torsteinvik.zetatypes.algebra.structures

import scala.language.implicitConversions

import io.github.torsteinvik.zetatypes.algebra._

/** Provides various methods for simplifying the syntax of working with algebra,
 *  such as converters between the main structures, the value [[DSL.I]] as the 
 *  imaginary unit, as well as various subobjects for polynomials and conversion from ring to monoid
 *  Also provides implicit rings for all rings in [[structures]] package
 */
package object DSL {
    /** Implicit instance of ring of [[Integers]] */
    implicit val integers : Ring[Integer] = Integers
    /** Implicit functor of a ring to its ring of [[Fractions]] */
    implicit def fractions[E <: RingElement[E]](implicit ring : Ring[E]) : Ring[Fraction[E]] = Fractions(ring)
    /** Implicit functor of a ring to its ring of [[ComplexNumbers]] */
    implicit def complexnumbers[E <: RingElement[E]](implicit ring : Ring[E]) : Ring[ComplexNumber[E]] = ComplexNumbers(ring)
    /** Implicit functor of a ring to the ring of [[Polynomials]] with coefficients in the input ring */
    implicit def polynomials[E <: RingElement[E]](implicit ring : Ring[E]) : Ring[Polynomial[E]] = Polynomials(ring)
    
    /** implicitly converts an int in standard syntax to an [[Integer]] */
    implicit def intToInteger  (x : Int) : Integer  = Integer(x)
    /** implicitly converts an int in standard syntax to a [[Rational]] number */
    implicit def intToRational (x : Int) : Rational = Rational(x)
    /** implicitly converts an int in standard syntax to a [[Complex]] number */
    implicit def intToComplex  (x : Int) : Complex  = Complex(x)
    
    /** implicitly converts an [[Integer]] to a [[Rational]] */
    implicit def IntegerToRational (x : Integer) : Rational = Rational(x)
    /** implicitly converts an [[Integer]] to a [[Complex]] number */
    implicit def IntegerToComplex  (x : Integer) : Complex  = Complex(x)
    
    /** implicitly converts a [[Rational]] number to a [[Complex]] number */
    implicit def RationalToComplex  (x : Rational) : Complex  = Complex(x)
    
    /** The imaginary unit, so that a [[Complex]] number can be specified like 1 + 2*I */
    def I = Complex(0, 1)
    
    /** implicitly embeds something into a [[Polynomial]] term with exponent 0
     *  @tparam S the type of x which is embedded into the [[Polynomial]]
     *  @tparam E the type of [[RingElement]] the coefficients of the [[Polynomial]] belong to
     *  @param x the 'something' which is embedded into the [[Polynomial]]
     *  @param ring the [[Ring]] the polynomial unit has coefficients in
     *  @param ev a conversion which embeds x into a [[Ring]] which is then embedded into [[Polynomials]]
     */
    implicit def intoPolynomial[S, E <: RingElement[E]](x : S)(implicit ring : Ring[E], ev : S => E) : Polynomial[E] = new Polynomial(Seq((ev(x), 0)))
    
    /** The polynomial unit, so that a [[Polynomial]] can be specified like 1 + x 
     *  @tparam E the type of [[RingElement]] the coefficients of the [[Polynomial]] belong to
     *  @param ring the [[Ring]] the polynomial unit has coefficients in
     */
    def pvar[E <: RingElement[E]](implicit ring : Ring[E]) = new Polynomial[E](Seq((ring.one, 1)))
    
    
    object IntegerPolynomial {
        def x = pvar[Integer]
    }
    
    object RationalPolynomial {
        def x = pvar[Rational]
    }
    
    object ComplexPolynomial {
        def x = pvar[Complex]
    }
    
    object Multiplicative {
        
        /** implicitly converts a [[Ring]] into its [[Ring.multiplicative]] [[Monoid]] (with implicit input) */
        implicit def implcitMultiplicativeMonoid[E <: RingElement[E]] (implicit ring : Ring[E]) : Monoid[E] = ring.multiplicative
        /** implicitly converts a [[Ring]] into its [[Ring.multiplicative]] [[Monoid]] */
        implicit def multiplicativeMonoid[E <: RingElement[E]] (ring : Ring[E]) : Monoid[E] = ring.multiplicative
    }
    
    object Additive {
        
        /** implicitly converts a [[Ring]] into its [[Ring.multiplicative]] [[Monoid]] (with implicit input) */
        implicit def implcitAdditiveMonoid[E <: RingElement[E]] (implicit ring : Ring[E]) : Monoid[E] = ring.additive
        /** implicitly converts a [[Ring]] into its [[Ring.multiplicative]] [[Monoid]] */
        implicit def additiveMonoid[E <: RingElement[E]] (ring : Ring[E]) : Monoid[E] = ring.additive
    }
}

package DSL {
    
    /** Dummy class so that scaladoc shows DSL */
    protected class DummyClass
}
