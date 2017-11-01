package io.zetatypes.algebra

trait AlgebraicElement

import scala.annotation.tailrec

class AlgebraicException(msg : String) extends Exception(msg)

trait Magma[T <: AlgebraicElement] {
    def combine (x : T, y : T) : T
}

trait Additive[that <: Additive[that]] extends AlgebraicElement{
    def +(y : that) : that
    def ++(n : Int)(implicit ev: this.type <:< that) : that = n match {
        case n if n > 0 => expTailRec(n, ev(this))
        case _ => throw new AlgebraicException("Magmatic repeated addition requires n > 0")
    }
    
    @tailrec
    private def expTailRec(n : Int, acc : that) : that = {
        if (n <= 1) return acc
        return expTailRec(n - 1, this + acc)
    } 
}

trait Multiplicative[that <: Multiplicative[that]] extends AlgebraicElement {
    def *(y : that) : that
    def ^(n : Int)(implicit ev: this.type <:< that) : that = n match {
        case n if n > 0 => expTailRec(n, ev(this))
        case _ => throw new AlgebraicException("Magmatic expontiation requires n > 0")
    }
    
    @tailrec
    private def expTailRec(n : Int, acc : that) : that = {
        if (n <= 1) return acc
        return expTailRec(n - 1, this * acc)
    } 
}
