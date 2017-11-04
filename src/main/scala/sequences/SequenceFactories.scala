package org.zetatypes.sequences

trait SequenceFactory[E] {
    def apply (seq : Sequence[E])(index : Int) : E
    def hasInternalCache : Boolean = false
    def length : Option[Int] = None
}

