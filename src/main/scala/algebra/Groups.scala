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