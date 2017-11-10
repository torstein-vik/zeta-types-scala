package org.torsteinv.zetatypes.algebra



/** An element of some ring, with implementations of [[Additive]], [[Subtractive]], and [[Multiplicative]]*/
trait RingElement[T <: RingElement[T]] extends GroupElement with Additive[T] with Subtractive[T] with Multiplicative[T] {
    /** The canonical ring that this element is a part of */
    val canonicalRing : Ring[T]
}

/** An algebraic structure containing an [[additive]] [[Group]] structure, and a [[multiplicative]] [[Monoid]] structure.
 *  Also has shorthands for the zero and one element
 *  @tparam E The type of [[RingElement]] that this ring uses 
 */
trait Ring[E <: RingElement[E]] {
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
abstract class RingClass[E <: RingElement[E]] (val zero : E, val one : E) extends Ring[E]{ outer =>
    override object additive extends AdditiveGroup[E] {
        def identity = outer.zero
    }
    
    override object multiplicative extends MultiplicativeMonoid[E] {
        def identity = outer.one
    }
}
