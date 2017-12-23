package io.github.torsteinvik.zetatypes.algebra.morphisms

import io.github.torsteinvik.zetatypes.algebra._

/** Identity morphism on A
 *  @tparam A The [[io.github.torsteinvik.zetatypes.algebra.AlgebraicElement]] this identity morphism maps
 */
case class Identity[A <: AlgebraicElement]() extends Isomorphism[A, A] {
    def apply (x : A) = x
    def inverse = Identity[A]()
}
