package io.github.torsteinvik.zetatypes.algorithm

package object Berlekamp {
    case class BerlekampResult[T](recursion : Seq[T], initialdata : Seq[T])
    
}
