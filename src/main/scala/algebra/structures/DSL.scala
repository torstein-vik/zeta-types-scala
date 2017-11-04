package org.zetatypes.algebra.structures

import scala.language.implicitConversions

import org.zetatypes.algebra._

package object DSL {
    implicit def toInteger (x : Int) : Integer = Integer(x)
    
    
    implicit def toRational(q : Fraction[Integer]) : Rational = Rational(q.numerator, q.denominator)
        
    implicit def multiplicativeMonoid[E <: RingElement[E]] (ring : Ring[E]) : Monoid[E] = ring.multiplicative
}