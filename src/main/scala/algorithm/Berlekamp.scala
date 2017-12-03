package io.github.torsteinvik.zetatypes.algorithm

import io.github.torsteinvik.zetatypes.sequences._

package object Berlekamp {
    case class BerlekampException(msg : String) extends Exception(msg)
    case class BerlekampSpec(length : Int)
    case class BerlekampResult[T](recursion : Seq[T], initialdata : Seq[T])
    
    implicit val defaultSpec = BerlekampSpec(50)
    
    def berlekamp[T](input : Sequence[T])(implicit spec : BerlekampSpec) : Option[BerlekampResult[T]] = {
        if (input.length.map(_ < spec.length).filter(identity) == None) throw BerlekampException("Input sequence is not long enough for length specified in implicit spec! It's length must be at least " + spec.length + " but it is " + input.length)
        
        
    } 
}
