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
    }
}
