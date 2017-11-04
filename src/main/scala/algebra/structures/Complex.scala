package org.zetatypes.algebra.structures

import org.zetatypes.algebra._

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
    
    override def toString : String = real.toString + " + " + imaginary.toString + " i"
}

object Complex extends ComplexNumbers (Rationals)

case class Complex (override val real : Rational, override val imaginary : Rational = Rationals.zero) extends 
    ComplexNumber[Fraction[Integer]] (real, imaginary)(Rationals)
