package io.github.torsteinvik.zetatypes.algebra

trait FieldElement[T <: FieldElement[T]] extends GroupElement with RingElement[T] with Divisible[T] {
    override val canonicalRing : Field[T]
    
}

trait Field[E <: FieldElement[E]] extends Ring[E] {
    override val multiplicative : MultiplicativeGroup[E]
    
}

abstract class FieldClass[E <: FieldElement[E]] (override val zero : E, override val one : E) extends Field[E]{ outer =>
    override object additive extends AdditiveGroup[E] {
        def identity = outer.zero
    }
    
    override object multiplicative extends MultiplicativeGroup[E] {
        def identity = outer.one
    }
}
