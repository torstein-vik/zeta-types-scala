package io.github.torsteinvik.zetatypes.test

import org.scalatest.FunSuite

import io.github.torsteinvik.zetatypes.algebra._
import io.github.torsteinvik.zetatypes.algebra.structures._

import io.github.torsteinvik.zetatypes.algebra.structures.DSL._
    
class ComplexTest extends FunSuite {
    
    val (a, b, c, d) = (Rational(2), Rational(-3, 2), Rational(7, 5), Rational(4, 3))
    
    test ("Complex number toString") {
        assert(Complex(a, b).toString === a.toString + " + " + b.toString + " i")
        assert(Complex(b, c).toString === b.toString + " + " + c.toString + " i")
        assert(Complex(c, d).toString === c.toString + " + " + d.toString + " i")
        assert(Complex(d, a).toString === d.toString + " + " + a.toString + " i")
        
        assert(Complex(a).toString === a.toString)
        assert(Complex(b).toString === b.toString)
        assert(Complex(c).toString === c.toString)
        assert(Complex(d).toString === d.toString)
        
        assert(Complex(0, a).toString === a.toString + " i")
        assert(Complex(0, b).toString === b.toString + " i")
        assert(Complex(0, c).toString === c.toString + " i")
        assert(Complex(0, d).toString === d.toString + " i")
        
        assert(Complex(a, 1).toString === a.toString + " + i")
        assert(Complex(b, 1).toString === b.toString + " + i")
        assert(Complex(c, 1).toString === c.toString + " + i")
        assert(Complex(d, 1).toString === d.toString + " + i")
        
        assert(Complex(0, 1).toString === "i")
    }
    
    test ("equality test") {
        assert(Complex(a, b) === Complex(a, b))
        assert(Complex(b, c) === Complex(b, c))
        assert(Complex(c, d) === Complex(c, d))
        assert(Complex(c, d) === Complex(c, d))
        
        assert(Complex(a, b) !== Complex(a + Rational(1), b))
        assert(Complex(a, b) !== Complex(a - Rational(1), b))
        assert(Complex(a, b) !== Complex(a + Rational(3), b))
        assert(Complex(a, b) !== Complex(a - Rational(3), b))
        
        assert(Complex(a, b) !== Complex(a, b + Rational(1)))
        assert(Complex(a, b) !== Complex(a, b - Rational(1)))
        assert(Complex(a, b) !== Complex(a, b + Rational(3)))
        assert(Complex(a, b) !== Complex(a, b - Rational(3)))
        
        assert(Complex(a) === Complex(a, Rationals.zero))
        assert(Complex(a) === Complex(a, Rationals.zero))
        assert(Complex(a) === Complex(a, Rationals.zero))
        assert(Complex(a) === Complex(a, Rationals.zero))
        
        assert(Complex(Rational(2,2), Rational(3,3)) === Complex(Rational(1), Rational(1)))
        assert(Complex(Rational(4,2), Rational(3,6)) === Complex(Rational(2), Rational(1, 2)))
        assert(Complex(Rational(2,12), Rational(9,3)) === Complex(Rational(1, 6), Rational(3)))
        assert(Complex(Rational(10,2), Rational(15,3)) === Complex(Rational(5), Rational(5)))
    }
    
    test ("identities test") {
        assert(Complex.additive.identity === Complex(Rational(0)))
        assert(Complex.multiplicative.identity === Complex(Rational(1)))
    }
    
    test ("zero and one test") {
        assert(Complex.zero === Complex(Rational(0)))
        assert(Complex.one  === Complex(Rational(1)))
    }
    
    test ("additive inverse test") {
        assert(Complex.additive.invert(Complex(a, b)) === Complex(-a, -b))
        assert(Complex.additive.invert(Complex(b, c)) === Complex(-b, -c))
        assert(Complex.additive.invert(Complex(c, d)) === Complex(-c, -d))
        assert(Complex.additive.invert(Complex(d, a)) === Complex(-d, -a))
    }
    
