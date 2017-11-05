package org.torsteinv.zetatypes.algebra.structures

import scala.language.implicitConversions

import org.torsteinv.zetatypes.algebra._

package object DSL extends DSLPackagingCommon with DSLPackaging

trait DSLPackagingCommon {
    type Rational = Fraction[Integer]
    type Complex  = ComplexNumber[Rational]
}

trait DSLPackaging {
    implicit def intToInteger  (x : Int) : Integer  = Integer(x)
    implicit def intToRational (x : Int) : Rational = Rational(x)
    implicit def intToComplex  (x : Int) : Complex  = Complex(x)
    
    implicit def IntegerToRational (x : Integer) : Rational = Rational(x)
    implicit def IntegerToComplex  (x : Integer) : Complex  = Complex(x)
    
    implicit def RationalToComplex  (x : Rational) : Complex  = Complex(x)
    
    def I = Complex(0, 1)
    
    implicit def toRational(q : Fraction[Integer]) : Rational = Rational(q.numerator, q.denominator)
    implicit def toComplex(q : ComplexNumber[Fraction[Integer]]) : Complex = Complex(q.real, q.imaginary)
        
    implicit def multiplicativeMonoid[E <: RingElement[E]] (ring : Ring[E]) : Monoid[E] = ring.multiplicative
    
}