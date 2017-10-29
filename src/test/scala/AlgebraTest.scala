import org.scalatest.FunSuite

import io.zetatypes.algebra.Monoids.{Monoid, Element => MonoidElement}
import io.zetatypes.algebra.Rings.{Ring, RingClass, Element => RingElement}

class AlgebraTest extends FunSuite {
    test ("algebra test") {
        class TestInteger (val x : Int) extends RingElement[TestInteger]{
            override def +(that : TestInteger) = TestInteger(x + that.x) 
            override def *(that : TestInteger) = TestInteger(x * that.x)
            
            def ==(that : TestInteger) = that.x == x
            override def toString = "TestInteger " + x
        }
        
        object TestInteger extends (Int => TestInteger){
            override def apply (x : Int) = new TestInteger(x)
        }
        
        object TestIntegers extends RingClass[TestInteger](TestInteger(0), TestInteger(1))
        
        assert(TestInteger(2) == TestInteger(2))
        
        assert(TestIntegers.additive      .identity == TestInteger(0))
        assert(TestIntegers.multiplicative.identity == TestInteger(1))
        assert(TestInteger(1) + TestInteger(2) == TestInteger(3))
        assert(TestInteger(2) * TestInteger(3) == TestInteger(6))
        
        assert(TestIntegers.additive      .combine(TestInteger(1), TestInteger(2)) == TestInteger(3))
        assert(TestIntegers.multiplicative.combine(TestInteger(2), TestInteger(3)) == TestInteger(6))
    }
}