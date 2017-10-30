package io.zetatypes.algebra

trait Magma[T <: AlgebraicElement] {
    def combine (x : T, y : T) : T
}

trait AlgebraicElement
