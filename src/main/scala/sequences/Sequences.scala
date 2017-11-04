package org.zetatypes.sequences

import scala.collection.immutable.IntMap

class SequenceException(msg : String) extends Exception(msg)

trait Sequence[E] {
    def apply (index : Int) : E 
    def length : Option[Int]
    
    def createSeq (start : Int, stop : Int) : Seq[E] = (start to stop).map(apply) 
}

class CachedSequence[E] (private val factory : SequenceFactory[E]) extends Sequence[E] {
    val length = factory.length
    
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
}

object Sequence {
    def apply[E](factory : SequenceFactory[E]) = new CachedSequence[E](factory)
}
