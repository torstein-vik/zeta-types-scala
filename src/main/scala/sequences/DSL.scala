package org.zetatypes.sequences

import scala.language.implicitConversions

package object DSL {
    implicit def createSequenceFactory[T] (f : Int => T) : SequenceFactory[T] = new SequenceFactory[T] {
        def apply(seq : Sequence[T])(index : Int) = f(index)
    }
    
    implicit def createRecursiveSequenceFactory[T] (f : Sequence[T] => Int => T) : SequenceFactory[T] = new SequenceFactory[T] {
        def apply(seq : Sequence[T])(index : Int) = f(seq)(index)
    }
    
    implicit def createConstantSequence[T] (value : T) : ConstantSequence[T] = new ConstantSequence[T](value)
    implicit def createShortSequence[T] (seq : Seq[T]) : ShortSequence[T]    = new ShortSequence[T](seq : _*)

    implicit class SequenceFactoryDSL[T, S] (val s : S)(implicit ev: S => SequenceFactory[T]) {
        lazy val old = ev(s)
        
        def followedBy (next : SequenceFactory[T]) : SequenceFactory[T] = new SequenceFactory[T]{
            def apply (seq : Sequence[T])(index : Int) : T = old.length match {
                case None => old(seq)(index)
                case Some(limit) if index <  limit =>  old(seq)(index)
                case Some(limit) if index >= limit => next(seq)(index)
            }
            
            override def hasInternalCache : Boolean = old.hasInternalCache && next.hasInternalCache
            override def length : Option[Int] = for {l1 <- old.length; l2 <- next.length} yield l1 + l2
        }
        
        def upTo (limit : Int) : SequenceFactory[T] = new SequenceFactory[T]{
            def apply (seq : Sequence[T])(index : Int) : T = old.apply(seq)(index)
            override def hasInternalCache : Boolean = old.hasInternalCache
            override def length : Option[Int] = Some(limit + 1)
        }
        
        def repeat : SequenceFactory[T] = new SequenceFactory[T]{
            def apply (seq : Sequence[T])(index : Int) : T = length match {
                case None => old.apply(seq)(index)
                case Some(limit) => old.apply(seq)(index % limit)
            }
            override def hasInternalCache : Boolean = old.hasInternalCache
        } 
    }
}
