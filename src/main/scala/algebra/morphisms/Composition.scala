package io.github.torsteinvik.zetatypes.algebra.morphisms

import io.github.torsteinvik.zetatypes.algebra._

private[morphisms] class Composition[A <: AlgebraicElement, B <: AlgebraicElement, C <: AlgebraicElement]
    (f : Homomorphism[B, C], g : Homomorphism[A, B]) extends Homomorphism[A, C] {
    def apply (x : A) = f(g(x))
}

object Composition {
    def apply[A <: AlgebraicElement, B <: AlgebraicElement, C <: AlgebraicElement]
             (f : Homomorphism[B, C], g : Homomorphism[A, B]) :      Composition[A, B, C] = (f, g) match {
        case (f : Monomorphism[B, C], g : Monomorphism[A, B]) => new Composition[A, B, C]   (f, g) with Monomorphism[A, C]
        case (f : Epimorphism[B, C],  g : Epimorphism[A, B])  => new Composition[A, B, C]   (f, g) with Epimorphism[A, C]
        case (f, g)                                           => new Composition[A, B, C]   (f, g)
    }
}
