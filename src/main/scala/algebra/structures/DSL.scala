package org.zetatypes.algebra.structures

import scala.language.implicitConversions

import org.zetatypes.algebra._

package object DSL {
    implicit def intToInteger (x : Int) : Integer = Integer(x)
    implicit def intToRational (x : Int) : Rational = Rational(x)
    
    implicit def toRational(q : Fraction[Integer]) : Rational = Rational(q.numerator, q.denominator)
    implicit def toComplex(q : ComplexNumber[Fraction[Integer]]) : Complex = Complex(q.real, q.imaginary)
        
    implicit def multiplicativeMonoid[E <: RingElement[E]] (ring : Ring[E]) : Monoid[E] = ring.multiplicative
    
    implicit class RationalWithToImaginary[T](x : T)(implicit conv: T => Rational) {
        def i : Complex = Complex(Rationals.zero, conv(x))
    }
}