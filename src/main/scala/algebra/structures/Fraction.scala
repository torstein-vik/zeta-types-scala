package org.zetatypes.algebra.structures

import org.zetatypes.algebra._

case class Fractions[T <: RingElement[T]] (ring : Ring[T]) extends 
    RingClass[Fraction[T]](
    new Fraction(ring)(ring.zero, ring.one), 
    new Fraction(ring)(ring.one,  ring.one))
    

case class Fraction[T] (numerator : T, denominator : T) extends RingElement[Fraction[T]]{
    
}