package io.zetatypes.algebra

trait RingElement[T <: MonoidElement with Additive[T] with Multiplicative[T]] extends MonoidElement with Additive[T] with Multiplicative[T]

trait Ring[E <: RingElement[E]] {
    val additive       : AdditiveMonoid[E]
    val multiplicative : MultiplicativeMonoid[E]
}

// TODO: Fix name
abstract class RingClass[E <: RingElement[E]] (val zero : E, val one : E) extends Ring[E]{ outer =>
    override object additive extends AdditiveMonoid[E] {
        def identity = outer.zero
    }
    
    override object multiplicative extends MultiplicativeMonoid[E] {
        def identity = outer.one
    }
}