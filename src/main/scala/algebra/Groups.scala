package org.torsteinv.zetatypes.algebra

/** An element of some [[PartialGroup]] */
trait PartialGroupElement extends MonoidElement
/** An element of some [[Group]] */
trait GroupElement extends PartialGroupElement

/** A [[Monoid]] with a [[partialinvert]], which returns an Option[T] 
 *  @tparam T the type of [[PartialGroupElement]] that this PartialGroup deals with
 */
trait PartialGroup[T <: PartialGroupElement] extends Monoid[T] {
    /** Tries to invert x, returning Some(result) on success, None on failure */
    def partialinvert (x : T) : Option[T]
}

/** A [[PartialGroup]] where the result of inversion is always Some(result). 
 *  @tparam T the type of [[GroupElement]] that this Group deals with
 */
trait Group[T <: GroupElement] extends PartialGroup[T] {
    /** Inverts x accoring to this Group's underlying [[Magma]] structure */
    def invert (x : T) : T
    /** Returns Some([[invert]](x)) */
    def partialinvert (x : T) = Some(invert(x))
}

/** A [[Group]] where the elements are [[Additive]]. This allows [[invert]] to be inferred. */
trait AdditiveGroup[T <: GroupElement with Additive[T] with Subtractive[T]] extends AdditiveMonoid[T] {
    def invert (x : T) = -x
}

/** A [[Group]] where the elements are [[Multiplicative]]. This allows [[invert]] to be inferred. */
trait MultiplicativeGroup[T <: GroupElement with Multiplicative[T] with Divisible[T]] extends MultiplicativeMonoid[T] {
    def invert (x : T) = x.inverse()
}

/** A [[GroupElement]] with an [[Additive]] structure and with a [[negation]] defined on it
 *  @tparam that The type that this element is negated into, and of the underlying [[Additive]] structure
 */
trait Subtractive[that <: Subtractive[that]] extends GroupElement with Additive[that]{
    /** The additive negation of this element */
    def negation() : that 
    /** Syntax synonym for [[negation]] */
    def unary_-() : that = this.negation()
    /** Returns this added to the negation of some other element*/
    def -(y : that) : that = (this + -y)
}

/** A [[GroupElement]] with an [[Multiplicative]] structure and with an [[inverse]] defined on it
 *  @tparam that The type that this element is inverted into, and of the underlying [[Multiplicative]] structure
 */
trait Divisible[that <: Divisible[that]] extends GroupElement with Multiplicative[that]{
    /** The multiplicative inverse of this element */
    def inverse() : that
    /** Syntax synonym for [[inverse]] */
    def unary_/() : that = this.inverse()
    /** Returns this multiplied by the inverse of some other element*/
    def /(y : that) : that = (this * y.inverse())
}
