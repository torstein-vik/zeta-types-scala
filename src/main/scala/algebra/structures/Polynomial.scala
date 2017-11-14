package org.torsteinv.zetatypes.algebra.structures

import org.torsteinv.zetatypes.algebra._

case class Polynomials[E <: RingElement](ring : Ring[E]) extends 
    RingClass[Polynomials[E]](
    new Polynomial(Seq.empty)(monoid), 
    new Polynomial(Seq((monoid.identity, 0)))(monoid))
