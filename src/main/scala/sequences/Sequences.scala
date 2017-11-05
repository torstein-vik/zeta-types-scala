package org.torsteinv.zetatypes.sequences

import scala.collection.immutable.IntMap

class SequenceException(msg : String) extends Exception(msg)

trait Sequence[E] extends Iterable[E] { outer => 
    def apply (index : Int) : E 
    def length : Option[Int]
    
    def createSeq (start : Int, stop : Int) : Seq[E] = (start to stop).map(apply) 
    def asSeq : Option[Seq[E]] = for {limit <- length} yield createSeq(0, limit - 1)
    
    def asFactory : SequenceFactory[E]
    
    def iterator = new Iterator[E] {
        var index = 0
        def hasNext = outer.length match {
            case None => true
            case Some(limit) => index < limit
        }
        
        def next = {index += 1; apply(index - 1)}
    }
}

class CachedSequence[E] (private val factory : SequenceFactory[E]) extends Sequence[E] {    
    val length = factory.length
    def asFactory = new WrappedSequence(this)
    
    private def calculator = factory(this)(_ : Int)
    
    private val doCache = !factory.hasInternalCache
    
    private var cache : IntMap[E] = IntMap()
    private def placeAndCache (index : Int) : E = {
        val res = calculator(index)
        cache += index -> res
        return res
    }
    
    def flush = {cache = IntMap()}
    
    def apply (index : Int) : E = (doCache, index, length) match {
        case (true,  _, _)           if cache.isDefinedAt(index)    => cache(index)
        
        case (true,  _, None)        if index >= 0                  => placeAndCache(index)
        case (true,  _, Some(limit)) if index >= 0 && index < limit => placeAndCache(index)
        
        case (false, _, None)        if index >= 0                  => calculator(index) 
        case (false, _, Some(limit)) if index >= 0 && index < limit => calculator(index) 
        
        case _ => throw new SequenceException("CachedSequence call out of bounds! " + index)
    }
    
    override def toString : String = toString(20)
    
    def toString(upto : Int) : String = length match {
        case Some(limit) if upto >= limit => toString(Math.min(limit - 1, upto))
        case _ => (0 to upto).map(this(_).toString).mkString("", ", ", ", ...")
    }
    
    override def equals(that : Any) : Boolean = that.isInstanceOf[Sequence[E]] && {(that.asInstanceOf[Sequence[E]].length, length) match {
        case (Some(length1 : Int), Some(length2 : Int)) if length1 == length2 => 
            (0 to length1 - 1).forall(i => this(i) == that.asInstanceOf[Sequence[E]](i))
        case (Some(_), Some(_)) => false
        case (None   , Some(_)) => false
        case (Some(_), None   ) => false
        case _ => if ((0 to 10).forall(i => this(i) == that.asInstanceOf[Sequence[E]](i))){
            throw new SequenceException("Can't determine equality of infinite sequences! The first 10 terms are equal")
        } else {
            return false
        }
    }}
    
}

object Sequence {
    def apply[E](factory : SequenceFactory[E]) = new CachedSequence[E](factory)
}
