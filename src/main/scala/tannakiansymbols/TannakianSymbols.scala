package org.zetatypes.tannakiansymbols

import org.zetatypes.algebra._

case class TS[E <: MonoidElement](monoid : Monoid[E]) extends 
    RingClass[TannakianSymbol[E]](
    new TannakianSymbol(Seq.empty)(monoid), 
    new TannakianSymbol(Seq((monoid.identity, 1 : BigInt)))(monoid))
    with StandardLambdaRing[TannakianSymbol[E]]
    with PartialQAlgebra[TannakianSymbol[E]]

class TannakianSymbol[E <: MonoidElement] (val elements : Seq[(E, BigInt)])(implicit monoid : Monoid[E]) extends 
    RingElement[TannakianSymbol[E]] 
    with StandardLambdaRingElement[TannakianSymbol[E]]
    with PartialQAlgebraElement[TannakianSymbol[E]]{
    override lazy val canonicalRing = TS(monoid)
        
    override def +(that : TannakianSymbol[E]) = new TannakianSymbol(elements ++ that.elements).cleanup 
    
    override def *(that : TannakianSymbol[E]) = new TannakianSymbol(for ((x, i) <- elements; (y, j) <- that.elements) yield (monoid.combine(x, y), i * j)).cleanup
    
    override def negation() = new TannakianSymbol(elements.map({case (x, i) => (x, -i)}))
    
    override def psi(n : Int) = new TannakianSymbol(elements.map({case (x, i) => (monoid.repeated(x, n), i)})).cleanup
    
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
        return upstairs + "/" + downstairs
    } 
    
    def upstairs : Multiset[E] = {
        return new Multiset(this.cleanup.elements.filter({case (x, i) => i > 0}).flatMap({case (x, i) => ((1 : BigInt) to i).map( _ => x)}) : _*)
    }
    
    def downstairs : Multiset[E] = {
        return this.negation.upstairs
    }
    
    def cleanup : TannakianSymbol[E] = {
        var data : Map[E, BigInt] = Map()
        elements.foreach({case (x, i) => data += x -> (data.getOrElse(x, 0 : BigInt) + i)})
        return new TannakianSymbol(data.toSeq.filter({case (x, i) => i != 0}))
    }
}
