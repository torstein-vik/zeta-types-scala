package io.zetatypes.algebra.structures

import scala.language.implicitConversions

import io.zetatypes.algebra._

package object DSL {
    implicit def toInteger (x : Int) : Integer = Integer(x)
    
    implicit def multiplicativeMonoid[E <: RingElement[E]] (ring : Ring[E]) : Monoid[E] = ring.multiplicative
}