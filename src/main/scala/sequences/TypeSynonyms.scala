package org.torsteinv.zetatypes

import org.torsteinv.zetatypes.algebra.structures.{Complex}
    
package object sequences {
    type ComplexSequence = AlgebraicSequence[Complex]
    type SpecialComplexSequence = SpecialAlgebraicSequence[Complex]
}