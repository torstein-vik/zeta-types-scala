package org.zetatypes.algebra.structures

import org.zetatypes.algebra._

case class Fractions[T] (ring : Ring[T]) extends RingClass[Fraction[T]](Fraction(ring.zero, ring.one), Fraction(ring.one, ring.one)) {
    
}

case class Fraction[T] (numerator : T, denominator : T) extends RingElement[Fraction[T]]{
    
}