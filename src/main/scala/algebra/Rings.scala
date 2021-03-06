package io.github.torsteinvik.zetatypes.algebra



/** An element of some [[Ring]], with implementations of [[GroupAdditive]], [[MonoidMultiplicative]]*/
trait RingElement[T <: RingElement[T]] extends GroupAdditive[T] with MonoidMultiplicative[T] {
    /** The canonical [[io.github.torsteinvik.zetatypes.algebra.Ring]] that this element is a part of */
    val canonicalRing : Ring[T]
    final override lazy val one = canonicalRing.one
    final override lazy val zero = canonicalRing.zero
}

/** An algebraic structure containing an [[additive]] [[Group]] structure, and a [[multiplicative]] [[Monoid]] structure.
 *  Also has shorthands for the zero and one element
 *  @tparam E The type of [[RingElement]] that this ring uses 
 */
trait Ring[E <: RingElement[E]] extends AlgebraicStructure[E] {
    /** The additive [[Group]] structure of this ring */
    val additive       : AdditiveGroup[E]
    /** The multiplicative [[Monoid]] structure of this ring */
    val multiplicative : MultiplicativeMonoid[E]
    
    /** Synonym for the additive identity */
    val zero : E
    /** Synonym for the multiplicative identity */
    val one : E
}

/** Implementation of the [[Ring]] trait, creating the additive and multiplicative structure based on the [[zero]] and [[one]] params
 *  @tparam E The type of [[RingElement]] that this [[Ring]] uses 
 *  @constructor Create a [[Ring]] from some [[RingElement]] type and a value for [[zero]] and [[one]]
 *  @param zero The additive identity of the [[Ring]]
 *  @param one The multiplicative identity of the [[Ring]]
 */
abstract class RingClass[E <: RingElement[E]] (override val zero : E, override val one : E) extends Ring[E]{ outer =>
    final override object additive extends AdditiveGroup[E] {
        final def identity = outer.zero
    }
    
    final override object multiplicative extends MultiplicativeMonoid[E] {
        final def identity = outer.one
    }
}
