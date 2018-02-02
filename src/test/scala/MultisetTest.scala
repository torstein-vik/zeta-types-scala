package io.github.torsteinvik.zetatypes.test

import org.scalatest.FunSuite

class AlgebraicMultisetTest extends FunSuite {
    
    {// With DSL
        import io.github.torsteinvik.zetatypes.tannakiansymbols.DSL.Integer._
        import io.github.torsteinvik.zetatypes.algebra.structures.DSL._
        
        test ("AlgebraicMultiset equality") {
            assert(Ø === ms())
            
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
        
        test ("AlgebraicMultiset toString test") {
            assert(Ø.toString === "Ø")
            
            assert(ms(1, 2, 3).toString === "{1, 2, 3}")
            assert(ms(1, 2, 2, 3).toString === "{1, 2, 2, 3}")
            assert(ms(10).toString === "{10}")
        }
        
    }
}
