package org.torsteinv.zetatypes.algebra.structures

import org.torsteinv.zetatypes.algebra._

/** A ring where the elements are [[Polynomial]]s with elements in some given [[org.torsteinv.zetatypes.algebra.Ring]] 
 *  @tparam E the type of [[org.torsteinv.zetatypes.algebra.RingElement]] that the [[Polynomial]]s consist of
 *  @constructor Creates a new ring of [[Polynomial]]s with coefficients in a given [[org.torsteinv.zetatypes.algebra.Ring]] 
 *  @param ring The [[org.torsteinv.zetatypes.algebra.Ring]] that the [[Polynomial]] coefficients belong to
 */
case class Polynomials[E <: RingElement](ring : Ring[E]) extends 
    RingClass[Polynomials[E]](
    new Polynomial(Seq.empty)(monoid), 
    new Polynomial(Seq((ring.one, 0)))(monoid))


/** An element of [[Polynomials]]
 *  @tparam E the type of [[org.torsteinv.zetatypes.algebra.RingElement]] that the coefficients of this polynomial belong to
 *  @constructor Creates a new Polynomial from a list of terms (coefficient and exponent)
 *  @param elements The coefficient-exponent pairs that build up the terms of this polynomial
 *  @param ring The [[org.torsteinv.zetatypes.algebra.Ring]] that the coefficients belong to
 */
class Polynomial[E <: RingElement] (val elements : Seq[(E, BigInt)])(implicit ring : Ring[E]) extends 
    RingElement[Polynomial[E]] {
}
