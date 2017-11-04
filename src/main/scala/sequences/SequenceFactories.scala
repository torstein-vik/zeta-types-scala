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

class SequenceLimited[E] (old : SequenceFactory[E], limit : Int) extends SequenceFactory[E] {
    def apply(seq : Sequence[E])(index : Int) = old.apply(seq)(index)
    override def hasInternalCache : Boolean = old.hasInternalCache
    override def length : Option[Int] = Some(limit + 1)
}

class RepeatedSequence[E] (old : SequenceFactory[E]) extends SequenceFactory[E] {
    def apply (seq : Sequence[E])(index : Int) : E = old.length match {
        case None => old.apply(seq)(index)
        case Some(limit) => old.apply(seq)(index % limit)
    }
    
    override def hasInternalCache : Boolean = old.hasInternalCache
} 

class JuxtapositionedSequence[E] (first : SequenceFactory[E], second : SequenceFactory[E]) extends SequenceFactory[E] {
    def apply (seq : Sequence[E])(index : Int) : E = first.length match {
        case None => first(seq)(index)
        case Some(limit) if index <  limit =>  first(seq)(index)
        case Some(limit) if index >= limit => second(seq)(index)
    }
    
    override def hasInternalCache : Boolean = first.hasInternalCache && second.hasInternalCache
    override def length : Option[Int] = for {l1 <- first.length; l2 <- second.length} yield l1 + l2
}
