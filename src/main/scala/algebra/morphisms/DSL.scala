package io.github.torsteinvik.zetatypes.algebra.morphisms

import io.github.torsteinvik.zetatypes.algebra._

package object DSL {
    type -->[A <: AlgebraicElement, B <: AlgebraicElement] = Homomorphism[A, B]
    type ^->[A <: AlgebraicElement, B <: AlgebraicElement] = Epimorphism[A, B]
    type ->>[A <: AlgebraicElement, B <: AlgebraicElement] = Monomorphism[A, B]
    type ~~>[A <: AlgebraicElement, B <: AlgebraicElement] = Isomorphism[A, B]
    
}

package DSL {
    
    /** Dummy class so that scaladoc shows DSL */
    protected class DummyClass
}
