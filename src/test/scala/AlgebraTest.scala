import org.scalatest.FunSuite

import io.zetatypes.algebra._

class AlgebraTest extends FunSuite {
    case class TestInteger (x : Int) extends RingElement[TestInteger]{
        override def +(that : TestInteger) = TestInteger(x + that.x) 
        override def *(that : TestInteger) = TestInteger(x * that.x)
        override def unary_-() = TestInteger(-x)
    }
    
    object TestIntegers extends RingClass[TestInteger](TestInteger(0), TestInteger(1))
    
    val (a, b, c) = (2, -3, 7)
    
    test ("equality test") {
        assert(TestInteger(a) === TestInteger(a))
        assert(TestInteger(b) === TestInteger(b))
        assert(TestInteger(c) === TestInteger(c))
        assert(TestInteger(c) !== TestInteger(c + 1))
        assert(TestInteger(c) !== TestInteger(c - 1))
        assert(TestInteger(c) !== TestInteger(c + 3))
    }
    
    test ("identities test") {
        assert(TestIntegers.additive.identity === TestInteger(0))
        assert(TestIntegers.multiplicative.identity === TestInteger(1))
    }
    
    test ("zero and one test") {
        assert(TestIntegers.zero === TestInteger(0))
        assert(TestIntegers.one  === TestInteger(1))
    }
    
    test ("additive inverse test") {
        assert(TestIntegers.additive.invert(TestInteger(a)) === TestInteger(-a))
        assert(TestIntegers.additive.invert(TestInteger(b)) === TestInteger(-b))
        assert(TestIntegers.additive.invert(TestInteger(c)) === TestInteger(-c))
    }
    
    test ("addition test") {
        assert(TestIntegers.additive.combine(TestInteger(a), TestInteger(b)) === TestInteger(a + b))
        assert(TestIntegers.additive.combine(TestInteger(b), TestInteger(c)) === TestInteger(b + c))
        assert(TestIntegers.additive.combine(TestInteger(c), TestInteger(a)) === TestInteger(c + a))
    }
    
    test ("multiplication test") {
        assert(TestIntegers.multiplicative.combine(TestInteger(a), TestInteger(b)) === TestInteger(a * b))
        assert(TestIntegers.multiplicative.combine(TestInteger(b), TestInteger(c)) === TestInteger(b * c))
        assert(TestIntegers.multiplicative.combine(TestInteger(c), TestInteger(a)) === TestInteger(c * a))
    }
    
    test ("notation adidition test") {
        assert(TestInteger(a) + TestInteger(b) === TestInteger(a + b))
        assert(TestInteger(b) + TestInteger(c) === TestInteger(b + c))
        assert(TestInteger(c) + TestInteger(a) === TestInteger(c + a))
    }
    
    test ("notation multiplication test") {
        assert(TestInteger(a) * TestInteger(b) === TestInteger(a * b))
        assert(TestInteger(b) * TestInteger(c) === TestInteger(b * c))
        assert(TestInteger(c) * TestInteger(a) === TestInteger(c * a))
    }
    
    test ("notation negation test") {
        assert(-TestInteger(a) === TestInteger(-a))
        assert(-TestInteger(b) === TestInteger(-b))
        assert(-TestInteger(c) === TestInteger(-c))
    }
    
    test ("subtraction test") {
        assert(TestInteger(a) - TestInteger(b) === TestInteger(a - b))
        assert(TestInteger(b) - TestInteger(c) === TestInteger(b - c))
        assert(TestInteger(c) - TestInteger(a) === TestInteger(c - a))
    }
}