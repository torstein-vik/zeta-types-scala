package org.torsteinv.zetatypes.algebra.structures

import org.torsteinv.zetatypes.algebra._
import structures.DSL.{Rational}

case class Integer (x : BigInt) extends StandardLambdaRingElement[Integer] with PartialQAlgebraElement[Integer]{
    override def +(that : Integer) = Integer(x + that.x) 
    override def *(that : Integer) = Integer(x * that.x)
    override def negation() = Integer(-x)
    
    override def psi(n : Int) = this
    
    override def partialQMult(n : Rational) : Option[Integer] = {
        val Rational(Integer(num), Integer(den)) = n
        val (div, mod) = (num * x) /% den
        if (mod == 0) {
            return Some(Integer(div))
        } else {
            return None
        }
    }
    
    override def toString() = x.toString()
    
    override val canonicalRing = Integers
}

object Integers extends RingClass[Integer](Integer(0), Integer(1)) with StandardLambdaRing[Integer] with PartialQAlgebra[Integer]