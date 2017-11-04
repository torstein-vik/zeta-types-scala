package org.zetatypes.sequences

import scala.collection.immutable.IntMap

class SequenceException(msg : String) extends Exception(msg)

trait Sequence[E] {
    def apply (index : Int) : E 
    def length : Option[Int]
    
    def createSeq (length : Int) : Seq[E] = (0 to length).map(apply) 
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
    
}
