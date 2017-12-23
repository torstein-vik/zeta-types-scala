package io.github.torsteinvik.zetatypes.algebra.morphisms

import io.github.torsteinvik.zetatypes.algebra._
case class Identity[A <: AlgebraicElement]() extends Isomorphism[A, A] {
    def apply (x : A) = x
    def inverse = Identity[A]()
}
