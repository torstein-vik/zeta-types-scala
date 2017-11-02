package org.zetatypes.algebra

import structures.Rational

trait PartialQAlgebraElement[T <: PartialQAlgebraElement[T]] extends RingElement[T]

trait PartialQAlgebra[T <: PartialQAlgebraElement[T]] extends Ring[T] {
    def partialQAlgebraOp (x : T)(n : Rational) : Option[T]
}
