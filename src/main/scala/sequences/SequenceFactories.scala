package org.torsteinv.zetatypes.sequences


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

class WrappedSequence[E] (data : Sequence[E]) extends SequenceFactory[E] {
    def apply (seq : Sequence[E])(index : Int) = data(index)
    override def hasInternalCache = data.isInstanceOf[CachedSequence[E]]
    
    override def length = data.length
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

class CombinedSequence[T, S, U] (seq1 : Sequence[T], seq2 : Sequence[S])(f : (T, S) => U)(isFSimple : Boolean = false) extends SequenceFactory[U] {
    def apply (seq : Sequence[U])(index : Int) : U = f(seq1(index), seq2(index))
    
    override def hasInternalCache : Boolean = isFSimple && seq1.isInstanceOf[CachedSequence[T]] && seq2.isInstanceOf[CachedSequence[S]]
    override def length : Option[Int] = (seq1.length, seq2.length) match {
        case (Some(l1), Some(l2)) => Some(Math.min(l1, l2))
        case (Some(l),  None)     => Some(l)
        case (None,     Some(l))  => Some(l)
        case (None,     None)     => None
    } 
}

class MappedSequence[T, S] (data : Sequence[T])(f : (T) => S)(isFSimple : Boolean = false) extends SequenceFactory[S] {
    def apply (seq : Sequence[S])(index : Int) : S = f(data(index))
    
    override def hasInternalCache : Boolean = isFSimple && data.isInstanceOf[CachedSequence[T]]
    override def length : Option[Int] = data.length
}
