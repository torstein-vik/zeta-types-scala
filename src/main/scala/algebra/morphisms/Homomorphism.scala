package io.github.torsteinvik.zetatypes.algebra.morphisms

import io.github.torsteinvik.zetatypes.algebra._

/** Homomorphism between two types of [[io.github.torsteinvik.zetatypes.algebra.AlgebraicElement]]'s 
 *  @tparam A domain of homomorphism
 *  @tparam B codomain of homomorphism
 */
trait Homomorphism[-A <: AlgebraicElement, +B <: AlgebraicElement] extends (A => B) {
    /** Apply this homomorphism to some input element from the domain */
    def apply(input : A) : B
}

trait Epimorphism  [-A <: AlgebraicElement, B <: AlgebraicElement] extends Homomorphism[A, B]
trait Monomorphism [A <: AlgebraicElement, +B <: AlgebraicElement] extends Homomorphism[A, B]

trait Isomorphism[A <: AlgebraicElement, B <: AlgebraicElement] extends Homomorphism[A, B] with Epimorphism[A, B] with Monomorphism[A, B]{
    def inverse : Isomorphism[B, A]
}
