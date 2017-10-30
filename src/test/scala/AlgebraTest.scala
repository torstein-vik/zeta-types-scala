import org.scalatest.FunSuite

import io.zetatypes.algebra._
import io.zetatypes.algebra.structures._

class AlgebraTest extends FunSuite {
    
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
}