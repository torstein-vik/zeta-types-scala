package org.torsteinv.zetatypes

import org.torsteinv.zetatypes.algebra.structures.{Complex}

/** Provides classes for creating and working with (potentially infinite) 
 *  sequences, as well as algebraic instances for normal and special sequences.
 *  Also provides some implicit methods for simplifying syntax.
 *  @author Torstein Vik
 */
package object sequences {
    /** Type synonym for [[AlgebraicSequence]] with [[org.torsteinv.zetatypes.algebra.structures.Complex]] elements */
    type ComplexSequence = AlgebraicSequence[Complex]
    
    /** Type synonym for [[SpecialAlgebraicSequence]] with [[org.torsteinv.zetatypes.algebra.structures.Complex]] elements */
    type SpecialComplexSequence = SpecialAlgebraicSequence[Complex]
}
