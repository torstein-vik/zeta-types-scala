package io.github.torsteinvik.zetatypes.algebra.morphisms

import io.github.torsteinvik.zetatypes.algebra._

package object DSL {
    /** Synonym for [[Homomorphism]]*/
    type -->[A <: AlgebraicElement, B <: AlgebraicElement] = Homomorphism[A, B]
    /** Synonym for [[Epimorphism]]*/
    type ^->[A <: AlgebraicElement, B <: AlgebraicElement] = Epimorphism[A, B]
    /** Synonym for [[Monomorphism]]*/
    type ->>[A <: AlgebraicElement, B <: AlgebraicElement] = Monomorphism[A, B]
    /** Synonym for [[Isomorphism]]*/
    type ~~>[A <: AlgebraicElement, B <: AlgebraicElement] = Isomorphism[A, B]
    
}

package DSL {
    
    /** Dummy class so that scaladoc shows DSL */
    protected class DummyClass
}
