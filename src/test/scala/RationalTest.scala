package io.github.torsteinvik.zetatypes.test

import org.scalatest.FunSuite

import io.github.torsteinvik.zetatypes.algebra._
import io.github.torsteinvik.zetatypes.algebra.structures._

import io.github.torsteinvik.zetatypes.algebra.structures.DSL._

class RationalTest extends FunSuite {
        
    val (a, b, c, d) = (2, -3, 7, 4)
    
    test ("zero as denominator test") {
        assertThrows[AlgebraicException] {
            Rational(1, 0)
        } 
    }
    
    test ("rational toString") {
        assert(Rational(a, b).toString === a.toString + "/" + b.toString)
        assert(Rational(b, c).toString === b.toString + "/" + c.toString)
        assert(Rational(c, d).toString === c.toString + "/" + d.toString)
        assert(Rational(d, a).toString === d.toString + "/" + a.toString)
        
        assert(Rational(a).toString === a.toString)
        assert(Rational(b).toString === b.toString)
        assert(Rational(c).toString === c.toString)
        assert(Rational(d).toString === d.toString)
    }
    
    test ("equality test") {
        assert(Rational(a, b) === Rational(a, b))
        assert(Rational(b, c) === Rational(b, c))
        assert(Rational(c, d) === Rational(c, d))
        assert(Rational(a, b) !== Rational(a + 1, b))
        assert(Rational(a, b) !== Rational(a - 1, b))
        assert(Rational(a, b) !== Rational(a + 3, b))
        
        assert(Rational(2, 4) === Rational(1, 2))
        assert(Rational(4, 8) === Rational(1, 2))
        assert(Rational(2, 8) === Rational(1, 4))
        assert(Rational(-2, -4) === Rational(1, 2))
        
        assert(Rational(a, a) === Rational(1))
        assert(Rational(b, b) === Rational(1))
        assert(Rational(c, c) === Rational(1))
        assert(Rational(d, d) === Rational(1))
    }
    
    test ("zero and one test") {
        assert(Element.zero[Rational] === Rational(0))
        assert(Element.one[Rational]  === Rational(1))
    }
    
    test ("divide by zero test") {
        assertThrows[AlgebraicException]{
            Rationals.multiplicative.invert(Rational(0, 1))
        }
    }
    
    test ("notation adidition test") {
        assert(Rational(a) + Rational(b) === Rational(a + b))
        assert(Rational(b) + Rational(c) === Rational(b + c))
        assert(Rational(c) + Rational(d) === Rational(c + d))
        assert(Rational(d) + Rational(a) === Rational(d + a))
        
        assert(Rational(a, b) + Rational(c, d) === Rational(a * d + b * c, b * d))
        assert(Rational(b, c) + Rational(d, a) === Rational(b * a + c * d, c * a))
    }
    
    test ("notation multiplication test") {
        assert(Rational(a) * Rational(b) === Rational(a * b))
        assert(Rational(b) * Rational(c) === Rational(b * c))
        assert(Rational(c) * Rational(d) === Rational(c * d))
        assert(Rational(d) * Rational(a) === Rational(d * a))
        
        assert(Rational(a, b) * Rational(c, d) === Rational(a * c, b * d))
        assert(Rational(b, c) * Rational(d, a) === Rational(b * d, c * a))
    }
    
    test ("notation negation test") {
        assert(-Rational(a, b) === Rational(-a, b))
        assert(-Rational(b, c) === Rational(-b, c))
        assert(-Rational(c, d) === Rational(-c, d))
        assert(-Rational(d, a) === Rational(-d, a))
    }
    
    test ("notation inverse test") {
        assert(~Rational(a, b) === Rational(b, a))
        assert(~Rational(b, c) === Rational(c, b))
        assert(~Rational(c, d) === Rational(d, c))
        assert(~Rational(d, a) === Rational(a, d))
    }
    
    test ("subtraction test") {
        assert(Rational(a) - Rational(b) === Rational(a - b))
        assert(Rational(b) - Rational(c) === Rational(b - c))
        assert(Rational(c) - Rational(d) === Rational(c - d))
        assert(Rational(d) - Rational(a) === Rational(d - a))
        
        assert(Rational(a, b) - Rational(c, d) === Rational(a * d - b * c, b * d))
        assert(Rational(b, c) - Rational(d, a) === Rational(b * a - c * d, c * a))
    }
    
