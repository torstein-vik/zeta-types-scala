package io.github.torsteinvik.zetatypes.algorithm

import io.github.torsteinvik.zetatypes.sequences._

package object Berlekamp {
    case class BerlekampSpec(length : Int)
    case class BerlekampResult[T](recursion : Seq[T], initialdata : Seq[T])
    
    implicit val defaultSpec = BerlekampSpec(50)
    
    def berlekamp[T](input : Sequence[T])(implicit spec : BerlekampSpec) : Option[BerlekampResult[T]] = {
        
    } 
}
