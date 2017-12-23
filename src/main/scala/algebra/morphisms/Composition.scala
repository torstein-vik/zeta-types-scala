package io.github.torsteinvik.zetatypes.algebra.morphisms

import io.github.torsteinvik.zetatypes.algebra._

private[morphisms] class Composition[A <: AlgebraicElement, B <: AlgebraicElement, C <: AlgebraicElement]
    (f : Homomorphism[B, C], g : Homomorphism[A, B]) extends Homomorphism[A, C] {
}
object Composition {
}
