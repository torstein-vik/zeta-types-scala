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

/** A [[Monoid]] where the elements are [[MonoidAdditive]]. Provides synonym for the identity. */
trait AdditiveMonoid[T <: MonoidAdditive[T]] extends Monoid[T] with AdditiveMagma[T] {
    /** Synonym for the monoid identity for additive structures */
    def zero : T = identity
}

/** A [[Monoid]] where the elements are [[MonoidMultiplicative]]. Provides synonym for the identity. */
trait MultiplicativeMonoid[T <: MonoidMultiplicative[T]] extends Monoid[T] with MultiplicativeMagma[T] {
    /** Synonym for the monoid identity for multiplicative structures */
    def one : T = identity
}


/** A [[MonoidElement]] with an addition defined on it
 *  @tparam that The type that this element may combined with and yield. that must be a subtype of MonoidAdditive[that]
 */
trait MonoidAdditive[that <: MonoidAdditive[that]] extends MonoidElement with MagmaAdditive[that] {
    /** The additive identity */
    val zero : that
    
}

/** A [[MonoidElement]] with a multiplication defined on it
 *  @tparam that The type that this element may combined with and yield. that must be a subtype of MonoidMultiplicative[that]
 */
trait MonoidMultiplicative[that <: MonoidMultiplicative[that]] extends MonoidElement with MagmaMultiplicative[that] {
    /** The multiplicative identity */
    val one : that
    
}
