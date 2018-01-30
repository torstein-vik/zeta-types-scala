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
    /** Add some other element to this */
    def +(y : that) : that
    
    /** Add this Additive element to itself n times. Requires some implicit evidence that the type of this is a subtype of that*/
    def ++(n : Int)(implicit ev: this.type <:< that) : that = MonoidRepetitionAlgorithm[that](_ + _, n, zero, ev(this), ev(this))
    
    /** The additive identity */
    val zero : that
}

/** A [[MonoidElement]] with a multiplication defined on it
 *  @tparam that The type that this element may combined with and yield. that must be a subtype of MonoidMultiplicative[that]
 */
trait MonoidMultiplicative[that <: MonoidMultiplicative[that]] extends MonoidElement {
    /** Multiply some other element with this */
    def *(y : that) : that
    
    /** Multiply this Multiplicative element with itself n times. Requires some implicit evidence that the type of this is a subtype of that*/
    def **(n : Int)(implicit ev: this.type <:< that) : that = MonoidRepetitionAlgorithm[that](_ * _, n, one, ev(this), ev(this))
    
    /** Synonym for [[***]] */
    def ~^(n : Int)(implicit ev: this.type <:< that) = this.**(n)(ev)
    
    /** The multiplicative identity */
    val one : that
}

private object MonoidRepetitionAlgorithm {
    import scala.annotation.tailrec
        
    @tailrec
    def apply[T](f : (T, T) => T, n : Int, unit : T, x : T, acc : T) : T = n match {
        case _ if n > 1 => MonoidRepetitionAlgorithm(f, n - 1, unit, x, f(x, acc))
        case 1 => acc
        case 0 => unit
        case _ => throw new AlgebraicException("Monoidal repeated combination requires n >= 0")
    }
}
