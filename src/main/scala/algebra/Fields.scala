package io.github.torsteinvik.zetatypes.algebra

trait FieldElement[T <: FieldElement[T]] extends GroupElement with RingElement[T] with Divisible[T] {
}


