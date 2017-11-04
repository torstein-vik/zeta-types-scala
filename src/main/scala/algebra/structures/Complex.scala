package org.zetatypes.algebra.structures

import org.zetatypes.algebra._

case class ComplexNumbers[T <: RingElement[T]] (ring : Ring[T]) extends 
    RingClass[ComplexNumber[T]](
    new ComplexNumber(ring)(ring.zero, ring.zero), 
    new ComplexNumber(ring)(ring.one,  ring.zero))

class ComplexNumber[T <: RingElement[T]] (val ring : Ring[T])(val real : T, val imaginary : T = ring.zero) extends RingElement[ComplexNumber[T]]{
    
    override lazy val canonicalRing = ComplexNumbers(ring)
    
}

object Complex extends ComplexNumbers (Rationals)

case class Complex (override val real : Rational, override val imaginary : Rational = Rationals.zero) extends 
    ComplexNumber (Rationals)(real, imaginary)
