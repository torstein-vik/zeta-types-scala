package io.github.torsteinvik.zetatypes.tannakiansymbols

import io.github.torsteinvik.zetatypes.algebra._


/** A set where the same element may appear multiple times. Used for defining [[TannakianSymbol]]
 *  @tparam T the type of element this AlgebraicMultiset contains
 *  @constructor Creates a new AlgebraicMultiset from a list of elements
 *  @param elements The list of elements building up the AlgebraicMultiset
 */
class AlgebraicMultiset[T <: MonoidElement](val elements : T*) {
    
    override def toString : String = {
        if (elements.length == 0) {
            return "Ø"
        } else {
            return elements.mkString("{", ", ", "}")
        }
    }
    
    override def equals (that : Any) : Boolean = that match {
        case _ : AlgebraicMultiset[_] =>  {
            val those = that.asInstanceOf[AlgebraicMultiset[_]].elements
            
            // If the lengths are the same, then 'those' cancelling all in elements must mean they are equal
            return those.length == elements.length && elements.diff(those) == Seq() 
        }
        case _ => false
    }
    
    /** Return the multiplicity map of this AlgebraicMultiset */
    def toMap : Map[T, Int] = {
        var el : Map[T, Int] = Map()
        elements.foreach(x => el += x -> (el.getOrElse(x, 0) + 1))
        return el
    }
    
    /** Create a [[TannakianSymbol]] with this [[AlgebraicMultiset]] as upstairs, and some other [[AlgebraicMultiset]] as downstairs. 
     *  Requires some implicit evidence to make all types compatible, along with a [[io.github.torsteinvik.zetatypes.algebra.Monoid]].
     *  @tparam S the type of the elements of the created [[TannakianSymbol]].
     *  @param downstairs the downstairs [[AlgebraicMultiset]] to create the [[TannakianSymbol]]
     *  @param monoid the [[io.github.torsteinvik.zetatypes.algebra.Monoid]] that the [[TannakianSymbol]] is to compute using
     *  @param conv implicit evidence that converts the type of the upstairs and downstairs elements into the elements of the [[TannakianSymbol]]
     */
    def /(downstairs : AlgebraicMultiset[T])(implicit monoid : Monoid[T]) : TannakianSymbol[T] = {
        var el : Map[T, BigInt] = Map()
                   elements.foreach(x => el += x -> (el.getOrElse(x, 0 : BigInt) + 1))
        downstairs.elements.foreach(x => el += x -> (el.getOrElse(x, 0 : BigInt) - 1))
        return new TannakianSymbol[T](el.toSeq)(monoid)
    }
}
