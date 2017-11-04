package org.zetatypes.sequences

trait SequenceFactory[E] {
    def apply (seq : Sequence[E])(index : Int) : E
    def hasInternalCache : Boolean = false
    def length : Option[Int] = None
}

class ShortSequence[E] (seq : E*) extends SequenceFactory[E] {
    def apply (seq : Sequence[E])(index : Int) = seq(index)
    override def hasInternalCache = true
    
    override def length = Some(seq.length)
}

class ConstantSequence[E] (value : E) extends SequenceFactory[E] {
    def apply (seq : Sequence[E])(index : Int) = value
    override def hasInternalCache = true
}
