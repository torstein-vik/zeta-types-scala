package org.torsteinv.zetatypes.tannakiansymbols

import scala.annotation.tailrec

import org.torsteinv.zetatypes.algebra._
import org.torsteinv.zetatypes.algebra.structures.{Rational, Integer}
import org.torsteinv.zetatypes.algebra.structures.DSL.{Rational}

import scalaz.{Monoid => ScalazMonoid, _}, std.list._, std.option._, syntax.traverse._


trait TannakianSymbolsPackaging {

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
        
        override def partialQMult(n : Rational) = (cleanup.elements.map({case (x, i) => (x, Integer(i).partialQMult(n))}).map({
            case (x, None) => None
            case (x, Some(Integer(i))) => Some((x, i))
        }).toList.sequence).map(new TannakianSymbol(_))
        
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
            return new TannakianSymbol(data.toSeq.filter({case (x, i) => i != 0}))
        }
        
        def superdimension : (BigInt, BigInt) = cleanup.elements.foldLeft((0 : BigInt, 0 : BigInt)) {
            case ((aa, ab), (x, i)) if i > 0 => (aa + i, ab)
            case ((aa, ab), (x, i)) if i < 0 => (aa, ab - i)
        }
        
        def evendimension : BigInt = superdimension._1
        
        def odddimension : BigInt = superdimension._2
        
        def islineelement = superdimension == (1, 0)
        
        def augmentation = superdimension match {case (even, odd) => even - odd}
    }

}
