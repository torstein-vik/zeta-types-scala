package org.torsteinv.zetatypes.algebra

package object MonoidPackage extends MonoidPackaging

trait MonoidPackaging{
    trait MonoidElement extends AlgebraicElement

    trait Monoid[T <: MonoidElement] extends Magma[T]{
        def identity : T
    }

    trait AdditiveMonoid[T <: MonoidElement with Additive[T]] extends Monoid[T] {
        def zero : T = identity
        override def combine (x : T, y : T) : T = x + y
    }

    trait MultiplicativeMonoid[T <: MonoidElement with Multiplicative[T]] extends Monoid[T] {
        def one : T = identity
        override def combine (x : T, y : T) : T = x * y
    }
}