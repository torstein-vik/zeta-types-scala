package io.github.torsteinvik.zetatypes.algorithm

package object Berlekamp {
    case class BerlekampSpec(length : Int)
    case class BerlekampResult[T](recursion : Seq[T], initialdata : Seq[T])
    
}
