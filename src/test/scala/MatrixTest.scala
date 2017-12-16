package io.github.torsteinvik.zetatypes.test

import org.scalatest.FunSuite

class MatrixTest extends FunSuite{
    import io.github.torsteinvik.zetatypes.algebra.structures._
    import io.github.torsteinvik.zetatypes.algebra._
    
    
    {// Most basic tests
        implicit val ring : Ring[Integer] = Integers
        
        val X     = new Matrix(1, 2, Seq(Seq(1, 2)))
        val Xcopy = new Matrix(1, 2, Seq(Seq(1, 2)))
        val Y     = new Matrix(3, 3, Seq(Seq(1, 2, 3), Seq(2, 3, 4), Seq(3, 4, 5))
        val Ycopy = new Matrix(3, 3, Seq(Seq(1, 2, 3), Seq(2, 3, 4), Seq(3, 4, 5))
        val Z     = new Matrix(1000, 1000, for {i <- 1 to 1000} yield for {j <- 1 to 1000} yield i * j + 2)
        val Zcopy = new Matrix(1000, 1000, for {i <- 1 to 1000} yield for {j <- 1 to 1000} yield i * j + 2)
        
        
    }
    
    
}
