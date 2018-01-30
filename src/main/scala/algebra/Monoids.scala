package io.github.torsteinvik.zetatypes.algebra


/** An element of a [[Monoid]] */
trait MonoidElement extends AlgebraicElement

/** A unital assosiative commutative operation together with an [[identity]] 
 *  @tparam T The type of MonoidElement which is combined in this Monoid
 */
trait Monoid[T <: MonoidElement] extends AlgebraicStructure[T] {
    
    /** The identity of this [[Monoid]] */
    def identity : T
    
    /** Combine two elements of type T into a signle one, according to the structure of this [[Monoid]] */
    def combine (x : T, y : T) : T
    
    /** Repeated combination of a single T, n times */
    def repeated (x : T, n : Int) : T
}

/** A [[Monoid]] where the elements are [[MonoidAdditive]]. Provides synonym for the identity. */
trait AdditiveMonoid[T <: MonoidAdditive[T]] extends Monoid[T] {
    
    override def combine (x : T, y : T) : T = x + y
    override def repeated (x : T, n : Int) : T = x ++ n
    
    /** Synonym for the monoid identity for additive structures */
    def zero : T = identity
}

/** A [[Monoid]] where the elements are [[MonoidMultiplicative]]. Provides synonym for the identity. */
trait MultiplicativeMonoid[T <: MonoidMultiplicative[T]] extends Monoid[T] {
    
    override def combine (x : T, y : T) : T = x * y
    override def repeated (x : T, n : Int) : T = x ** n
    
    /** Synonym for the monoid identity for multiplicative structures */
    def one : T = identity
}


/** A [[MonoidElement]] with an addition defined on it
 *  @tparam that The type that this element may combined with and yield. that must be a subtype of MonoidAdditive[that]
 */
trait MonoidAdditive[that <: MonoidAdditive[that]] extends MonoidElement {
    /** The additive identity */
    val zero : that
    
    override def ++(n : Int)(implicit ev: this.type <:< that) : that = n match {
        case _ if n > 0 => super.++(n)(ev)
        case 0 => zero
        case _ => throw new AlgebraicException("Monoidal repeated application requires n >= 0")
    }
}

/** A [[MonoidElement]] with a multiplication defined on it
 *  @tparam that The type that this element may combined with and yield. that must be a subtype of MonoidMultiplicative[that]
 */
trait MonoidMultiplicative[that <: MonoidMultiplicative[that]] extends MonoidElement {
    /** The multiplicative identity */
    val one : that
    
    override def **(n : Int)(implicit ev: this.type <:< that) : that = n match {
        case _ if n > 0 => super.**(n)(ev)
        case 0 => one
        case _ => throw new AlgebraicException("Monoidal repeated application requires n >= 0")
    }
}
