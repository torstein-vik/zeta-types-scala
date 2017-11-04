package org.zetatypes.algebra.structures

import org.zetatypes.algebra._
import structures.DSL.{Rational}

case class ComplexNumbers[T <: RingElement[T]] (ring : Ring[T]) extends 
    RingClass[ComplexNumber[T]](
    new ComplexNumber(ring.zero, ring.zero)(ring), 
    new ComplexNumber(ring.one,  ring.zero)(ring))

class ComplexNumber[T <: RingElement[T]] (val real : T, val imaginary : T)(implicit val ring : Ring[T]) extends RingElement[ComplexNumber[T]]{
    
    override lazy val canonicalRing = ComplexNumbers(ring)
    
    override def +(that : ComplexNumber[T]) = new ComplexNumber(real + that.real, imaginary + that.imaginary)
    
    override def *(that : ComplexNumber[T]) = new ComplexNumber(real * that.real - imaginary * that.imaginary, real * that.imaginary + imaginary * that.real)
    
    override def negation() = new ComplexNumber(-real,-imaginary)
    
    override def equals(that : Any) : Boolean = that match {
        case _ : ComplexNumber[T] => {
            val other = that.asInstanceOf[ComplexNumber[T]]
            this.real == other.real && this.imaginary == other.imaginary
        }
        case _ => false
    }
    
    override def toString : String = {
        if (imaginary == ring.zero) {
            return real.toString
        } else if (real == ring.zero && imaginary == ring.one) {
            return "i"
        } else if (real == ring.zero) {
            return imaginary.toString + " i"
        } else {
            return real.toString + " + " + imaginary.toString + " i"
        }
    }
}

object Complex extends ComplexNumbers (Rationals) {
    def apply(real : Rational, imaginary : Rational = Rationals.zero) = new ComplexNumber(real, imaginary)(Rationals)
    def unapply(z : ComplexNumber[Rational]) : Option[(Rational, Rational)] = Some(z.real, z.imaginary)
}