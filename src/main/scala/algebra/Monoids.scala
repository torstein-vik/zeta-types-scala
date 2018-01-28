package io.github.torsteinvik.zetatypes.algebra


/** An element of a [[Monoid]] */
trait MonoidElement extends MagmaElement

/** A [[Magma]] together with an [[identity]] 
 *  @tparam T The type of MonoidElement which the identity is of, and of the underlying Magma
 */
trait Monoid[T <: MonoidElement] extends Magma[T]{
    /** The identity of this [[Monoid]] */
    def identity : T
}

/** A [[Monoid]] where the elements are [[MonoidAdditive]]. This requries the identity "zero" be declared */
trait AdditiveMonoid[T <: MonoidAdditive[T]] extends Monoid[T] with AdditiveMagma[T] {
    /** Synonym for the monoid identity for additive structures */
    def zero : T = identity
}

/** A [[Monoid]] where the elements are [[MonoidMultiplicative]]. This requries the identity "one" be declared */
trait MultiplicativeMonoid[T <: MonoidMultiplicative[T]] extends Monoid[T] with MultiplicativeMagma[T] {
    /** Synonym for the monoid identity for multiplicative structures */
    def one : T = identity
}


