package org.torsteinv.zetatypes.sequences

import scala.language.implicitConversions

/** Provides implicit methods for creating sequence factories. Provides the 
 *  implicit [[SequenceFactoryDSL]] class, which has methods to alter factories.
 *  
 */
package object DSL {
    
    implicit def getFactory[T] (seq : Sequence[T]) = seq.asFactory
    
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
        
        def followedBy (next : SequenceFactory[T]) : SequenceFactory[T] = new JuxtapositionedSequence(old, next)
        def upTo (limit : Int) : SequenceFactory[T] = new SequenceLimited(old, limit)
        def repeat : SequenceFactory[T] = new RepeatedSequence[T](old)
    }
    
    /** Dummy class so that scaladoc shows DSL */
    protected class DummyClass
}
