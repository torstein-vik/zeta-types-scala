package io.github.torsteinvik.zetatypes.test

import org.scalatest.FunSuite

import io.github.torsteinvik.zetatypes.algebra._
import io.github.torsteinvik.zetatypes.algebra.structures._

class IntegerTest extends FunSuite {
    
    val (a, b, c) = (2, -3, 7)
    
    test ("equality test") {
        assert(Integer(a) === Integer(a))
        assert(Integer(b) === Integer(b))
        assert(Integer(c) === Integer(c))
        assert(Integer(c) !== Integer(c + 1))
        assert(Integer(c) !== Integer(c - 1))
        assert(Integer(c) !== Integer(c + 3))
    }
    
    test ("Integer toString") {
        assert(Integer(a).toString === a.toString)
        assert(Integer(b).toString === b.toString)
        assert(Integer(c).toString === c.toString)
    }
        
    test ("identities test") {
        assert(Integers.additive.identity === Integer(0))
        assert(Integers.multiplicative.identity === Integer(1))
    }
    
    test ("zero and one test") {
        assert(Integers.zero === Integer(0))
        assert(Integers.one  === Integer(1))
    }
    
    test ("additive inverse test") {
        assert(Integers.additive.invert(Integer(a)) === Integer(-a))
        assert(Integers.additive.invert(Integer(b)) === Integer(-b))
        assert(Integers.additive.invert(Integer(c)) === Integer(-c))
    }
    
    test ("addition test") {
        assert(Integers.additive.combine(Integer(a), Integer(b)) === Integer(a + b))
        assert(Integers.additive.combine(Integer(b), Integer(c)) === Integer(b + c))
        assert(Integers.additive.combine(Integer(c), Integer(a)) === Integer(c + a))
    }
    
    test ("multiplication test") {
        assert(Integers.multiplicative.combine(Integer(a), Integer(b)) === Integer(a * b))
        assert(Integers.multiplicative.combine(Integer(b), Integer(c)) === Integer(b * c))
        assert(Integers.multiplicative.combine(Integer(c), Integer(a)) === Integer(c * a))
    }
    
    test ("notation adidition test") {
        assert(Integer(a) + Integer(b) === Integer(a + b))
        assert(Integer(b) + Integer(c) === Integer(b + c))
        assert(Integer(c) + Integer(a) === Integer(c + a))
    }
    
    test ("notation multiplication test") {
        assert(Integer(a) * Integer(b) === Integer(a * b))
        assert(Integer(b) * Integer(c) === Integer(b * c))
        assert(Integer(c) * Integer(a) === Integer(c * a))
    }
    
    test ("notation negation test") {
        assert(-Integer(a) === Integer(-a))
        assert(-Integer(b) === Integer(-b))
        assert(-Integer(c) === Integer(-c))
    }
    
    test ("subtraction test") {
        assert(Integer(a) - Integer(b) === Integer(a - b))
        assert(Integer(b) - Integer(c) === Integer(b - c))
        assert(Integer(c) - Integer(a) === Integer(c - a))
    }
    
    test ("implicit integer test") {
        import io.github.torsteinvik.zetatypes.algebra.structures.DSL._
        
        assert((a : Integer).negation === -Integer(a))
        assert((b : Integer).negation === -Integer(b))
        assert((c : Integer).negation === -Integer(c))
    }
    
    test ("Integer psi^k") {
        assert(Integer(a).psi(10) === Integer(a))
        assert(Integer(b).psi(10) === Integer(b))
        assert(Integer(c).psi(10) === Integer(c))
        assert(Integer(a).psi(5) === Integer(a))
        assert(Integer(b).psi(5) === Integer(b))
        assert(Integer(c).psi(5) === Integer(c))
    }
    
