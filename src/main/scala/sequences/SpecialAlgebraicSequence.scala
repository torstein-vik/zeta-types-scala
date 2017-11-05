package org.torsteinv.zetatypes.sequences

import org.torsteinv.zetatypes.algebra._
import org.torsteinv.zetatypes.algebra.structures.{Complex}

package object SpecialAlgebraicSequencesPackage extends SpecialAlgebraicSequencesPackaging

trait SpecialAlgebraicSequencesPackaging {

    case class SpecialAlgebraicSequences[E <: RingElement[E]] (ring : Ring[E]) extends 
        RingClass[SpecialAlgebraicSequence[E]](
        new SpecialAlgebraicSequence(new ConstantSequence(ring.zero))(ring),
        new SpecialAlgebraicSequence(new ConstantSequence(ring.one ))(ring))

    class SpecialAlgebraicSequence[E <: RingElement[E]] (factory : SequenceFactory[E])(implicit ring : Ring[E]) extends CachedSequence[E](factory) 
        with RingElement[SpecialAlgebraicSequence[E]] {
        
        override lazy val canonicalRing = SpecialAlgebraicSequences(ring)
        
        override def +(that : SpecialAlgebraicSequence[E]) = new SpecialAlgebraicSequence(new CombinedSequence(this, that)(_ + _)(true))
        
        override def *(that : SpecialAlgebraicSequence[E]) = new SpecialAlgebraicSequence(new CombinedSequence(this, that)(_ * _)(true))
        
        override def negation() = new SpecialAlgebraicSequence(new MappedSequence(this)(_.negation)(true))
        
        override def apply(index : Int) = if (index == 0) {ring.one} else {super.apply(index)}
    }

    object SpecialComplexSequences extends SpecialAlgebraicSequences(Complex)

    object SpecialComplexSequence {
        import org.torsteinv.zetatypes.algebra.structures.Complex
        def apply (factory : SequenceFactory[Complex]) = new SpecialAlgebraicSequence(factory)(Complex)
    }

}
