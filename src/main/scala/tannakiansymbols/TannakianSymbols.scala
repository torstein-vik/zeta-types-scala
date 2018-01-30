package io.github.torsteinvik.zetatypes.tannakiansymbols

import scala.annotation.tailrec

import io.github.torsteinvik.zetatypes.algebra._
import io.github.torsteinvik.zetatypes.algebra.structures.{Rational, Integer}

/** A ring where the elements are [[TannakianSymbol]]s with elements in some given [[io.github.torsteinvik.zetatypes.algebra.Monoid]] 
 *  @tparam E the type of [[io.github.torsteinvik.zetatypes.algebra.MonoidElement]] that the [[TannakianSymbol]]s consist of
 *  @constructor Creates a new ring of [[TannakianSymbol]]s with elements in a given [[io.github.torsteinvik.zetatypes.algebra.Monoid]] 
 *  @param monoid The [[io.github.torsteinvik.zetatypes.algebra.Monoid]] that the [[TannakianSymbol]] elements belong to
 */
case class TS[E <: MonoidElement](monoid : Monoid[E]) extends 
    RingClass[TannakianSymbol[E]](
    new TannakianSymbol(Seq.empty)(monoid), 
    new TannakianSymbol(Seq((monoid.identity, 1 : BigInt)))(monoid))
    with STDLambdaRing[TannakianSymbol[E]]
    with PartialQAlgebra[TannakianSymbol[E]]

/** An element of [[TS]]
 *  @tparam E the type of [[io.github.torsteinvik.zetatypes.algebra.MonoidElement]] that the elements of this symbol belong to
 *  @constructor Creates a new Tannakian symbol from a list of elements
 *  @param elements The element-multiplicity pairs of this Tannakian symbol
 *  @param monoid The [[io.github.torsteinvik.zetatypes.algebra.Monoid]] that the elements belong to
 */
class TannakianSymbol[E <: MonoidElement] (val elements : Seq[(E, BigInt)])(implicit monoid : Monoid[E]) extends 
    RingElement[TannakianSymbol[E]] with Cleanable
    with STDLambdaRingElement[TannakianSymbol[E]]
    with PartialQAlgebraElement[TannakianSymbol[E]]{
    override lazy val canonicalRing = TS(monoid)
        
    override def +(that : TannakianSymbol[E]) = new TannakianSymbol(elements ++ that.elements).cleanup 
    
    override def *(that : TannakianSymbol[E]) = new TannakianSymbol(for ((x, i) <- elements; (y, j) <- that.elements) yield (monoid.combine(x, y), i * j)).cleanup
    
    override def negation = new TannakianSymbol(elements.map({case (x, i) => (x, -i)}))
    
    override def psi(n : Int) = new TannakianSymbol(elements.map({case (x, i) => (monoid.repeated(x, n), i)})).cleanup
    
    // TODO: Improve with monadic TS
    override def partialQMult(n : Rational) = (cleanup.elements.map({case (x, i) => (x, Integer(i).partialQMult(n))}).map({
        case (_, None) => None
        case (x, Some(Integer(i))) => Some((x, i))
    }).foldLeft[Option[Seq[(E, BigInt)]]](Some(Seq())){
        case (None, _) => None
        case (_, None) => None
        case (Some(acc), Some(x)) => Some(acc ++ Seq(x))
    }).map(new TannakianSymbol(_) with Clean)
    
    override def equals(that : Any) : Boolean = that match {
        case x : TannakianSymbol[E] => (this - x).elements.length == 0
        case _ => false
    }
    
    override def toString : String = {
        return upstairs + "/" + downstairs
    } 
    
    /** Returns the upstairs [[AlgebraicMultiset]] of this [[TannakianSymbol]] */
    def upstairs : AlgebraicMultiset[E] = {
        return new AlgebraicMultiset(this.cleanup.elements.filter({case (_, i) => i > 0}).flatMap({case (x, i) => ((1 : BigInt) to i).map( _ => x)}) : _*)
    }
    
    /** Returns the downstairs [[AlgebraicMultiset]] of this [[TannakianSymbol]] */
    def downstairs : AlgebraicMultiset[E] = {
        return this.negation.upstairs
    }
    
    /** Returns a cleaned-up version of this [[TannakianSymbol]], where element-multiplicity pairs have been combined as much as possible,
     *  and those pairs where the multiplicity is zero, have been removed
     */
    override def cleanup : TannakianSymbol[E] with Clean = {
        import scala.collection.mutable.{Seq => MSeq}
        
        var data : MSeq[(E, BigInt)] = MSeq()
        
        @tailrec
        def add(x : E, i : BigInt, index : Int) : Unit = {
            if (index >= data.length){
                data = data ++ Seq((x, i))
            } else {
                val (y, j) = data(index) 
                if (x == y) {
                    data(index) = (y, i + j)
                } else {
                    add(x, i, index + 1)
                }
            }
        }
        
        elements.foreach {case (x, i) => add(x, i, 0)}
        return new TannakianSymbol(data.toSeq.filter({case (_, i) => i != 0})) with Clean
    }
    
    /** Returns the superdimension of this symbol, that is a tuple of ([[evendimension]], [[odddimension]]) */
    def superdimension : (BigInt, BigInt) = cleanup.elements.foldLeft((0 : BigInt, 0 : BigInt)) {
        case ((aa, ab), (_, i)) if i > 0 => (aa + i, ab)
        case ((aa, ab), (_, i)) if i < 0 => (aa, ab - i)
    }
    
    /** Returns the even dimension of this symbol, that is the number of elements [[upstairs]] (although calculated in an efficient way) */
    def evendimension : BigInt = superdimension._1
    
    /** Returns the odd dimension of this symbol, that is the number of elements [[downstairs]] (although calculated in an efficient way) */
    def odddimension : BigInt = superdimension._2
    
    /** Returns whether or not this symbol is a line-element, that is a symbol with one element [[upstairs]], and none [[downstairs]] */
    def islineelement = superdimension == ((1, 0))
    
    /** Returns the augmentation of this symbol, that is the [[evendimension]] minus the [[odddimension]] */
    def augmentation = superdimension match {case (even, odd) => even - odd}
}
