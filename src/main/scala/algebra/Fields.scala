package io.github.torsteinvik.zetatypes.algebra

trait FieldElement[T <: FieldElement[T]] extends GroupElement with RingElement[T] with Divisible[T] {
}

trait Field[E <: FieldElement[E]] extends Ring[E] {
    
}

