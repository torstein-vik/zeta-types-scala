package io.zetatypes.algebra

trait AlgebraicElement

class AlgebraicException(msg : String) extends Exception(msg)

trait Magma[T <: AlgebraicElement] {
    def combine (x : T, y : T) : T
    def times (x : T)(n : Int) = n match {
        case n if n > 0 => Seq.fill(n)(x).reduceLeft(combine(_, _))
        case _ => throw new AlgebraicException("Magma times, n must be at least 1!")
    }    
}

trait Additive[that <: Additive[that]] extends AlgebraicElement{
    def +(y : that) : that
}

trait Multiplicative[that <: Multiplicative[that]] extends AlgebraicElement {
    def *(y : that) : that
}
