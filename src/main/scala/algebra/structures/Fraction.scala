package io.github.torsteinvik.zetatypes.algebra.structures

import io.github.torsteinvik.zetatypes.algebra._

    
/** An element of [[Fractions]]
 *  @tparam T the type of [[RingElement]] that the numerator and denominator of this element belong to.
 *  @constructor Creates a new fraction from a numerator and a denominator
 *  @param numerator The numerator of this fraction
 *  @param denominator The denominator of this fraction
 *  @param ring The ring that the numerator and denominator belong to
 */
class Fraction[T <: RingElement[T]] (val numerator : T, val denominator : T)(implicit val ring : Ring[T]) extends FieldElement[Fraction[T]]{
    if (denominator == ring.zero) {
        throw new AlgebraicException("Denominator may not be zero!")
    }
    
    override lazy val canonicalRing = Fractions(ring)
    
    override def +(that : Fraction[T]) = new Fraction(numerator * that.denominator + that.numerator * denominator, denominator * that.denominator)
    
    override def *(that : Fraction[T]) = new Fraction(numerator * that.numerator, denominator * that.denominator)
    
    override def negation = new Fraction(-numerator, denominator)
    
    override def inverse = checkZero{new Fraction(denominator, numerator)}
    
    override def equals(that : Any) : Boolean = that match {
        case x : Fraction[T] => this.numerator * x.denominator == x.numerator * this.denominator
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

/** An object to create a [[Fraction]] with [[Integer]] parts.
 *  Also provides unapply, so that [[Rational]] numbers can be pattern matched.
 */
object Rational {   
     /** Creates a new [[Rational]] number using a numerator and denominator part as parameters */
    def apply(numerator : Integer, denominator : Integer = Integers.one) = new Fraction(numerator, denominator)(Integers)
    /** Takes a ([[Rational]]) number and extracts its numerator and denominator */
    def unapply(q : Fraction[Integer]) : Option[(Integer, Integer)] = Some((q.numerator, q.denominator))
}
