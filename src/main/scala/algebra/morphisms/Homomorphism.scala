package io.github.torsteinvik.zetatypes.algebra.morphisms

import io.github.torsteinvik.zetatypes.algebra._

trait Homomorphism[-A <: AlgebraicElement, +B <: AlgebraicElement] extends (A => B) {
    def apply(input : A) : B
}

trait Epimorphism  [-A <: AlgebraicElement, B <: AlgebraicElement] extends Homomorphism[A, B]
trait Monomorphism [A <: AlgebraicElement, +B <: AlgebraicElement] extends Homomorphism[A, B]

trait Isomorphism[A <: AlgebraicElement, B <: AlgebraicElement] extends Homomorphism[A, B] with Epimorphism[A, B] with Monomorphism[A, B]{
}
