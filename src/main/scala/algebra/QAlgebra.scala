package io.github.torsteinvik.zetatypes.algebra

import structures.{Rational}


/** An element of a [[PartialQModule]] */
trait PartialQModuleElement[T <: PartialQModuleElement[T]] extends GroupAdditive[T] {
    /** Attempts to multiply this element with a rational number, returns Some(result) on success, None on failure. */
    def partialQMult (n : Rational) : Option[T]
}

/** A type of group with a partial Q-action, that is a form of multiplication with rational numbers that returns Option[T] 
 *  @tparam T The type of element that is partially multiplied with elements in Q.
 */
trait PartialQModule[T <: PartialQModuleElement[T]] extends AdditiveGroup[T] {
    /** partial rational multiplication, inferred from [[PartialQModuleElement.partialQMult]] */
    def partialQMult (x : T)(n : Rational) : Option[T] = x.partialQMult(n)
}

/** A type of ring with a partial Q-action, that is a form of multiplication with rational numbers that returns Option[T] 
 *  @tparam T The type of element that is partially multiplied with elements in Q.
 */
trait PartialQAlgebra[T <: RingElement[T] with PartialQModuleElement[T]] extends Ring[T] { outer => 
    /** partial rational multiplication, inferred from [[PartialQModuleElement.partialQMult]] */
    def partialQMult (x : T)(n : Rational) : Option[T] = x.partialQMult(n)
}
