package org.zetatypes.algebra.structures

import org.zetatypes.algebra._

case class Fractions[T <: RingElement[T]] (ring : Ring[T]) extends 
    RingClass[Fraction[T]](
    new Fraction(ring)(ring.zero, ring.one), 
    new Fraction(ring)(ring.one,  ring.one))

class Fraction[T <: RingElement[T]] (val ring : Ring[T])(val numerator : T, val denominator : T = ring.one) extends RingElement[Fraction[T]]{
    if (denominator == ring.zero) {
        throw new AlgebraicException("Denominator may not be zero!")
    }
    

    
    override def toString : String = {
        return numerator + "/" + denominator
    } 
}