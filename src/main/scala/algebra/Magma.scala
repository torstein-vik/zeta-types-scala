package org.torsteinv.zetatypes.algebra

import scala.annotation.tailrec

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
    def repeated (x : T, n : Int) : T = n match {
        case n if n > 0 => expTailRec(n, x, x)
        case _ => throw new AlgebraicException("Magmatic repeated addition requires n > 0")
    }
    
    @tailrec
    private def expTailRec(n : Int, x : T, acc : T) : T = {
        if (n <= 1) return acc
        return expTailRec(n - 1, x, combine(x, acc))
    }  
}
/** An [[AlgebraicElement]] with an addition defined on it
 *  @tparam that The type that this element may combined with and yield. that must be a subtype of Additive[that]
 */
trait Additive[that <: Additive[that]] extends AlgebraicElement{
    /** Add some other element to this */
    def +(y : that) : that
    /** Add this Additive element to itself n times. Requires some implicit evidence that the type of this is a subtype of that*/
    def ++(n : Int)(implicit ev: this.type <:< that) : that = n match {
        case n if n > 0 => expTailRec(n, ev(this))
        case _ => throw new AlgebraicException("Magmatic repeated addition requires n > 0")
    }
    
    @tailrec
    private def expTailRec(n : Int, acc : that) : that = {
        if (n <= 1) return acc
        return expTailRec(n - 1, this + acc)
    } 
}
/** An [[AlgebraicElement]] with a multiplication defined on it
 *  @tparam that The type that this element may combined with and yield. that must be a subtype of Multiplicative[that]
 */
trait Multiplicative[that <: Multiplicative[that]] extends AlgebraicElement {
    /** Multiply some other element with this */
    def *(y : that) : that
    /** Multiply this Multiplicative element with itself n times. Requires some implicit evidence that the type of this is a subtype of that*/
    def **(n : Int)(implicit ev: this.type <:< that) : that = n match {
        case n if n > 0 => expTailRec(n, ev(this))
        case _ => throw new AlgebraicException("Magmatic repeated multiplication requires n > 0")
    }
    
    @tailrec
    private def expTailRec(n : Int, acc : that) : that = {
        if (n <= 1) return acc
        return expTailRec(n - 1, this * acc)
    } 
}
