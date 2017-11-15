package org.torsteinv.zetatypes.algebra.structures

import scala.language.implicitConversions

import org.torsteinv.zetatypes.algebra._

/** Provides various methods for simplifying the syntax of working with algebra,
 *  such as converters between the main structures, the value [[DSL.I]], which 
 *  is the imaginary unit, and the implicit [[DSL.multiplicativeMonoid]]
 */
package object DSL {
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
    
    implicit def intoPolynomial[S, E <: RingElement[E]](x : S)(implicit ring : Ring[E], ev : S => E) : Polynomial[E] = new Polynomial(Seq((ev(x), 0)))
    
    def x[E <: RingElement[E]](implicit ring : Ring[E]) = new Polynomial[E](Seq((ring.one, 1)))
    
    /** implicitly converts a [[Ring]] into its [[Ring.multiplicative]] [[Monoid]] */
    implicit def multiplicativeMonoid[E <: RingElement[E]] (ring : Ring[E]) : Monoid[E] = ring.multiplicative
    
    /** Dummy class so that scaladoc shows DSL */
    protected class DummyClass
}