package io.zetatypes

import algebra.Monoids.{Monoid, Element => MonoidElement}
import algebra.Rings.{Ring, RingClass, Element => RingElement}

class TannakianSymbols[E <: MonoidElement, M <: Monoid[E]] extends RingClass[TannakianSymbol[E, M]](null, null)

class TannakianSymbol[E <: MonoidElement, M <: Monoid[E]] (elements : Array[Pair[E, Int]]) extends RingElement[TannakianSymbol[E, M]] {
    override def +(that : TannakianSymbol[E, M]) = null 
    override def *(that : TannakianSymbol[E, M]) = null
    
    def ==(that : TannakianSymbol[E, M]) : Boolean = {
        return false
    }
    
    override def toString : String = {
        return "string"
    } 
}

object TannakianSymbol {
    
}