    test ("division test") {
        assert(Rational(a) / Rational(b) === Rational(a, b))
        assert(Rational(b) / Rational(c) === Rational(b, c))
        assert(Rational(c) / Rational(d) === Rational(c, d))
        assert(Rational(d) / Rational(a) === Rational(d, a))
        
        assert(Rational(a, b) / Rational(c, d) === Rational(a * d, b * c))
        assert(Rational(b, c) / Rational(d, a) === Rational(a * b, d * c))
    }
    
        
    test ("Rational exponentiation") {
        assert((Rational(a, b) ** 0) === Rational(1, 1))
        assert((Rational(b, c) ** 0) === Rational(1, 1))
        assert((Rational(c, d) ** 0) === Rational(1, 1))
        assert((Rational(d, a) ** 0) === Rational(1, 1))
        assert((Rational(a, b) ** 1) === Rational(a, b))
        assert((Rational(b, c) ** 1) === Rational(b, c))
        assert((Rational(c, d) ** 1) === Rational(c, d))
        assert((Rational(d, a) ** 1) === Rational(d, a))
        assert((Rational(a, b) ** 2) === Rational(a * a, b * b))
        assert((Rational(b, c) ** 2) === Rational(b * b, c * c))
        assert((Rational(c, d) ** 2) === Rational(c * c, d * d))
        assert((Rational(d, a) ** 2) === Rational(d * d, a * a))
        assert((Rational(a, b) ** 3) === Rational(a * a * a, b * b * b))
        assert((Rational(b, c) ** 3) === Rational(b * b * b, c * c * c))
        assert((Rational(c, d) ** 3) === Rational(c * c * c, d * d * d))
        assert((Rational(d, a) ** 3) === Rational(d * d * d, a * a * a))
        
        assert((Rational(a, b) ** -1) === Rational(b, a))
        assert((Rational(b, c) ** -1) === Rational(c, b))
        assert((Rational(c, d) ** -1) === Rational(d, c))
        assert((Rational(d, a) ** -1) === Rational(a, d))
        assert((Rational(a, b) ** -2) === Rational(b * b, a * a))
        assert((Rational(b, c) ** -2) === Rational(c * c, b * b))
        assert((Rational(c, d) ** -2) === Rational(d * d, c * c))
        assert((Rational(d, a) ** -2) === Rational(a * a, d * d))
        assert((Rational(a, b) ** -3) === Rational(b * b * b, a * a * a))
        assert((Rational(b, c) ** -3) === Rational(c * c * c, b * b * b))
        assert((Rational(c, d) ** -3) === Rational(d * d * d, c * c * c))
        assert((Rational(d, a) ** -3) === Rational(a * a * a, d * d * d))
        
        assert(Rationals.multiplicative.repeated(Rational(a, b), -3) === Rational(b * b * b, a * a * a))
    }
    
    
    test ("Rational module multiplication") {
        assert((Rational(a, b) ++ 0) === Rational(0, 1))
        assert((Rational(b, c) ++ 0) === Rational(0, 1))
        assert((Rational(c, d) ++ 0) === Rational(0, 1))
        assert((Rational(d, a) ++ 0) === Rational(0, 1))
        assert((Rational(a, b) ++ 1) === Rational(a, b))
        assert((Rational(b, c) ++ 1) === Rational(b, c))
        assert((Rational(c, d) ++ 1) === Rational(c, d))
        assert((Rational(d, a) ++ 1) === Rational(d, a))
        assert((Rational(a, b) ++ 2) === Rational(a + a, b))
        assert((Rational(b, c) ++ 2) === Rational(b + b, c))
        assert((Rational(c, d) ++ 2) === Rational(c + c, d))
        assert((Rational(d, a) ++ 2) === Rational(d + d, a))
        assert((Rational(a, b) ++ 3) === Rational(a + a + a, b))
        assert((Rational(b, c) ++ 3) === Rational(b + b + b, c))
        assert((Rational(c, d) ++ 3) === Rational(c + c + c, d))
        assert((Rational(d, a) ++ 3) === Rational(d + d + d, a))
        
        assert((Rational(a, b) ++ -1) === Rational(-a, b))
        assert((Rational(b, c) ++ -1) === Rational(-b, c))
        assert((Rational(c, d) ++ -1) === Rational(-c, d))
        assert((Rational(d, a) ++ -1) === Rational(-d, a))
        assert((Rational(a, b) ++ -2) === Rational(-a - a, b))
        assert((Rational(b, c) ++ -2) === Rational(-b - b, c))
        assert((Rational(c, d) ++ -2) === Rational(-c - c, d))
        assert((Rational(d, a) ++ -2) === Rational(-d - d, a))
        assert((Rational(a, b) ++ -3) === Rational(-a - a - a, b))
        assert((Rational(b, c) ++ -3) === Rational(-b - b - b, c))
        assert((Rational(c, d) ++ -3) === Rational(-c - c - c, d))
        assert((Rational(d, a) ++ -3) === Rational(-d - d - d, a))
        
        assert(Rationals.additive.repeated(Rational(a, b), -3) === Rational(-a - a - a, b))
    }
    
    
}
