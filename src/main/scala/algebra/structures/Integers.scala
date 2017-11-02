package org.zetatypes.algebra.structures

import org.zetatypes.algebra._

case class Integer (x : Int) extends StandardLambdaRingElement[Integer]{
    override def +(that : Integer) = Integer(x + that.x) 
    override def *(that : Integer) = Integer(x * that.x)
    override def negation() = Integer(-x)
    
    override def psi(n : Int) = this
    
    override def toString() = x.toString()
    
    override val canonicalRing = Integers
}

object Integers extends RingClass[Integer](Integer(0), Integer(1)) with StandardLambdaRing[Integer]