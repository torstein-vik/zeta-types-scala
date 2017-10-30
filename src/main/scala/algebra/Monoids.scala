package io.zetatypes.algebra

trait Monoid[T <: MonoidElement] extends Magma[T]{
    def identity : T
}

trait AdditiveMonoid[T <: Additive[T]] extends Monoid[T] {
    def zero : T = identity
    override def combine (x : T, y : T) : T = x + y
}

trait MultiplicativeMonoid[T <: Multiplicative[T]] extends Monoid[T] {
    def one : T = identity
    override def combine (x : T, y : T) : T = x * y
}

trait MonoidElement extends AlgebraicElement
