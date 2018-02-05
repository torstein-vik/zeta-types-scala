package io.github.torsteinvik.zetatypes.algebra.structures

import io.github.torsteinvik.zetatypes.algebra._

/** An int with algebraic semantics.
 *  @constructor Create a new Integer from a BigInt
 *  @param x the number that this Integer is created from
 */
case class Integer (x : BigInt) extends Element {
    override def toString() = x.toString()
}

object Integer {
    implicit object integerRing extends Ring[Integer] {
        def add (i : Integer, j : Integer) = Integer(i.x + j.x)
        def multiply (i : Integer, j : Integer) = Integer(i.x * j.x)
        
        def zero = Integer(0)
        def one = Integer(1)
        
    }
}
