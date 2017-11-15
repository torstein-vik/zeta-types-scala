package org.torsteinv.zetatypes.test

import org.scalatest.FunSuite

import org.torsteinv.zetatypes.algebra.structures.{Rational, Complex}

class PolynomialTest extends FunSuite{
    import org.torsteinv.zetatypes.algebra.structures._
    import org.torsteinv.zetatypes.algebra._
    
    
    {// Most basic tests
        implicit val ring : Ring[Integer] = Integers
        
        val X     = new Polynomial(Seq((Integer(1), 1), (Integer(2), 2)))
        val Xcopy = new Polynomial(Seq((Integer(1), 1), (Integer(2), 2)))
        val Y     = new Polynomial(Seq((Integer(3), 2), (Integer(2), 4)))
        val Ycopy = new Polynomial(Seq((Integer(3), 2), (Integer(2), 4)))
        val Z     = new Polynomial(Seq((Integer(2), 0), (Integer(2), 1), (Integer(3), 2)))
        val Zcopy = new Polynomial(Seq((Integer(2), 0), (Integer(2), 1), (Integer(3), 2)))
        
        test ("Polynomial basic equality") {
            assert(X !== Y)
            assert(Y !== Z)
            assert(Z !== X)
            
            assert(X === Xcopy)
            assert(Y === Ycopy)
            assert(Z === Zcopy)
        }
        
        // TODO: more comprehensive toString test
        test ("Polynomial basic toString") {
            assert(X.toString() === "x + 2x^2")
            assert(Y.toString() === "3x^2 + 2x^4")
            assert(Z.toString() === "2 + 2x + 3x^2")
            
            assert(Polynomials(Integers).one.toString() === "1")
            assert(Polynomials(Integers).zero.toString() === "0")
        }
    }
    
    {// Cancellation tests
        implicit val ring : Ring[Integer] = Integers
        
        val X     = new Polynomial(Seq((Integer(2), 1), (Integer(1), 1), (Integer(3), 1)))
        val Xsimp = new Polynomial(Seq((Integer(6), 1)))
        val Y     = new Polynomial(Seq((Integer(0), 5), (Integer(10), 1), (Integer(2), 0)))
        val Ysimp = new Polynomial(Seq((Integer(2), 0), (Integer(10), 1)))
        val Z     = new Polynomial(Seq((Integer(3), 2), (Integer(3), 44), (Integer(3), 44)))
        val Zsimp = new Polynomial(Seq((Integer(3), 2), (Integer(6), 44)))
        val W     = new Polynomial(Seq((Integer(-3), 1), (Integer(1), 1), (Integer(2), 1), (Integer(3), 3)))
        val Wsimp = new Polynomial(Seq((Integer(3), 3)))
        
        test ("Cleanup works") {
            assert(X.cleanup.elements === Xsimp.elements)
            assert(Y.cleanup.elements === Ysimp.elements)
            assert(Z.cleanup.elements === Zsimp.elements)
            assert(W.cleanup.elements === Wsimp.elements)
        }
        
        test ("Equality works") {
            assert(X === Xsimp)
            assert(Y === Ysimp)
            assert(Z === Zsimp)
            assert(W === Wsimp)
            
            assert(X + Ysimp === Xsimp + Y)
            assert(Y + Zsimp === Ysimp + Z)
            assert(Z + Wsimp === Zsimp + W)
            assert(W + Xsimp === Wsimp + X)
            
            assert(X + Y + Z + W + Y + Y === Xsimp + Y + Z + Wsimp + Ysimp + Y)
        }
        
    }
    
}
