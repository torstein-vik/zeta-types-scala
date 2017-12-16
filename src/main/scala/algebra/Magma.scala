package io.github.torsteinvik.zetatypes.algebra


/** An element of some algebraic structure */
trait AlgebraicElement

/** An exception coming from some algebraic method 
 *  @constructor Create an algebraic exception
 *  @param msg The message to be displayed alongside the exception
 */
class AlgebraicException(msg : String) extends Exception(msg)

/** An algebraic structure with a [[combine]] function. Like a [[Monoid]], but without requiring an identity element. 
 *  @tparam T the type of [[AlgebraicElement]] that this magma may combine
 */
trait Magma[T <: AlgebraicElement] {
    /** Combine two elements of type T into a signle one, according to the structure of this [[Magma]] */
    def combine (x : T, y : T) : T
    /** Repeated combination of a single T, n times */
    def repeated (x : T, n : Int) : T = RepetitionAlgorithm[T](combine, n, x, x)
    
}
/** An [[AlgebraicElement]] with an addition defined on it
 *  @tparam that The type that this element may combined with and yield. that must be a subtype of Additive[that]
 */
trait Additive[that <: Additive[that]] extends AlgebraicElement{
    /** Add some other element to this */
    def +(y : that) : that
    /** Add this Additive element to itself n times. Requires some implicit evidence that the type of this is a subtype of that*/
    def ++(n : Int)(implicit ev: this.type <:< that) : that = RepetitionAlgorithm[that](_ + _, n, ev(this), ev(this))
    
}
/** An [[AlgebraicElement]] with a multiplication defined on it
 *  @tparam that The type that this element may combined with and yield. that must be a subtype of Multiplicative[that]
 */
trait Multiplicative[that <: Multiplicative[that]] extends AlgebraicElement {
    /** Multiply some other element with this */
    def *(y : that) : that
    /** Multiply this Multiplicative element with itself n times. Requires some implicit evidence that the type of this is a subtype of that*/
    def **(n : Int)(implicit ev: this.type <:< that) : that = RepetitionAlgorithm[that](_ * _, n, ev(this), ev(this))
    
    def ~^(n : Int)(implicit ev: this.type <:< that) = this.**(n)(ev)
    
    
}

private object RepetitionAlgorithm {
    import scala.annotation.tailrec
        
    //@tailrec
    def apply[T](f : (T, T) => T, n : Int, x : T, acc : T) : T = n match {
        case _ if n > 1 => RepetitionAlgorithm(f, n - 1, x, f(x, acc))
        case 1 => acc
        case _ => throw new AlgebraicException("Magmatic repeated multiplication requires n > 0")
    }
}

