package io.github.torsteinvik.zetatypes.algebra.morphisms

import io.github.torsteinvik.zetatypes.algebra._

/** Homomorphism between two types of [[io.github.torsteinvik.zetatypes.algebra.AlgebraicElement]]'s 
 *  @tparam A domain of homomorphism
 *  @tparam B codomain of homomorphism
 */
trait Homomorphism[A <: AlgebraicElement, B <: AlgebraicElement] extends (A => B) {
    /** Apply this homomorphism to some input element from the domain */
    def apply(input : A) : B
    final def of [C <: AlgebraicElement](f : Homomorphism[C, A]) = Composition(this, f)
    final def and [C <: AlgebraicElement](f : Homomorphism[B, C]) = Composition(f, this)
}

/** A surjective [[Homomorphism]] */
trait Epimorphism  [A <: AlgebraicElement, B <: AlgebraicElement] extends Homomorphism[A, B]
/** An injective [[Homomorphism]] */
trait Monomorphism [A <: AlgebraicElement, B <: AlgebraicElement] extends Homomorphism[A, B]

/** A bijective [[Homomorphism]] */
trait Isomorphism[A <: AlgebraicElement, B <: AlgebraicElement] extends Homomorphism[A, B] with Epimorphism[A, B] with Monomorphism[A, B]{
    /** The inverse og this isomorphism */
    def inverse : Isomorphism[B, A]
}
