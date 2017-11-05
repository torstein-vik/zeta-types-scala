package org.torsteinv.zetatypes.test

import org.scalatest.FunSuite

class MultisetTest extends FunSuite {
    
    {// With DSL
        import org.torsteinv.zetatypes.tannakiansymbols.DSL._
        
        test ("Multiset equality") {
            assert(Ø[Int] === ms())
            
            assert(ms(1, 2) === ms(1, 2))
            assert(ms(1, 2, 2, 3) === ms(1, 2, 2, 3))
            
            assert(ms(1, 2, 3) !== ms(1, 2))
            assert(ms(1, 2, 3) !== ms(1, 2, 3, 4))
            assert(ms(1, 2, 3) !== ms(1, 2, 2, 3))
            assert(ms(2, 3) !== ms(2, 2))
            assert(ms(2, 2) !== ms(2, 2, 1))
            assert(ms(2, 2) !== ms(2, 1))
            assert(ms(2, 1, 1) !== ms(2, 2, 1))
            assert(ms(2, 1, 1) !== ms(1, 2, 2))
            
            assert(ms(1, 2) === ms(2, 1))
            assert(ms(1, 2, 2) === ms(2, 1, 2))
            assert(ms(4, 8, 8, 2, 9) === ms(2, 4, 8, 8, 9))
        }
        
        test ("Multiset toString test") {
            assert(Ø[Int].toString === "Ø")
            
            assert(ms(1, 2, 3).toString === "{1, 2, 3}")
            assert(ms(1, 2, 2, 3).toString === "{1, 2, 2, 3}")
            assert(ms(10).toString === "{10}")
        }
        
    }
}
