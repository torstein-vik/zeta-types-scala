package org.zetatypes.algebra

trait PartialGroupElement extends MonoidElement
trait GroupElement extends PartialGroupElement

trait PartialGroup[T <: PartialGroupElement] extends Monoid[T] {
    def partialinvert (x : T) : Option[T]
}

trait Group[T <: GroupElement] extends PartialGroup[T] {
    def invert (x : T) : T
    def partialinvert (x : T) = Some(invert(x))
}

trait AdditiveGroup[T <: GroupElement with Additive[T] with Subtractive[T]] extends AdditiveMonoid[T] {
    def invert (x : T) = -x
}

trait MultiplicativeGroup[T <: GroupElement with Multiplicative[T] with Divisible[T]] extends MultiplicativeMonoid[T] {
    def invert (x : T) = x.inverse()
}

trait Subtractive[that <: Subtractive[that]] extends GroupElement with Additive[that]{
    def negation() : that 
    def unary_-() : that = this.negation()
    def -(y : that) : that = (this + -y)
}

trait Divisible[that <: Divisible[that]] extends GroupElement with Multiplicative[that]{
    def inverse() : that
    def unary_/() : that = this.inverse()
    def /(y : that) : that = (this * y.inverse())
}