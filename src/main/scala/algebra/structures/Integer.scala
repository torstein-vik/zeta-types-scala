package io.github.torsteinvik.zetatypes.algebra.structures

import io.github.torsteinvik.zetatypes.algebra._

/** An int with algebraic semantics.
 *  @constructor Create a new Integer from a BigInt
 *  @param x the number that this Integer is created from
 */
case class Integer (x : BigInt) extends STDLambdaRingElement[Integer] with PartialQAlgebraElement[Integer]{
    
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
    
    override lazy val canonicalRing = Integers
}

/** The ring of [[Integer]]s */
object Integers extends ParsableRingClass[Integer](Integer(0), Integer(1)) with STDLambdaRing[Integer] with PartialQAlgebra[Integer] {
    import AlgebraicParser._
    def parser = """-?\d+""".r ^^ (str => Integer(BigInt(str)))
}
