package io.zetatypes

import algebra.Monoids.{Monoid, Element => MonoidElement}
import algebra.Rings.{Ring, RingClass, Element => RingElement}

class TannakianSymbols[E <: MonoidElement, M <: Monoid[E]](monoid : M) extends 
    RingClass[TannakianSymbol[E, M]](
    new TannakianSymbol(Seq.empty), 
    new TannakianSymbol(Seq((monoid.identity, 1))))

class TannakianSymbol[E <: MonoidElement, M <: Monoid[E]] (elements : Seq[(E, Int)]) extends 
    RingElement[TannakianSymbol[E, M]] {
    override def +(that : TannakianSymbol[E, M]) = null 
    override def *(that : TannakianSymbol[E, M]) = null
    
    def ==(that : TannakianSymbol[E, M]) : Boolean = {
        return false
    }
    
    override def toString : String = {
        return "string"
    } 
}
