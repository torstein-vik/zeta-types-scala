package org.zetatypes.sequences

trait SequenceFactory[E] {
    def apply (seq : Sequence[E])(index : Int) : E
    def hasInternalCache : Boolean = false
    def length : Option[Int] = None
}

class ShortSequence[E] (data : E*) extends SequenceFactory[E] {
    def apply (seq : Sequence[E])(index : Int) = data(index)
    override def hasInternalCache = true
    
    override def length = Some(data.length)
}

class ConstantSequence[E] (value : E) extends SequenceFactory[E] {
    def apply (seq : Sequence[E])(index : Int) = value
    override def hasInternalCache = true
}
