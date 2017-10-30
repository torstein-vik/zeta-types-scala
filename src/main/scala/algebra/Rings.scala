package io.zetatypes.algebra

package object Rings {
    
    trait Element[T <: MonoidElement with Additive[T] with Multiplicative[T]] extends MonoidElement with Additive[T] with Multiplicative[T]
    
    trait Ring[E <: Element[E]] {
        val additive       : AdditiveMonoid[E]
        val multiplicative : MultiplicativeMonoid[E]
    }
    
    // TODO: Fix name
    abstract class RingClass[E <: Element[E]] (val zero : E, val one : E) extends Ring[E]{ outer =>
        override object additive extends AdditiveMonoid[E] {
            def identity = outer.zero
        }
        
        override object multiplicative extends MultiplicativeMonoid[E] {
            def identity = outer.one
        }
    }

}