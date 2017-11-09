package org.torsteinv.zetatypes.algebra


/** An element of a [[Monoid]] */
trait MonoidElement extends AlgebraicElement

/** A [[Magma]] together with an [[identity]] 
 *  @tparam T The type of MonoidElement which the identity is of, and of the underlying Magma
 */
trait Monoid[T <: MonoidElement] extends Magma[T]{
    /** The identity of this [[Monoid]] */
    def identity : T
}

/** A [[Monoid]] where the elements are [[Additive]]. This allows [[combine]] to be inferred. */
trait AdditiveMonoid[T <: MonoidElement with Additive[T]] extends Monoid[T] {
    /** Synonym for the monoid identity for additive structures */
    def zero : T = identity
    override def combine (x : T, y : T) : T = x + y
}

/** A [[Monoid]] where the elements are [[Multiplicative]]. This allows [[combine]] to be inferred. */
trait MultiplicativeMonoid[T <: MonoidElement with Multiplicative[T]] extends Monoid[T] {
    /** Synonym for the monoid identity for multiplicative structures */
    def one : T = identity
    override def combine (x : T, y : T) : T = x * y
}