    test ("multiplicative inverse test") {
        assert(Complex.multiplicative.invert(Complex(a, b)) === Complex(a/(a*a + b*b), -b/(a*a + b*b)))
        assert(Complex.multiplicative.invert(Complex(b, c)) === Complex(b/(b*b + c*c), -c/(b*b + c*c)))
        assert(Complex.multiplicative.invert(Complex(c, d)) === Complex(c/(c*c + d*d), -d/(c*c + d*d)))
        assert(Complex.multiplicative.invert(Complex(d, a)) === Complex(d/(d*d + a*a), -a/(d*d + a*a)))
    }
    
    test ("divide by zero test") {
        assertThrows[AlgebraicException]{
            Complex.multiplicative.invert(Complex(0, 0))
        }
    }
    
    test ("addition test") {
        assert(Complex.additive.combine(Complex(a), Complex(b)) === Complex(a + b))
        assert(Complex.additive.combine(Complex(b), Complex(c)) === Complex(b + c))
        assert(Complex.additive.combine(Complex(c), Complex(d)) === Complex(c + d))
        assert(Complex.additive.combine(Complex(d), Complex(a)) === Complex(d + a))
        
        assert(Complex.additive.combine(Complex(a, b), Complex(c, d)) === Complex(a + c, b + d))
        assert(Complex.additive.combine(Complex(b, c), Complex(d, a)) === Complex(b + d, c + a))
    }
    
    test ("multiplication test") {
        assert(Complex.multiplicative.combine(Complex(a), Complex(b)) === Complex(a * b))
        assert(Complex.multiplicative.combine(Complex(b), Complex(c)) === Complex(b * c))
        assert(Complex.multiplicative.combine(Complex(c), Complex(d)) === Complex(c * d))
        assert(Complex.multiplicative.combine(Complex(d), Complex(a)) === Complex(d * a))
        
        assert(Complex.multiplicative.combine(Complex(a, b), Complex(c, d)) === Complex(a * c - b * d, a * d + b * c))
        assert(Complex.multiplicative.combine(Complex(b, c), Complex(d, a)) === Complex(b * d - c * a, b * a + c * d))
    }
    
    test ("notation adidition test") {
        assert(Complex(a) + Complex(b) === Complex(a + b))
        assert(Complex(b) + Complex(c) === Complex(b + c))
        assert(Complex(c) + Complex(d) === Complex(c + d))
        assert(Complex(d) + Complex(a) === Complex(d + a))
        
        assert(Complex(a, b) + Complex(c, d) === Complex(a + c, b + d))
        assert(Complex(b, c) + Complex(d, a) === Complex(b + d, c + a))
    }
    
    test ("notation multiplication test") {
        assert(Complex(a) * Complex(b) === Complex(a * b))
        assert(Complex(b) * Complex(c) === Complex(b * c))
        assert(Complex(c) * Complex(d) === Complex(c * d))
        assert(Complex(d) * Complex(a) === Complex(d * a))
        
        assert(Complex(a, b) * Complex(c, d) === Complex(a * c - b * d, a * d + b * c))
        assert(Complex(b, c) * Complex(d, a) === Complex(b * d - c * a, b * a + c * d))
    }
    
    test ("notation negation test") {
        assert(-Complex(a, b) === Complex(-a, -b))
        assert(-Complex(b, c) === Complex(-b, -c))
        assert(-Complex(c, d) === Complex(-c, -d))
        assert(-Complex(d, a) === Complex(-d, -a))
    }
    
    test ("notation inverse test") {
        assert(/Complex(a, b) === Complex(a/(a*a + b*b), -b/(a*a + b*b)))
        assert(/Complex(b, c) === Complex(b/(b*b + c*c), -c/(b*b + c*c)))
        assert(/Complex(c, d) === Complex(c/(c*c + d*d), -d/(c*c + d*d)))
        assert(/Complex(d, a) === Complex(d/(d*d + a*a), -a/(d*d + a*a)))
    }
    
    test ("subtraction test") {
        assert(Complex(a) - Complex(b) === Complex(a - b))
        assert(Complex(b) - Complex(c) === Complex(b - c))
        assert(Complex(c) - Complex(d) === Complex(c - d))
        assert(Complex(d) - Complex(a) === Complex(d - a))
        
        assert(Complex(a, b) - Complex(c, d) === Complex(a - c, b - d))
        assert(Complex(b, c) - Complex(d, a) === Complex(b - d, c - a))
    }
    
