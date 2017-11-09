package org.torsteinv.zetatypes

import org.torsteinv.zetatypes.algebra.structures.{Complex}

/** Provides classes for creating and working with (potentially infinite) 
 *  sequences, as well as algebraic instances for normal and special sequences.
 *  Also provides some implicit methods for simplifying syntax.
 */
package object sequences {
    type ComplexSequence = AlgebraicSequence[Complex]
    type SpecialComplexSequence = SpecialAlgebraicSequence[Complex]
}
