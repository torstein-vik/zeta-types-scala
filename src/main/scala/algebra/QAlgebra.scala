package org.torsteinv.zetatypes.algebra

import structures.{Rational}


/** An element of a [[PartialQAlgebra]] */
trait PartialQAlgebraElement[T <: PartialQAlgebraElement[T]] extends RingElement[T]{
    /** Attempts to multiply this element with a rational number, returns Some(result) on success, None on failure. */
    def partialQMult (n : Rational) : Option[T]
}

/** A type of ring with a partial Q-action, that is a form of multiplication with rational numbers that returns Option[T] 
 *  @tparam T The type of element that is partially multiplied with elements in Q.
 */
trait PartialQAlgebra[T <: PartialQAlgebraElement[T]] extends Ring[T] {
    /** partial rational multiplication, inferred from [[PartialQAlgebraElement.partialQMult]] */
    def partialQMult (x : T)(n : Rational) : Option[T] = x.partialQMult(n)
}


