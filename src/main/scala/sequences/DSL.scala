package io.github.torsteinvik.zetatypes.sequences

import scala.language.implicitConversions

import io.github.torsteinvik.zetatypes.algebra._

/** Provides implicit methods for creating sequence factories. Provides the 
 *  implicit [[SequenceFactoryDSL]] class, which has methods to alter factories.
 *  
 */
package object DSL {
    
    /** Implicit functor of a ring to the ring of [[AlgebraicSequences]] with coefficients in that ring */
    implicit def algebraicsequences[E <: RingElement[E]](implicit ring : Ring[E]) : AlgebraicSequences[E] = AlgebraicSequences(ring)
    
    /** Implicit functor of a ring to the ring of [[SpecialAlgebraicSequences]] with coefficients in that ring */
    implicit def specialalgebraicsequences[E <: RingElement[E]](implicit ring : Ring[E]) : SpecialAlgebraicSequences[E] = SpecialAlgebraicSequences(ring)
    
    /** Implicitly creates [[SequenceFactory]] from a [[Sequence]], using [[Sequence.asFactory]]*/
    implicit def getFactory[T] (seq : Sequence[T]) = seq.asFactory
    
    /** Implicitly creates [[SequenceFactory]] from a function from ints*/
    implicit def createSequenceFactory[T] (f : Int => T) : SequenceFactory[T] = new FunctionSequence(f)
    
    /** Implicitly creates [[SequenceFactory]] from a function from [[Sequence]] and ints*/
    implicit def createRecursiveSequenceFactory[T] (f : Sequence[T] => Int => T) : SequenceFactory[T] = new RecursiveSequence(f)
    
    /** Implicitly creates [[ConstantSequence]] from a constant value*/
    implicit def createConstantSequence[T] (value : T) : ConstantSequence[T] = new ConstantSequence[T](value)
    /** Implicitly creates [[ShortSequence]] from a Seq, truncated to the length of the Seq*/
    implicit def createShortSequence[T] (seq : Seq[T]) : ShortSequence[T]    = new ShortSequence[T](seq : _*)
    
    /** Implicit class for improved syntax. 
     *  @constructor creates an implicit class from a value and some implicit evidence turning it into [[SequenceFactory]]. In 
     *  practice, this means that it takes a [[SequenceFactory]] as input
     *  @param s the value which ev converts into a [[SequenceFactory]]
     *  @param ev some evidence, which converts s into a [[SequenceFactory]]
     *  @tparam T the type of [[Sequence]] to be created
     *  @tparam S the type of the input, which the implicit evidence is applied to to create a [[SequenceFactory]]
     */
    implicit class SequenceFactoryDSL[T, S] (val s : S)(implicit ev: S => SequenceFactory[T]) {
        /** Evidence applied to the input */
        lazy val old = ev(s)
        
        /** The [[JuxtapositionedSequence]] of this before some other. Apply [[upTo]] first in case this sequence is infinite. */
        def followedBy (next : SequenceFactory[T]) : SequenceFactory[T] = new JuxtapositionedSequence(old, next)
        /** The [[SequenceLimited]] of this factory and some limit length. */
        def upTo (limit : Int) : SequenceFactory[T] = new SequenceLimited(old, limit)
        /** The [[RepeatedSequence]] of this factory. */
        def repeat : SequenceFactory[T] = new RepeatedSequence[T](old)
    }
}

package DSL {
    
    /** Dummy class so that scaladoc shows DSL */
    protected class DummyClass
}
