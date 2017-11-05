package org.zetatypes.sequences

import org.zetatypes.algebra._
import org.zetatypes.algebra.structures.DSL.{Rational}


case class AlgebraicSequences[E <: RingElement[E]] (ring : Ring[E]) extends 
    RingClass[AlgebraicSequence[E]](
    new AlgebraicSequence(new JuxtapositionedSequence(new SequenceLimited(new ConstantSequence(ring.zero), 0), new ConstantSequence(ring.one)))(ring),
    new AlgebraicSequence(new ConstantSequence(ring.one))(ring))

class AlgebraicSequence[E <: RingElement[E]] (factory : SequenceFactory[E])(implicit ring : Ring[E]) extends CachedSequence[E](factory) 
    with RingElement[AlgebraicSequence[E]] {
    override lazy val canonicalRing = AlgebraicSequences(ring)
    
    override def +(that : AlgebraicSequence[E]) = null
    
    override def *(that : AlgebraicSequence[E]) = null
    
    override def negation() = null
}
