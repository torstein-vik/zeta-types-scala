package io.zetatypes.tannakiansymbols

import io.zetatypes.algebra._

class TannakianSymbols[E <: MonoidElement](monoid : Monoid[E]) extends 
    RingClass[TannakianSymbol[E]](
    new TannakianSymbol(Seq.empty), 
    new TannakianSymbol(Seq((monoid.identity, 1))))

class TannakianSymbol[E <: MonoidElement] (elements : Seq[(E, Int)]) extends 
    RingElement[TannakianSymbol[E]] {
    override def +(that : TannakianSymbol[E]) = null 
    override def *(that : TannakianSymbol[E]) = null
    override def negation() = null
    
    override def equals(that : Any) : Boolean = {
        return false
    }
    
    override def toString : String = {
        return "string"
    } 
}
