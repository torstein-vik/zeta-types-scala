package io.zetatypes.algebra

trait AlgebraicElement

class AlgebraicException(msg : String) extends Exception(msg)

trait Magma[T <: AlgebraicElement] {
    def combine (x : T, y : T) : T
}

trait Additive[that <: Additive[that]] extends AlgebraicElement{
    def +(y : that) : that
}

trait Multiplicative[that <: Multiplicative[that]] extends AlgebraicElement {
    def *(y : that) : that
}
