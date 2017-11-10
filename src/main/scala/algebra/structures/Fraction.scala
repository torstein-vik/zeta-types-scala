package org.torsteinv.zetatypes.algebra.structures

import org.torsteinv.zetatypes.algebra._

/** A ring where the elements are x / y where x and y are in some ring and y is not zero. 
 *  An important special case is [[Rationals]] = Fractions([[Integers]])
 *  @tparam T the type of [[RingElement]] that the numerator and denominator of this ring's elements belong to.
 *  @constructor Creates a new ring of fractions with coefficients in some ring.
 *  @param ring The [[Ring]] that the numerator and denominator belong to.
 */
case class Fractions[T <: RingElement[T]] (ring : Ring[T]) extends 
    RingClass[Fraction[T]](
    new Fraction(ring.zero, ring.one)(ring), 
    new Fraction(ring.one,  ring.one)(ring))
    
/** An element of [[Fractions]]
 *  @tparam T the type of [[RingElement]] that the numerator and denominator of this element belong to.
 *  @constructor Creates a new fraction from a numerator and a denominator
 *  @param numerator The numerator of this fraction
 *  @param denominator The denominator of this fraction
 *  @param ring The ring that the numerator and denominator belong to
 */
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
/** A ring that [[Fractions]]s with [[Rational]] numerator and denominator belong to.
 */
object Rationals extends Fractions[Integer] (Integers)

/** An object to create a [[Fraction]] with [[Integer]] parts.
 *  Also provides unapply, so that [[Rational]] numbers can be pattern matched.
 */
object Rational {   
     /** Creates a new [[Rational]] number using a numerator and denominator part as parameters */
    def apply(numerator : Integer, denominator : Integer = Integers.one) = new Fraction(numerator, denominator)(Integers)
    /** Takes a ([[Rational]]) number and extracts its numerator and denominator */
    def unapply(q : Fraction[Integer]) : Option[(Integer, Integer)] = Some(q.numerator, q.denominator)
}
