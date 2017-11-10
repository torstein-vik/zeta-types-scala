package org.torsteinv.zetatypes.sequences


/** A factory that builds some [[Sequence]] 
 *  @tparam E the type of element that the created [[Sequence]] consists of
 */
trait SequenceFactory[E] {
    /** Calculate the value at some index 
     *  @param index the index to calculate at
     *  @param seq the sequence that this factory is applied to, allowing recursion
     */
    def apply (seq : Sequence[E])(index : Int) : E
    /** Whether or not this factory has an internal cache, or is so light in computation that there is no need for a [[CachedSequence]] to cache*/
    def hasInternalCache : Boolean = false
    /** The length that this factory can generate to, Some(length) if it is finite, None if it is not */
    def length : Option[Int] = None
}

/** A [[SequenceFactory]] that creates a short sequence from some input finite list 
 *  @tparam E the type of the element that the created [[Sequence]] consists of
 *  @constructor Create a new [[SequenceFactory]] from a fixed sequence of elements
 *  @param data the sequence to generate the factory from
 */
class ShortSequence[E] (data : E*) extends SequenceFactory[E] {
    def apply (seq : Sequence[E])(index : Int) = data(index)
    override def hasInternalCache = true
    
    override def length = Some(data.length)
}

/** A [[SequenceFactory]] that creates an infinite sequence of one constant element 
 *  @tparam E the type of the element that the created [[Sequence]] consists of
 *  @constructor Create a new [[SequenceFactory]] outputting some fixed element
 *  @param value the constant value that the sequence consists of
 */
class ConstantSequence[E] (value : E) extends SequenceFactory[E] {
    def apply (seq : Sequence[E])(index : Int) = value
    override def hasInternalCache = true
}

/** A [[SequenceFactory]] that uses a [[Sequence]] to create a factory, making a duplicate [[Sequence]]
 *  @tparam E the type of the element that the created [[Sequence]] consists of
 *  @constructor Create a new [[SequenceFactory]] duplicating some other [[Sequence]]
 *  @param data the [[Sequence]] to duplicate
 */
class WrappedSequence[E] (data : Sequence[E]) extends SequenceFactory[E] {
    def apply (seq : Sequence[E])(index : Int) = data(index)
    override def hasInternalCache = data.isInstanceOf[CachedSequence[E]]
    
    override def length = data.length
}

/** A [[SequenceFactory]] that uses a [[SequenceFactory]] to create a new factory, but with restricted length
 *  @tparam E the type of the element that the created [[Sequence]] consists of
 *  @constructor Create a new [[SequenceFactory]] as a length restriction of some other [[SequenceFactory]]
 *  @param old the [[SequenceFactory]] to restrict
 *  @param limit the new length of the [[SequenceFactory]]
 */
class SequenceLimited[E] (old : SequenceFactory[E], limit : Int) extends SequenceFactory[E] {
    def apply(seq : Sequence[E])(index : Int) = old.apply(seq)(index)
    override def hasInternalCache : Boolean = old.hasInternalCache
    override def length : Option[Int] = Some(limit + 1)
}

/** A [[SequenceFactory]] that uses a [[SequenceFactory]] to create a new factory, that repeats the input [[SequenceFactory]] again and again
 *  @tparam E the type of the element that the created [[Sequence]] consists of
 *  @constructor Create a new [[SequenceFactory]] that repeats some other [[SequenceFactory]]
 *  @param old the [[SequenceFactory]] to repeat
 */
class RepeatedSequence[E] (old : SequenceFactory[E]) extends SequenceFactory[E] {
    def apply (seq : Sequence[E])(index : Int) : E = old.length match {
        case None => old.apply(seq)(index)
        case Some(limit) => old.apply(seq)(index % limit)
    }
    
    override def hasInternalCache : Boolean = old.hasInternalCache
} 

/** A [[SequenceFactory]] that juxtapositions two other [[SequenceFactory]]s. 
 *  The index is not reset at 0 for the latter [[SequenceFactory]].
 *  @tparam E the type of the element that the created [[Sequence]] consists of
 *  @constructor Create a new [[SequenceFactory]] that which consists of one applied first, and then some other.
 *  @param first the first [[SequenceFactory]] to be applied
 *  @param second the second [[SequenceFactory]] to be applied
 */
class JuxtapositionedSequence[E] (first : SequenceFactory[E], second : SequenceFactory[E]) extends SequenceFactory[E] {
    def apply (seq : Sequence[E])(index : Int) : E = first.length match {
        case None => first(seq)(index)
        case Some(limit) if index <  limit =>  first(seq)(index)
        case Some(limit) if index >= limit => second(seq)(index)
    }
    
    override def hasInternalCache : Boolean = first.hasInternalCache && second.hasInternalCache
    override def length : Option[Int] = for {l1 <- first.length; l2 <- second.length} yield l1 + l2
}

/** A [[SequenceFactory]] that combines two other [[SequenceFactory]]s using some function. 
 *  @tparam T the type of the element that the first [[SequenceFactory]] consists of
 *  @tparam S the type of the element that the second [[SequenceFactory]] consists of
 *  @tparam U the type of the element that the computed [[Sequence]] consists of
 *  @constructor Create a new [[SequenceFactory]] that combines two other [[SequenceFactory]]s using some function.
 *  @param seq1 the [[SequenceFactory]] used in the first function parameter
 *  @param seq2 the [[SequenceFactory]] used in the second function parameter
 *  @param f the function applied to compute a new [[SequenceFactory]]
 *  @param isFSimple whether or not the function f is so simple that there is no need for a [[CachedSequence]] to cache
 */
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

/** A [[SequenceFactory]] that maps another [[SequenceFactory]] through some function. 
 *  @tparam T the type of the element that the [[SequenceFactory]] consists of
 *  @tparam S the type of the element that the computed [[Sequence]] consists of
 *  @constructor Create a new [[SequenceFactory]] that maps a [[SequenceFactory]]s through some function.
 *  @param data the [[SequenceFactory]] that is mapped through the function
 *  @param f the function applied to compute a new [[SequenceFactory]]
 *  @param isFSimple whether or not the function f is so simple that there is no need for a [[CachedSequence]] to cache
 */
class MappedSequence[T, S] (data : Sequence[T])(f : (T) => S)(isFSimple : Boolean = false) extends SequenceFactory[S] {
    def apply (seq : Sequence[S])(index : Int) : S = f(data(index))
    
    override def hasInternalCache : Boolean = isFSimple && data.isInstanceOf[CachedSequence[T]]
    override def length : Option[Int] = data.length
}
