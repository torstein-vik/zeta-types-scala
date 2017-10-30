package io.zetatypes.tannakiansymbols

import io.zetatypes.algebra._

class TannakianSymbols[E <: MonoidElement](monoid : Monoid[E]) extends 
    RingClass[TannakianSymbol[E]](
    new TannakianSymbol(Seq.empty), 
    new TannakianSymbol(Seq((monoid.identity, 1))))

class TannakianSymbol[E <: MonoidElement] (val elements : Seq[(E, Int)]) extends 
    RingElement[TannakianSymbol[E]] {
    override def +(that : TannakianSymbol[E]) = new TannakianSymbol(elements ++ that.elements).cleanup 
    override def *(that : TannakianSymbol[E]) = null
    override def negation() = new TannakianSymbol(elements.map({case (x, i) => (x, -i)}))
    
    override def equals(that : Any) : Boolean = {
        return false
    }
    
    override def toString : String = {
        return "string"
    } 
    
    def cleanup : TannakianSymbol[E] = {
        var data : Map[E, Int] = Map()
        elements.foreach({case (x, i) => data += x -> (data.getOrElse(x, 0) + i)})
        return new TannakianSymbol(data.toSeq.filter({case (x, i) => i != 0}))
    }
}
