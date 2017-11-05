package org.torsteinv.zetatypes.sequences

import org.torsteinv.zetatypes.algebra._
import org.torsteinv.zetatypes.algebra.structures.{Complex}

trait AlgebraicSequencePackaging {

    case class AlgebraicSequences[E <: RingElement[E]] (ring : Ring[E]) extends 
        RingClass[AlgebraicSequence[E]](
        new AlgebraicSequence(new ConstantSequence(ring.zero))(ring),
        new AlgebraicSequence(new ConstantSequence(ring.one ))(ring))

    class AlgebraicSequence[E <: RingElement[E]] (factory : SequenceFactory[E])(implicit ring : Ring[E]) extends CachedSequence[E](factory) 
        with RingElement[AlgebraicSequence[E]] {
        override lazy val canonicalRing = AlgebraicSequences(ring)
        
        override def +(that : AlgebraicSequence[E]) = new AlgebraicSequence(new CombinedSequence(this, that)(_ + _)(true))
        
        override def *(that : AlgebraicSequence[E]) = new AlgebraicSequence(new CombinedSequence(this, that)(_ * _)(true))
        
        override def negation() = new AlgebraicSequence(new MappedSequence(this)(_.negation)(true))
    }

    object ComplexSequences extends AlgebraicSequences(Complex)

    object ComplexSequence{
        import org.torsteinv.zetatypes.algebra.structures.DSL.{Complex}
        def apply (factory : SequenceFactory[Complex]) = new AlgebraicSequence(factory)(Complex)
    }

}