    test ("division test") {
        assert(Complex(a, 0) / Complex(b, 0) === Complex(a/b, 0))
        assert(Complex(b, 0) / Complex(0, c) === Complex(0,-b/c))
        assert(Complex(0, c) / Complex(d, 0) === Complex(0,c/d))
        assert(Complex(0, d) / Complex(0, a) === Complex(d/a, 0))
        
        assert(Complex(a, b) / Complex(c, d) === Complex((a*c + b*d)/(c*c + d*d), (b*c - a*d)/(c*c + d*d)))
        assert(Complex(b, c) / Complex(d, a) === Complex((b*d + c*a)/(d*d + a*a), (c*d - b*a)/(d*d + a*a)))
        assert(Complex(c, d) / Complex(a, b) === Complex((c*a + d*b)/(a*a + b*b), (d*a - c*b)/(a*a + b*b)))
        assert(Complex(d, a) / Complex(b, c) === Complex((d*b + a*c)/(b*b + c*c), (a*b - d*c)/(b*b + c*c)))
    }
        
    test ("Complex exponentiation") {
        assert((Complex(a, b) ** 1) === Complex(a, b))
        assert((Complex(b, c) ** 1) === Complex(b, c))
        assert((Complex(c, d) ** 1) === Complex(c, d))
        assert((Complex(d, a) ** 1) === Complex(d, a))
        assert((Complex(a, b) ** 2) === Complex(a * a - b * b, 2 * a * b))
        assert((Complex(b, c) ** 2) === Complex(b * b - c * c, 2 * b * c))
        assert((Complex(c, d) ** 2) === Complex(c * c - d * d, 2 * c * d))
        assert((Complex(d, a) ** 2) === Complex(d * d - a * a, 2 * d * a))
        assert((Complex(a, b) ** 3) === Complex(a * a * a - 3 * a * b * b, 3 * a * a * b - b * b * b))
        assert((Complex(b, c) ** 3) === Complex(b * b * b - 3 * b * c * c, 3 * b * b * c - c * c * c))
        assert((Complex(c, d) ** 3) === Complex(c * c * c - 3 * c * d * d, 3 * c * c * d - d * d * d))
        assert((Complex(d, a) ** 3) === Complex(d * d * d - 3 * d * a * a, 3 * d * d * a - a * a * a))
        
        assertThrows[AlgebraicException] {
            Complex(1, 2) ** 0
        }
        
        assertThrows[AlgebraicException] {
            Complex(1, 2) ** (-1)
        }
    }
    
    test ("Complex module multiplication") {
        assert((Complex(a, b) ++ 1) === Complex(a, b))
        assert((Complex(b, c) ++ 1) === Complex(b, c))
        assert((Complex(c, d) ++ 1) === Complex(c, d))
        assert((Complex(d, a) ++ 1) === Complex(d, a))
        assert((Complex(a, b) ++ 2) === Complex(a + a, b + b))
        assert((Complex(b, c) ++ 2) === Complex(b + b, c + c))
        assert((Complex(c, d) ++ 2) === Complex(c + c, d + d))
        assert((Complex(d, a) ++ 2) === Complex(d + d, a + a))
        assert((Complex(a, b) ++ 3) === Complex(a + a + a, b + b + b))
        assert((Complex(b, c) ++ 3) === Complex(b + b + b, c + c + c))
        assert((Complex(c, d) ++ 3) === Complex(c + c + c, d + d + d))
        assert((Complex(d, a) ++ 3) === Complex(d + d + d, a + a + a))
        
        assertThrows[AlgebraicException] {
            Complex(1, 2) ++ 0
        }
        
        assertThrows[AlgebraicException] {
            Complex(1, 2) ++ (-1)
        }
    }
    
    test ("Complex DSL") {
        assert(1 * I === Complex(0, 1))
        assert(1 + 1 * I === Complex(1, 1))
        assert(1 + 3 * I === Complex(1, 3))
        assert(Rational(2, 3) - 3 * I === Complex(Rational(2, 3), -3))
        
        assert(Complex(0, 1) === 1 * I)
        assert(Complex(1, 1) === 1 + 1 * I)
        assert(Complex(1, 3) === 1 + 3 * I)
        assert(Complex(Rational(2, 3), -3) === Rational(2, 3) - 3 * I)
    }
}
