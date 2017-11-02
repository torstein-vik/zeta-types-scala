package org.zetatypes.tannakiansymbols

import org.zetatypes.algebra._

class Multiset[T](val elements : T*) {
    
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
    
    def toMap : Map[T, Int] = {
        var el : Map[T, Int] = Map()
        elements.foreach(x => el += x -> (el.getOrElse(x, 0) + 1))
        return el
    }
    
    def /[S <: MonoidElement](downstairs : Multiset[T])(implicit conv: T => S, monoid : Monoid[S]) : TannakianSymbol[S] = {
        var el : Map[S, BigInt] = Map()
                   elements.foreach(x => el += conv(x) -> (el.getOrElse(conv(x), 0 : BigInt) + 1))
        downstairs.elements.foreach(x => el += conv(x) -> (el.getOrElse(conv(x), 0 : BigInt) - 1))
        return new TannakianSymbol[S](el.toSeq)(monoid)
    }
}