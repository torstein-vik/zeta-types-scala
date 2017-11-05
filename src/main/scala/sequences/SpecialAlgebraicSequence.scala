package org.zetatypes.sequences

import org.zetatypes.algebra._
import org.zetatypes.algebra.structures.{Complex}


case class SpecialAlgebraicSequences[E <: RingElement[E]] (ring : Ring[E]) extends 
    RingClass[SpecialAlgebraicSequence[E]](
    new SpecialAlgebraicSequence(new ConstantSequence(ring.zero))(ring),
    new SpecialAlgebraicSequence(new ConstantSequence(ring.one ))(ring))

class SpecialAlgebraicSequence[E <: RingElement[E]] (factory : SequenceFactory[E])(implicit ring : Ring[E]) extends CachedSequence[E](factory) 
    with RingElement[SpecialAlgebraicSequence[E]] {
    
    override lazy val canonicalRing = SpecialAlgebraicSequences(ring)
    
    override def +(that : SpecialAlgebraicSequence[E]) = new SpecialAlgebraicSequence(new CombinedSequence(this, that)(_ + _)(true))
    
    override def *(that : SpecialAlgebraicSequence[E]) = new SpecialAlgebraicSequence(new CombinedSequence(this, that)(_ * _)(true))
    
}

