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
        // Other choice: upstairs == other.upstairs && downstairs == other.downstairs. Unsure which is best... 
        // that depends on multiplicities, but the current choice allows for high multiplicity without issue
        
        return that match {
            case _ : TannakianSymbol[E] => {
                (this - that.asInstanceOf[TannakianSymbol[E]]).elements.length == 0
            }
            
            case _ => false
        }
    }
    
    override def toString : String = {
        return "string"
    } 
    
    def upstairs : Multiset[E] = {
        return new Multiset(this.cleanup.elements.filter({case (x, i) => i > 0}).flatMap({case (x, i) => (1 to i).map( _ => x)}) : _*)
    }
    
    def downstairs : Multiset[E] = {
        return this.negation.upstairs
    }
    
    def cleanup : TannakianSymbol[E] = {
        var data : Map[E, Int] = Map()
        elements.foreach({case (x, i) => data += x -> (data.getOrElse(x, 0) + i)})
        return new TannakianSymbol(data.toSeq.filter({case (x, i) => i != 0}))
    }
}
