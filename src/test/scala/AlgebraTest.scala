import org.scalatest.FunSuite

import org.zetatypes.algebra._
import org.zetatypes.algebra.structures._

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
        import scala.language.implicitConversions
        import org.zetatypes.algebra.structures.DSL._
        
        assert(a.negation == -Integer(a))
        assert(b.negation == -Integer(b))
        assert(c.negation == -Integer(c))
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
        import scala.language.implicitConversions
        import org.zetatypes.algebra.structures.DSL._
        
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

class RationalTest extends FunSuite {
    
    import org.zetatypes.algebra.structures.DSL._
    
    val (a, b, c, d) = (2, -3, 7, 4)
    
    test ("divide by zero test") {
        assertThrows[AlgebraicException] {
            Rational(1, 0)
        } 
    }
    
    test ("rational toString") {
        assert(Rational(a, b).toString === a.toString + "/" + b.toString)
        assert(Rational(b, c).toString === b.toString + "/" + c.toString)
        assert(Rational(c, d).toString === c.toString + "/" + d.toString)
        assert(Rational(d, a).toString === d.toString + "/" + a.toString)
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
    
    test ("identities test") {
        assert(Rationals.additive.identity === Rational(0))
        assert(Rationals.multiplicative.identity === Rational(1))
    }
    
    test ("zero and one test") {
        assert(Rationals.zero === Rational(0))
        assert(Rationals.one  === Rational(1))
    }
    
    test ("additive inverse test") {
        assert(Rationals.additive.invert(Rational(a, b)) === Rational(-a, b))
        assert(Rationals.additive.invert(Rational(b, c)) === Rational(-b, c))
        assert(Rationals.additive.invert(Rational(c, d)) === Rational(-c, d))
        assert(Rationals.additive.invert(Rational(d, a)) === Rational(-d, a))
    }
    
    test ("addition test") {
        assert(Rationals.additive.combine(Rational(a), Rational(b)) === Rational(a + b))
        assert(Rationals.additive.combine(Rational(b), Rational(c)) === Rational(b + c))
        assert(Rationals.additive.combine(Rational(c), Rational(d)) === Rational(c + d))
        assert(Rationals.additive.combine(Rational(d), Rational(a)) === Rational(d + a))
        
        assert(Rationals.additive.combine(Rational(a, b), Rational(c, d)) === Rational(a * d + b * c, b * d))
        assert(Rationals.additive.combine(Rational(b, c), Rational(d, a)) === Rational(b * a + c * d, c * a))
    }
    
    test ("multiplication test") {
        assert(Rationals.multiplicative.combine(Rational(a), Rational(b)) === Rational(a * b))
        assert(Rationals.multiplicative.combine(Rational(b), Rational(c)) === Rational(b * c))
        assert(Rationals.multiplicative.combine(Rational(c), Rational(d)) === Rational(c * d))
        assert(Rationals.multiplicative.combine(Rational(d), Rational(a)) === Rational(d * a))
        
        assert(Rationals.multiplicative.combine(Rational(a, b), Rational(c, d)) === Rational(a * c, b * d))
        assert(Rationals.multiplicative.combine(Rational(b, c), Rational(d, a)) === Rational(b * d, c * a))
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
    
    test ("subtraction test") {
        assert(Rational(a) - Rational(b) === Rational(a - b))
        assert(Rational(b) - Rational(c) === Rational(b - c))
        assert(Rational(c) - Rational(d) === Rational(c - d))
        assert(Rational(d) - Rational(a) === Rational(d - a))
        
        assert(Rational(a, b) - Rational(c, d) === Rational(a * d - b * c, b * d))
        assert(Rational(b, c) - Rational(d, a) === Rational(b * a - c * d, c * a))
    }
    
        
    test ("Rational exponentiation") {
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
        
        assertThrows[AlgebraicException] {
            Rational(1, 2) ** 0
        }
        
        assertThrows[AlgebraicException] {
            Rational(1, 2) ** (-1)
        }
    }
    
    
    test ("Rational module multiplication") {
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
        
        assertThrows[AlgebraicException] {
            Rational(1, 2) ++ 0
        }
        
        assertThrows[AlgebraicException] {
            Rational(1, 2) ++ (-1)
        }
    }
    
    
}

class ComplexTest extends FunSuite {
    import org.zetatypes.algebra.structures.DSL._
    
    val (a, b, c, d) = (Fraction(2), Fraction(-3, 2), Fraction(7, 5), Fraction(4, 3))
    
    test ("Complex number DSL") {
        assertCompiles("1 + 9 i")
        assertCompiles("1 - 9 i")
        assertCompiles("1 + 2 i")
        assertCompiles("9 i")
    }
    
    test ("Complex number toString") {
        assert(Complex(a, b).toString === a.toString + " + " + b.toString + " i")
        assert(Complex(b, c).toString === b.toString + " + " + c.toString + " i")
        assert(Complex(c, d).toString === c.toString + " + " + d.toString + " i")
        assert(Complex(d, a).toString === d.toString + " + " + a.toString + " i")
    }
    
    test ("equality test") {
        assert(Complex(a, b) === Complex(a, b))
        assert(Complex(b, c) === Complex(b, c))
        assert(Complex(c, d) === Complex(c, d))
        assert(Complex(c, d) === Complex(c, d))
        
        assert(Complex(a, b) !== Complex(a + 1, b))
        assert(Complex(a, b) !== Complex(a - 1, b))
        assert(Complex(a, b) !== Complex(a + 3, b))
        assert(Complex(a, b) !== Complex(a - 3, b))
        
        assert(Complex(a, b) !== Complex(a, b + 1))
        assert(Complex(a, b) !== Complex(a, b - 1))
        assert(Complex(a, b) !== Complex(a, b + 3))
        assert(Complex(a, b) !== Complex(a, b - 3))
        
        assert(Complex(a) === Complex(a, Rationals.zero))
        assert(Complex(a) === Complex(a, Rationals.zero))
        assert(Complex(a) === Complex(a, Rationals.zero))
        assert(Complex(a) === Complex(a, Rationals.zero))
        
        assert(Complex(Fraction(2,2), Fraction(3,3)) === Complex(Fraction(1), Fraction(1)))
        assert(Complex(Fraction(4,2), Fraction(3,6)) === Complex(Fraction(2), Fraction(1, 2)))
        assert(Complex(Fraction(2,12), Fraction(9,3)) === Complex(Fraction(1, 6), Fraction(3)))
        assert(Complex(Fraction(10,2), Fraction(15,3)) === Complex(Fraction(5), Fraction(5)))
    }
    
    test ("identities test") {
        assert(Complex.additive.identity === Complex(0))
        assert(Complex.multiplicative.identity === Complex(1))
    }
    
    test ("zero and one test") {
        assert(Complex.zero === Complex(0))
        assert(Complex.one  === Complex(1))
    }
    
}
