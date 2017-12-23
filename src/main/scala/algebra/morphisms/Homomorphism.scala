package io.github.torsteinvik.zetatypes.algebra.morphisms

import io.github.torsteinvik.zetatypes.algebra._

trait Homomorphism[-A <: AlgebraicElement, +B <: AlgebraicElement] extends (A => B) {
}

