package org.torsteinv.zetatypes.algebra.structures

import scala.language.implicitConversions

import org.torsteinv.zetatypes.algebra._

/** Provides various methods for simplifying the syntax of working with algebra,
 *  such as converters between the main structures, the value [[DSL.I]], which 
 *  is the imaginary unit, and the implicit [[DSL.multiplicativeMonoid]]
 */
package object DSL {
    /** implicitly converts an int in standard syntax to an Integer */
    implicit def intToInteger  (x : Int) : Integer  = Integer(x)
    /** implicitly converts an int in standard syntax to a Rational number */
    implicit def intToRational (x : Int) : Rational = Rational(x)
    /** implicitly converts an int in standard syntax to a Complex number */
    implicit def intToComplex  (x : Int) : Complex  = Complex(x)
    
    /** implicitly converts an Integer to a Rational */
    implicit def IntegerToRational (x : Integer) : Rational = Rational(x)
    /** implicitly converts an Integer to a Complex number */
    implicit def IntegerToComplex  (x : Integer) : Complex  = Complex(x)
    
    /** implicitly converts a Rational number to a Complex number */
    implicit def RationalToComplex  (x : Rational) : Complex  = Complex(x)
    
    def I = Complex(0, 1)
    
    implicit def multiplicativeMonoid[E <: RingElement[E]] (ring : Ring[E]) : Monoid[E] = ring.multiplicative
    
    /** Dummy class so that scaladoc shows DSL */
    protected class DummyClass
}