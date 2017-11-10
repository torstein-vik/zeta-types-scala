package org.torsteinv.zetatypes.sequences

import org.torsteinv.zetatypes.algebra._
import org.torsteinv.zetatypes.algebra.structures.{Complex}


/** A ring where the elements are [[SpecialAlgebraicSequence]]s with elements in some given [[org.torsteinv.zetatypes.algebra.Ring]] 
 *  @tparam E the type of [[org.torsteinv.zetatypes.algebra.RingElement]] that the [[SpecialAlgebraicSequence]]s consist of
 *  @constructor Creates a new ring of [[SpecialAlgebraicSequence]]s with elements in a given [[org.torsteinv.zetatypes.algebra.Ring]] 
 *  @param ring The [[org.torsteinv.zetatypes.algebra.Ring]] that the [[SpecialAlgebraicSequence]] elements belong to
 */
case class SpecialAlgebraicSequences[E <: RingElement[E]] (ring : Ring[E]) extends 
    RingClass[SpecialAlgebraicSequence[E]](
    new SpecialAlgebraicSequence(new ConstantSequence(ring.zero))(ring),
    new SpecialAlgebraicSequence(new ConstantSequence(ring.one ))(ring))
    
/** An element of [[SpecialAlgebraicSequences]], and a [[CachedSequence]] that starts with the multiplicative unit of the [[org.torsteinv.zetatypes.algebra.Ring]]
 *  @tparam T the type of [[org.torsteinv.zetatypes.algebra.RingElement]] that the elements of this sequence belong to
 *  @constructor Creates a new [[CachedSequence]] from a [[SequenceFactory]], that is forced to start with the multiplicative unit of the [[org.torsteinv.zetatypes.algebra.Ring]]
 *  @param factory The [[SequenceFactory]] that this sequence is created from
 *  @param ring The [[org.torsteinv.zetatypes.algebra.Ring]] that the elements of this [[CachedSequence]] belong to
 */
class SpecialAlgebraicSequence[E <: RingElement[E]] (factory : SequenceFactory[E])(implicit ring : Ring[E]) extends CachedSequence[E](factory) 
    with RingElement[SpecialAlgebraicSequence[E]] {
    
    override lazy val canonicalRing = SpecialAlgebraicSequences(ring)
    
    override def +(that : SpecialAlgebraicSequence[E]) = new SpecialAlgebraicSequence(new CombinedSequence(this, that)(_ + _)(true))
    
    override def *(that : SpecialAlgebraicSequence[E]) = new SpecialAlgebraicSequence(new CombinedSequence(this, that)(_ * _)(true))
    
    override def negation() = new SpecialAlgebraicSequence(new MappedSequence(this)(_.negation)(true))
    
    override def apply(index : Int) = if (index == 0) {ring.one} else {super.apply(index)}
}

/** A ring of [[SpecialAlgebraicSequence]]s with elements in the [[org.torsteinv.zetatypes.structures.Complex]] numbers
 */
object SpecialComplexSequences extends SpecialAlgebraicSequences(Complex)

/** An object to create a [[SpecialAlgebraicSequence]] with [[org.torsteinv.zetatypes.structures.Complex]] elements.
 */
object SpecialComplexSequence {
    def apply (factory : SequenceFactory[Complex]) = new SpecialAlgebraicSequence(factory)(Complex)
}
