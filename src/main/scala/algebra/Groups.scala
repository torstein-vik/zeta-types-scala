package io.zetatypes.algebra

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

trait MultiplicativeGroup[T <: GroupElement with Multiplicative[T]] extends MultiplicativeMonoid[T]

trait Subtractive[that <: Subtractive[that]] extends GroupElement with Additive[that]{
    def unary_-() : that
    def -(y : that) : that = (this + -y)
}