package org.torsteinv.zetatypes.test

import org.scalatest.FunSuite

import org.torsteinv.zetatypes.algebra.structures.{Rational, Complex}

class PolynomialTest extends FunSuite{
    import org.torsteinv.zetatypes.algebra.structures._
    import org.torsteinv.zetatypes.algebra._
    
    
    {// Most basic tests
        implicit val monoid : Monoid[Integer] = Integers.multiplicative
        
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
            assert(Y.toString() === "3x^3 + 2x^4")
            assert(Z.toString() === "2 + 2x + 3x^2")
            
            assert(Polynomials(Integers).one.toString() === "1")
            assert(Polynomials(Integers).zero.toString() === "0")
        }
    }
    
}
