package org.torsteinv.zetatypes.algebra

package object Ringlike extends RinglikePackage

trait RinglikePackage {

    trait RingElement[T <: RingElement[T]] extends GroupElement with Additive[T] with Subtractive[T] with Multiplicative[T] {
        val canonicalRing : Ring[T]
    }

    trait Ring[E <: RingElement[E]] {
        val additive       : AdditiveGroup[E]
        val multiplicative : MultiplicativeMonoid[E]
        
        val zero : E
        val one : E
    }

    // TODO: Fix name
    abstract class RingClass[E <: RingElement[E]] (val zero : E, val one : E) extends Ring[E]{ outer =>
        override object additive extends AdditiveGroup[E] {
            def identity = outer.zero
        }
        
        override object multiplicative extends MultiplicativeMonoid[E] {
            def identity = outer.one
        }
    }

}
