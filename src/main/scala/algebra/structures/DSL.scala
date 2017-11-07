package org.torsteinv.zetatypes.algebra.structures

import scala.language.implicitConversions

import org.torsteinv.zetatypes.algebra._

/** Provides various methods for simplifying the syntax of working with algebra,
 *  such as converters between the main structures, the value [[DSL.I]], which 
 *  is the imaginary unit, and the implicit [[DSL.multiplicativeMonoid]]
 */
package object DSL {
    implicit def intToInteger  (x : Int) : Integer  = Integer(x)
    implicit def intToRational (x : Int) : Rational = Rational(x)
    implicit def intToComplex  (x : Int) : Complex  = Complex(x)
    
    implicit def IntegerToRational (x : Integer) : Rational = Rational(x)
    implicit def IntegerToComplex  (x : Integer) : Complex  = Complex(x)
    
    implicit def RationalToComplex  (x : Rational) : Complex  = Complex(x)
    
    def I = Complex(0, 1)
    
    implicit def multiplicativeMonoid[E <: RingElement[E]] (ring : Ring[E]) : Monoid[E] = ring.multiplicative
    
}