package io.github.torsteinvik.zetatypes.tannakiansymbols

import io.github.torsteinvik.zetatypes.algebra._


/** A set where the same element may appear multiple times. Used for defining [[TannakianSymbol]]
 *  @tparam T the type of element this Multiset contains
 *  @constructor Creates a new Multiset from a list of elements
 *  @param elements The list of elements building up the Multiset
 */
class Multiset[T <: MonoidElement](val elements : T*) {
    
    override def toString : String = {
        if (elements.length == 0) {
            return "Ø"
        } else {
            return elements.mkString("{", ", ", "}")
        }
    }
    
    override def equals (that : Any) : Boolean = that match {
        case _ : Multiset[_] =>  {
            val those = that.asInstanceOf[Multiset[_]].elements
            
            // If the lengths are the same, then 'those' cancelling all in elements must mean they are equal
            return those.length == elements.length && elements.diff(those) == Seq() 
        }
        case _ => false
    }
    
    /** Return the multiplicity map of this Multiset */
    def toMap : Map[T, Int] = {
        var el : Map[T, Int] = Map()
        elements.foreach(x => el += x -> (el.getOrElse(x, 0) + 1))
        return el
    }
    
    /** Create a [[TannakianSymbol]] with this [[Multiset]] as upstairs, and some other [[Multiset]] as downstairs. 
     *  Requires some implicit evidence to make all types compatible, along with a [[io.github.torsteinvik.zetatypes.algebra.Monoid]].
     *  @tparam S the type of the elements of the created [[TannakianSymbol]].
     *  @param downstairs the downstairs [[Multiset]] to create the [[TannakianSymbol]]
     *  @param monoid the [[io.github.torsteinvik.zetatypes.algebra.Monoid]] that the [[TannakianSymbol]] is to compute using
     *  @param conv implicit evidence that converts the type of the upstairs and downstairs elements into the elements of the [[TannakianSymbol]]
     */
    def /[S <: MonoidElement](downstairs : Multiset[T])(implicit monoid : Monoid[S], conv: T => S) : TannakianSymbol[S] = {
        var el : Map[S, BigInt] = Map()
                   elements.foreach(x => el += conv(x) -> (el.getOrElse(conv(x), 0 : BigInt) + 1))
        downstairs.elements.foreach(x => el += conv(x) -> (el.getOrElse(conv(x), 0 : BigInt) - 1))
        return new TannakianSymbol[S](el.toSeq)(monoid)
    }
}
