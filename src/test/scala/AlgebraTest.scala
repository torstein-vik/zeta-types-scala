import org.scalatest.FunSuite

import io.zetatypes.algebra._
import io.zetatypes.algebra.structures._

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
        import io.zetatypes.algebra.structures.DSL._
        
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
        
        assert((Integer(a) ** 0) === Integers.one)
        assert((Integer(b) ** 0) === Integers.one)
        assert((Integer(c) ** 0) === Integers.one)
        
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
        
        assert((Integer(a) ++ 0) === Integers.zero)
        assert((Integer(b) ++ 0) === Integers.zero)
        assert((Integer(c) ++ 0) === Integers.zero)
        
        assertThrows[AlgebraicException] {
            Integer(1) ++ (-1)
        }
    }
}