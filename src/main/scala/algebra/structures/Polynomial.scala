package org.torsteinv.zetatypes.algebra.structures

import scala.annotation.tailrec

import org.torsteinv.zetatypes.algebra._

/** A ring where the elements are [[Polynomial]]s with elements in some given [[org.torsteinv.zetatypes.algebra.Ring]] 
 *  @tparam E the type of [[org.torsteinv.zetatypes.algebra.RingElement]] that the [[Polynomial]]s consist of
 *  @constructor Creates a new ring of [[Polynomial]]s with coefficients in a given [[org.torsteinv.zetatypes.algebra.Ring]] 
 *  @param ring The [[org.torsteinv.zetatypes.algebra.Ring]] that the [[Polynomial]] coefficients belong to
 */
case class Polynomials[E <: RingElement[E]](ring : Ring[E]) extends 
    RingClass[Polynomial[E]](
    new Polynomial(Seq.empty)(ring), 
    new Polynomial(Seq((ring.one, 0)))(ring))


/** An element of [[Polynomials]]
 *  @tparam E the type of [[org.torsteinv.zetatypes.algebra.RingElement]] that the coefficients of this polynomial belong to
 *  @constructor Creates a new Polynomial from a list of terms (coefficient and exponent)
 *  @param elements The coefficient-exponent pairs that build up the terms of this polynomial
 *  @param ring The [[org.torsteinv.zetatypes.algebra.Ring]] that the coefficients belong to
 */
class Polynomial[E <: RingElement[E]] (val elements : Seq[(E, Int)])(implicit ring : Ring[E]) extends 
    RingElement[Polynomial[E]] {
    override lazy val canonicalRing = Polynomials(ring)
        
    override def +(that : Polynomial[E]) = new Polynomial(elements ++ that.elements).cleanup 
    
    override def *(that : Polynomial[E]) = new Polynomial(for ((c, e) <- elements; (d, f) <- that.elements) yield (ring.multiplicative.combine(c, d), e + f)).cleanup
    
    override def negation() = new Polynomial(elements.map({case (c, e) => (-c, e)}))
    
    override def equals(that : Any) : Boolean = that match {
        case _ : Polynomial[E] => {
            (this - that.asInstanceOf[Polynomial[E]]).elements.length == 0
        }
        
        case _ => false
    }
    
    
    override def toString : String = if (elements.length == 0) ring.zero.toString else (for {x <- cleanup.elements} yield x match {
        case (c, 0) => c.toString
        case (ring.one, 1) => "x"
        case (c, 1) => c.toString + "x"
        case (ring.one, n) => "x^" + n
        case (c, n) => c.toString + "x^" + n
    }).mkString(" + ")
    
    // TODO: Optimize by combining sorting and combination
    /** Returns a cleaned-up version of this [[Polynomial]], where coefficient-exponent pairs have been combined as much as possible,
     *  and those pairs where the coefficient is zero, have been removed. It is also sorted according to the coefficient
     */
    def cleanup : Polynomial[E] = {
        import scala.collection.mutable.{Seq => MSeq}
        
        var data : MSeq[(E, Int)] = MSeq()
        
        @tailrec
        def add(c : E, e : Int, index : Int) : Unit = {
            if (index >= data.length){
                data = data ++ Seq((c, e))
            } else {
                val (d, f) = data(index) 
                if (e == f) {
                    data(index) = (ring.additive.combine(c, d), e)
                } else {
                    add(c, e, index + 1)
                }
            }
        }
        
        elements.foreach {case (c, e) => add(c, e, 0)}
        return new Polynomial(data.toSeq.filter({case (c, n) => c != ring.zero}).sortWith(_._2 < _._2))
    }
    
}
