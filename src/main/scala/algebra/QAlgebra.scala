package org.zetatypes.algebra

import structures.Rational
import structures.DSL.{Rational}

trait PartialQAlgebraElement[T <: PartialQAlgebraElement[T]] extends RingElement[T]{
    def partialQMult (n : Rational) : Option[T]
}

trait PartialQAlgebra[T <: PartialQAlgebraElement[T]] extends Ring[T] {
    def partialQMult (x : T)(n : Rational) : Option[T] = x.partialQMult(n)
}