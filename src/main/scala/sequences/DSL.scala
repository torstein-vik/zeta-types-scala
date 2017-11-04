package org.zetatypes.sequences

import scala.language.implicitConversions

package object DSL {
    implicit def createSequenceFactory[T] (f : Int => T) : SequenceFactory[T] = new SequenceFactory[T] {
        def apply(seq : Sequence[T])(index : Int) = f(index)
    }
    
    implicit def createRecursiveSequenceFactory[T] (f : Sequence[T] => Int => T) : SequenceFactory[T] = new SequenceFactory[T] {
        def apply(seq : Sequence[T])(index : Int) = f(seq)(index)
    }
    
}
