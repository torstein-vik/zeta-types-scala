package io.github.torsteinvik.zetatypes.algebra

/** An element of some [[Field]], with implementations of [[RingElement]] and [[GroupMultiplicative]]*/
trait FieldElement[T <: FieldElement[T]] extends RingElement[T] with GroupMultiplicative[T] {
    /** The canonical [[Field]] that this element is a part of */
    override val canonicalRing : Field[T]
    
    /** Check that this is nonzero, and if so execute and return the input */
    protected final def checkZero(nonzero : =>T) : T = if (this == canonicalRing.zero) throw new AlgebraicException("May not divide by zero") else nonzero
}

/** An algebraic structure containing an [[additive]] and a [[multiplicative]] [[Group]] structure.
 *  Also has shorthands for the zero and one element
 *  @tparam E The type of [[FieldElement]] that this ring uses 
 */
trait Field[E <: FieldElement[E]] extends Ring[E] {
    /** The multiplicative [[Group]] structure of this ring */
    override val multiplicative : MultiplicativeGroup[E]
    
}

/** Implementation of the [[Field]] trait, creating the additive and multiplicative structure based on the [[zero]] and [[one]] params
 *  @tparam E The type of [[FieldElement]] that this [[Field]] uses 
 *  @constructor Create a [[Field]] from some [[FieldElement]] type and a value for [[zero]] and [[one]]
 *  @param zero The additive identity of the [[Field]]
 *  @param one The multiplicative identity of the [[Field]]
 */
abstract class FieldClass[E <: FieldElement[E]] (override val zero : E, override val one : E) extends Field[E]{ outer =>
    override object additive extends AdditiveGroup[E] {
        def identity = outer.zero
    }
    
    override object multiplicative extends MultiplicativeGroup[E] {
        def identity = outer.one
    }
}
