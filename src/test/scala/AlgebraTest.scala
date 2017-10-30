import org.scalatest.FunSuite

import io.zetatypes.algebra._

class AlgebraTest extends FunSuite {
    case class TestInteger (x : Int) extends RingElement[TestInteger]{
        override def +(that : TestInteger) = TestInteger(x + that.x) 
        override def *(that : TestInteger) = TestInteger(x * that.x)
        override def unary_-() = TestInteger(-x)
    }
    
    object TestIntegers extends RingClass[TestInteger](TestInteger(0), TestInteger(1))
    
    test ("equality test") {
        assert(TestInteger(2) === TestInteger(2))
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
        assert(TestIntegers.additive.invert(TestInteger(4)) === TestInteger(-4))
    }
    
    test ("addition test") {
        assert(TestIntegers.additive.combine(TestInteger(1), TestInteger(2)) === TestInteger(3))
    }
    
    test ("multiplication test") {
        assert(TestIntegers.multiplicative.combine(TestInteger(2), TestInteger(3)) === TestInteger(6))
    }
    
    test ("notation adidition test") {
        assert(TestInteger(1) + TestInteger(2) === TestInteger(3))
    }
    
    test ("notation multiplication test") {
        assert(TestInteger(2) * TestInteger(3) === TestInteger(6))    
    }
    
    test ("notation negation test") {
        assert(- TestInteger(3) === TestInteger(-3))
    }
    
    test ("subtraction test") {
        assert(TestInteger(7) - TestInteger(4) === TestInteger(3))    
    }
}