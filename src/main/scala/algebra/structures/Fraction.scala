package org.torsteinv.zetatypes.algebra.structures

import org.torsteinv.zetatypes.algebra._

package object RationalPackage extends RationalPackaging

trait RationalPackaging {

    case class Fractions[T <: RingElement[T]] (ring : Ring[T]) extends 
        RingClass[Fraction[T]](
        new Fraction(ring.zero, ring.one)(ring), 
        new Fraction(ring.one,  ring.one)(ring))

    class Fraction[T <: RingElement[T]] (val numerator : T, val denominator : T)(implicit val ring : Ring[T]) extends RingElement[Fraction[T]]{
        if (denominator == ring.zero) {
            throw new AlgebraicException("Denominator may not be zero!")
        }
        
        override lazy val canonicalRing = Fractions(ring)
        
        override def +(that : Fraction[T]) = new Fraction(numerator * that.denominator + that.numerator * denominator, denominator * that.denominator)
        
        override def *(that : Fraction[T]) = new Fraction(numerator * that.numerator, denominator * that.denominator)
        
        override def negation() = new Fraction(-numerator, denominator)
        
        override def equals(that : Any) : Boolean = that match {
            case _ : Fraction[T] => {
                val other = that.asInstanceOf[Fraction[T]]
                this.numerator * other.denominator == other.numerator * this.denominator
            }
            case _ => false
        }

        
        override def toString : String = {
            if (denominator == ring.one){
                return numerator.toString
            } else {
                return numerator + "/" + denominator
            }
        } 
    }

    object Rationals extends Fractions[Integer] (Integers)

    object Rational {
        def apply(numerator : Integer, denominator : Integer = Integers.one) = new Fraction(numerator, denominator)(Integers)
        def unapply(q : Fraction[Integer]) : Option[(Integer, Integer)] = Some(q.numerator, q.denominator)
    }

}
