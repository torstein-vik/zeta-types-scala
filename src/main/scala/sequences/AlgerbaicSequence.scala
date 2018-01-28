package io.github.torsteinvik.zetatypes.sequences

import io.github.torsteinvik.zetatypes.algebra._
import io.github.torsteinvik.zetatypes.algebra.structures.{Complex}


/** A ring where the elements are [[AlgebraicSequence]]s with elements in some given [[io.github.torsteinvik.zetatypes.algebra.Ring]] 
 *  @tparam E the type of [[io.github.torsteinvik.zetatypes.algebra.RingElement]] that the [[AlgebraicSequence]]s consist of
 *  @constructor Creates a new ring of [[AlgebraicSequence]]s with elements in a given [[io.github.torsteinvik.zetatypes.algebra.Ring]] 
 *  @param ring The [[io.github.torsteinvik.zetatypes.algebra.Ring]] that the [[AlgebraicSequence]] elements belong to
 */
case class AlgebraicSequences[E <: RingElement[E]] (ring : Ring[E]) extends 
    RingClass[AlgebraicSequence[E]](
    new AlgebraicSequence(new ConstantSequence(ring.zero))(ring),
    new AlgebraicSequence(new ConstantSequence(ring.one ))(ring))
    
/** An element of [[AlgebraicSequences]], and a [[CachedSequence]]
 *  @tparam T the type of [[io.github.torsteinvik.zetatypes.algebra.RingElement]] that the elements of this sequence belong to
 *  @constructor Creates a new [[CachedSequence]] from a [[SequenceFactory]]
 *  @param factory The [[SequenceFactory]] that this sequence is created from
 *  @param ring The [[io.github.torsteinvik.zetatypes.algebra.Ring]] that the elements of this [[CachedSequence]] belong to
 */
class AlgebraicSequence[E <: RingElement[E]] (factory : SequenceFactory[E])(implicit ring : Ring[E]) extends CachedSequence[E](factory) 
    with RingElement[AlgebraicSequence[E]] {
    override lazy val canonicalRing = AlgebraicSequences(ring)
    
    override def +(that : AlgebraicSequence[E]) = new AlgebraicSequence(new CombinedSequence(this, that)(_ + _)(true))
    
    override def *(that : AlgebraicSequence[E]) = new AlgebraicSequence(new CombinedSequence(this, that)(_ * _)(true))
    
    override def negation = new AlgebraicSequence(new MappedSequence(this)(_.negation)(true))
}

/** A ring of [[AlgebraicSequence]]s with elements in the [[io.github.torsteinvik.zetatypes.algebra.structures.Complex]] numbers
 */
object ComplexSequences extends AlgebraicSequences(Complex)

/** An object to create an [[AlgebraicSequence]] with [[io.github.torsteinvik.zetatypes.algebra.structures.Complex]] elements.
 */
object ComplexSequence{
    def apply (factory : SequenceFactory[Complex]) = new AlgebraicSequence(factory)(Complex)
}
