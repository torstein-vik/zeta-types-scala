package io.github.torsteinvik.zetatypes.algebra.structures

import io.github.torsteinvik.zetatypes.algebra._

case class Matricies[T <: RingElement[T]] (ring : Ring[T], n : Int) extends 
    RingClass[Matrix[T]](
    new Matrix(n, n, for {i <- (1 to n)} yield for {j <- (1 to n)} yield ring.zero)(ring), 
    new Matrix(n, n, for {i <- (1 to n)} yield for {j <- (1 to n)} yield if i == j ring.one else ring.zero)(ring))
