package io.github.torsteinvik.zetatypes.algebra.morphisms

import io.github.torsteinvik.zetatypes.algebra._
import io.github.torsteinvik.zetatypes.algebra.morphisms.DSL._
import io.github.torsteinvik.zetatypes.algebra.structures._

/** Object with various kinds of structural morphisms */
object StructureMorphisms {
    /** Natural inclusion of a ring into its ring of fractions */
    implicit def fractionInclusion[E <: RingElement[E]](implicit ring : Ring[E]) : (E ^-> Fraction[E]) = new Monomorphism[E, Fraction[E]]{
        def apply(x : E) = new Fraction(x, ring.one)(ring)
    }
    
    /** Natural inclusion of a field into its field of complex numbers */
    implicit def complexInclusion[E <: FieldElement[E]](implicit ring : Field[E]) : (E ^-> ComplexNumber[E]) = new Monomorphism[E, ComplexNumber[E]]{
        def apply(x : E) = new ComplexNumber(x, ring.zero)(ring)
    }
    
    /** Natural inclusion of a ring into the ring of polynomials with coefficients in that ring */
    implicit def polynomialInclusion[E <: RingElement[E]](implicit ring : Ring[E]) : (E ^-> Polynomial[E]) = new Monomorphism[E, Polynomial[E]]{
        def apply(x : E) = new Polynomial(Seq((x, 0)))(ring)
    }
    
}
