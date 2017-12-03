package io.github.torsteinvik.zetatypes.algorithm

package object Berlekamp {
    case class BerlekampSpec(length : Int)
    case class BerlekampResult[T](recursion : Seq[T], initialdata : Seq[T])
    
    implicit val defaultSpec = BerlekampSpec(50)
    
    def berlekamp[T](input : Sequence[T])(implicit spec : BerlekampSpec) : Option[BerlekampResult[T]] = {
        
    } 
}
