package org.zetatypes.algebra

import structures.Rational

trait PartialQAlgebraElement extends GroupElement

trait PartialQAlgebra[T <: PartialQAlgebraElement] extends Group[T] {
    def partialQAlgebraOp (x : T)(n : Rational) : Option[T]
}
