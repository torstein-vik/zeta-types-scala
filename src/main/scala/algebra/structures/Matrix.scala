package io.github.torsteinvik.zetatypes.algebra.structures

import io.github.torsteinvik.zetatypes.algebra._

/** A ring where the elements are matricies with elements in some [[Ring]]
 *  @tparam T the type of [[RingElement]] that the matricies belong to.
 *  @constructor Creates a new ring of matricies with coefficients in some ring.
 *  @param ring The [[Ring]] that the elements of the matricies belong to.
 *  @param n The dimension of the matricies
 */
case class Matricies[T <: RingElement[T]] (ring : Ring[T], n : Int) extends 
    RingClass[Matrix[T]](
    new Matrix(n, n, for {i <- (1 to n)} yield for {j <- (1 to n)} yield ring.zero)(ring), 
    new Matrix(n, n, for {i <- (1 to n)} yield for {j <- (1 to n)} yield if i == j ring.one else ring.zero)(ring))
