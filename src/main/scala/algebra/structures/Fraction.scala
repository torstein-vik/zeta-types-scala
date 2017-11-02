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
    
    override def negation() = new Fraction(ring)(-numerator, denominator)
    
    override def equals(that : Any) : Boolean = that match {
        case _ : Fraction[T] => {
            val other = that.asInstanceOf[Fraction[T]]
            this.numerator * other.denominator == other.numerator * this.denominator
        }
        case _ => false
    }

    
    override def toString : String = {
        return numerator + "/" + denominator
    } 
}

object Rationals extends Fractions[Integer] (Integers)

case class Rational (override val numerator : Integer, override val denominator : Integer = Integers.one) extends 
    Fraction[Integer] (Integers)(numerator, denominator)