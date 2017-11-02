package org.zetatypes.algebra

import structures.Rational

trait PartialQAlgebra[T <: GroupElement] extends Group[T] {
    def partialQAlgebraOp (x : T)(n : Rational) : Option[T]
}