package io.zetatypes.algebra

trait Monoid[T <: MonoidElement] {
    def identity : T
    def combine (x : T, y : T) : T
}

trait GeneralAdditive[that, output] {
    def +(that : that) : output
}

trait GeneralMultiplicative[that, output] {
    def *(that : that) : output
}

trait Additive[that <: Additive[that]] extends GeneralAdditive[that, that] with MonoidElement
trait Multiplicative[that <: Multiplicative[that]] extends GeneralMultiplicative[that, that] with MonoidElement

trait AdditiveMonoid[T <: Additive[T]] extends Monoid[T] {
    def zero : T = identity
    override def combine (x : T, y : T) : T = x + y
}

trait MultiplicativeMonoid[T <: Multiplicative[T]] extends Monoid[T] {
    def one : T = identity
    override def combine (x : T, y : T) : T = x * y
}

trait MonoidElement
