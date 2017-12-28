package io.github.torsteinvik.zetatypes.algebra.morphisms

import io.github.torsteinvik.zetatypes.algebra._

/** Homomorphism between two types of [[io.github.torsteinvik.zetatypes.algebra.AlgebraicElement]]'s 
 *  @tparam A domain of homomorphism
 *  @tparam B codomain of homomorphism
 */
trait Homomorphism[A <: AlgebraicElement, B <: AlgebraicElement] extends (A => B) {
    /** Apply this homomorphism to some input element from the domain */
    def apply(input : A) : B
    
    /** Compose this morphism with some other morphism 
     *  @tparam C the input type of the final morphism
     *  @param f the morphism to compose with
     */
    final def of [C <: AlgebraicElement](f : Homomorphism[C, A]) = Composition(this, f)
    /** Precompose this morphism with some other morphism 
     *  @tparam C the output type of the final morphism
     *  @param f the morphism to precompose with
     */
    final def and [C <: AlgebraicElement](f : Homomorphism[B, C]) = Composition(f, this)
}

/** A surjective [[Homomorphism]] */
trait Epimorphism  [A <: AlgebraicElement, B <: AlgebraicElement] extends Homomorphism[A, B]
/** An injective [[Homomorphism]] */
trait Monomorphism [A <: AlgebraicElement, B <: AlgebraicElement] extends Homomorphism[A, B]

/** A bijective [[Homomorphism]] */
trait Isomorphism[A <: AlgebraicElement, B <: AlgebraicElement] extends Homomorphism[A, B] with Epimorphism[A, B] with Monomorphism[A, B]{
    /** The inverse of this isomorphism */
    def inverse : Isomorphism[B, A]
}
