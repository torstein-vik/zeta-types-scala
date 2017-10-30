package io.zetatypes.algebra.structures

import io.zetatypes.algebra._

case class Integer (x : Int) extends RingElement[Integer]{
    override def +(that : Integer) = Integer(x + that.x) 
    override def *(that : Integer) = Integer(x * that.x)
    override def negation() = Integer(-x)
    
    override def toString() = x.toString()
}

object Integers extends RingClass[Integer](Integer(0), Integer(1))