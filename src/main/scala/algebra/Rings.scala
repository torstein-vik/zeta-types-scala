package io.zetatypes.algebra

package object Rings {
    
    trait Element[T <: Monoids.Element with Monoids.Additive[T] with Monoids.Multiplicative[T]] extends Monoids.Element with Monoids.Additive[T] with Monoids.Multiplicative[T]
    
    trait Ring[E <: Element[E]] {
        val additive       : Monoids.AdditiveMonoid[E]
        val multiplicative : Monoids.MultiplicativeMonoid[E]
    }
    
    // TODO: Fix name
    abstract class RingClass[E <: Element[E]] (val zero : E, val one : E) extends Ring[E]{ outer =>
        override object additive extends Monoids.AdditiveMonoid[E] {
            def identity = outer.zero
        }
        
        override object multiplicative extends Monoids.MultiplicativeMonoid[E] {
            def identity = outer.one
        }
    }

}