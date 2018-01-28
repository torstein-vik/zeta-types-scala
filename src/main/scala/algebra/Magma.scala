package io.github.torsteinvik.zetatypes.algebra

/** An element of a [[Magma]] */
trait MagmaElement extends AlgebraicElement

/** An algebraic structure with a [[combine]] function. Like a [[Monoid]], but without requiring an identity element. 
 *  @tparam T the type of [[MagmaElement]] that this magma may combine
 */
trait Magma[T <: MagmaElement] extends AlgebraicStructure[T] {
    /** Combine two elements of type T into a signle one, according to the structure of this [[Magma]] */
    def combine (x : T, y : T) : T
    /** Repeated combination of a single T, n times */
    def repeated (x : T, n : Int) : T
    
}

/** A [[MagmaElement]] with an addition defined on it
 *  @tparam that The type that this element may combined with and yield. that must be a subtype of Additive[that]
 */
trait Additive[that <: Additive[that]] extends AlgebraicElement {
    /** Add some other element to this */
    def +(y : that) : that
    /** Add this Additive element to itself n times. Requires some implicit evidence that the type of this is a subtype of that*/
    def ++(n : Int)(implicit ev: this.type <:< that) : that = MagmaRepetitionAlgorithm[that](_ + _, n, ev(this), ev(this))
    
}

/** A [[MagmaElement]] with a multiplication defined on it
 *  @tparam that The type that this element may combined with and yield. that must be a subtype of Multiplicative[that]
 */
trait Multiplicative[that <: Multiplicative[that]] extends AlgebraicElement {
    /** Multiply some other element with this */
    def *(y : that) : that
    /** Multiply this Multiplicative element with itself n times. Requires some implicit evidence that the type of this is a subtype of that*/
    def **(n : Int)(implicit ev: this.type <:< that) : that = MagmaRepetitionAlgorithm[that](_ * _, n, ev(this), ev(this))
    /** Synonym for [[***]] */
    def ~^(n : Int)(implicit ev: this.type <:< that) = this.**(n)(ev)
    
}

private object MagmaRepetitionAlgorithm {
    import scala.annotation.tailrec
        
    @tailrec
    def apply[T](f : (T, T) => T, n : Int, x : T, acc : T) : T = n match {
        case _ if n > 1 => MagmaRepetitionAlgorithm(f, n - 1, x, f(x, acc))
        case 1 => acc
        case _ => throw new AlgebraicException("Magmatic repeated application requires n > 0")
    }
}
