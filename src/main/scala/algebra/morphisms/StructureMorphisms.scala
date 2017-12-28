package io.github.torsteinvik.zetatypes.algebra.morphisms

import io.github.torsteinvik.zetatypes.algebra._
import io.github.torsteinvik.zetatypes.algebra.morphisms.DSL._
import io.github.torsteinvik.zetatypes.algebra.structures._

/** Object with various kinds of structural morphisms */
object StructureMorphisms {
    implicit def fractionInclusion[E <: RingElement[E]](implicit ring : Ring[E]) : (E ^-> Fraction[E]) = new Monomorphism[E, Fraction[E]]{
        def apply(x : E) = new Fraction(x, ring.one)(ring)
    }
    
    implicit def complexInclusion[E <: RingElement[E]](implicit ring : Ring[E]) : (E ^-> ComplexNumber[E]) = new Monomorphism[E, ComplexNumber[E]]{
        def apply(x : E) = new ComplexNumber(x, ring.zero)(ring)
    }
    
}
