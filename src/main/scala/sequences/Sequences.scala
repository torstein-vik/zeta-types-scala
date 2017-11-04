package org.zetatypes.sequences

import scala.collection.immutable.IntMap

class SequenceException(msg : String) extends Exception(msg)

trait Sequence[E] {
    def apply (index : Int) : E 
    def length : Option[Int]
    
    def createSeq (length : Int) : Seq[E] = (0 to length).map(apply) 
}
}
