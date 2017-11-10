package org.torsteinv.zetatypes.algebra.structures

import org.torsteinv.zetatypes.algebra._


/** A ring where the elements are x + y*i where x and y are in some ring. An important special case is [[Complex]] = ComplexNumbers([[Rationals]])
 *  @tparam T the type of [[RingElement]] that the real and imaginary part of this ring's elements belong to.
 *  @constructor Creates a new ring of complex numbers with coefficients in some ring.
 *  @param ring The [[Ring]] that the coefficients belong to.
 */
case class ComplexNumbers[T <: RingElement[T]] (ring : Ring[T]) extends 
    RingClass[ComplexNumber[T]](
    new ComplexNumber(ring.zero, ring.zero)(ring), 
    new ComplexNumber(ring.one,  ring.zero)(ring))

/** An element of [[ComplexNumbers]]
 *  @tparam T the type of [[RingElement]] that the real and imaginary part of this element belong to.
 *  @constructor Creates a new complex number from a real and an imaginary part
 *  @param real The real part of this complex number
 *  @param imaginary The imaginary part of this complex number
 *  @param ring The ring that the real and imaginary parts belong to
 */
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
    
    override def toString : String = (real, imaginary) match {
        case (ring.zero, ring.one) => "i"
        case (x, ring.zero) => x.toString
        case (x, ring.one)  => x.toString + " + i"
        case (ring.zero, y) => y.toString + " i"
        case (x, y) => x + " + " + y + " i"
    }
}

/** An object to create a [[ComplexNumber]] with [[Rational]] coefficients.
 *  Also provides unapply, so that ComplexNumbers can be pattern matched.
 */
object Complex extends ComplexNumbers (Rationals) {
    /** Creates a new [[Rational]] [[ComplexNumber]] using a real and imaginary part as parameters */
    def apply(real : Rational, imaginary : Rational = Rationals.zero) = new ComplexNumber(real, imaginary)(Rationals)
    /** Takes a ([[Rational]]) [[ComplexNumber]] and extracts its real and imaginary parts */
    def unapply(z : ComplexNumber[Rational]) : Option[(Rational, Rational)] = Some(z.real, z.imaginary)
}