    test ("Integer exponentiation") {
        assert((Integer(a) ** 1) === Integer(a))
        assert((Integer(b) ** 1) === Integer(b))
        assert((Integer(c) ** 1) === Integer(c))
        assert((Integer(a) ** 2) === Integer(a * a))
        assert((Integer(b) ** 2) === Integer(b * b))
        assert((Integer(c) ** 2) === Integer(c * c))
        assert((Integer(a) ** 3) === Integer(a * a * a))
        assert((Integer(b) ** 3) === Integer(b * b * b))
        assert((Integer(c) ** 3) === Integer(c * c * c))
        
        assertThrows[AlgebraicException] {
            Integer(1) ** 0
        }
        
        assertThrows[AlgebraicException] {
            Integer(1) ** (-1)
        }
    }
    
    test ("Integer module multiplication") {
        assert((Integer(a) ++ 1) === Integer(a))
        assert((Integer(b) ++ 1) === Integer(b))
        assert((Integer(c) ++ 1) === Integer(c))
        assert((Integer(a) ++ 2) === Integer(a + a))
        assert((Integer(b) ++ 2) === Integer(b + b))
        assert((Integer(c) ++ 2) === Integer(c + c))
        assert((Integer(a) ++ 3) === Integer(a + a + a))
        assert((Integer(b) ++ 3) === Integer(b + b + b))
        assert((Integer(c) ++ 3) === Integer(c + c + c))
        
        assertThrows[AlgebraicException] {
            Integer(1) ++ 0
        }
        
        assertThrows[AlgebraicException] {
            Integer(1) ++ (-1)
        }
    }
    
    test ("Integer q algebra test") {
        import io.github.torsteinvik.zetatypes.algebra.structures.DSL._
        
        assert(Integer(4).partialQMult(Rational(2)) === Some(Integer(8)))
        assert(Integer(2).partialQMult(Rational(2)) === Some(Integer(4)))
        assert(Integer(4).partialQMult(Rational(16, 8)) === Some(Integer(8)))
        assert(Integer(2).partialQMult(Rational(16, 8)) === Some(Integer(4)))
        
        assert(Integer(4).partialQMult(Rational(1, 3)) === None)
        assert(Integer(4).partialQMult(Rational(1, 5)) === None)
        assert(Integer(4).partialQMult(Rational(1, 7)) === None)
        assert(Integer(4).partialQMult(Rational(1, 8)) === None)
        
        assert(Integer(3).partialQMult(Rational(1, 3)) === Some(Integer(1)))
        assert(Integer(7).partialQMult(Rational(1, 7)) === Some(Integer(1)))
        assert(Integer(20).partialQMult(Rational(1, 10)) === Some(Integer(2)))
        assert(Integer(40).partialQMult(Rational(1, 10)) === Some(Integer(4)))
        
        assert(Integer(6).partialQMult(Rational(2, 3)) === Some(Integer(4)))
        assert(Integer(4).partialQMult(Rational(2, 8)) === Some(Integer(1)))
        assert(Integer(10).partialQMult(Rational(3, 6)) === Some(Integer(5)))
        assert(Integer(40).partialQMult(Rational(10, 25)) === Some(Integer(16)))
    }
    
    test ("Integer lambda operation") {
        assert(Integer(4).lambda(2) === Integer(6))
        assert(Integer(3).lambda(3) === Integer(1))
        assert(Integer(0).lambda(3) === Integer(0))
        assert(Integer(1).lambda(7) === Integer(0))
        assert(Integer(7).lambda(2) === Integer(21))
        assert(Integer(4).lambda(3) === Integer(4))
        assert(Integer(5).lambda(1) === Integer(5))
        assert(Integer(40).lambda(30) === Integer(847660528))
        assert(Integer(45).lambda(39) === Integer(8145060))
        
        assert(Integer(-4).lambda(3) === Integer(-20))
        assert(Integer(-2).lambda(3) === Integer(-4))
        
        assertThrows[AlgebraicException] {
            Integer(1).lambda(-3)
        }
    }
}
