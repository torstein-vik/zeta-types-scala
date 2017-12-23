package io.github.torsteinvik.zetatypes.test

import org.scalatest.FunSuite

import io.github.torsteinvik.zetatypes.algebra.morphisms._

class HomomorphismTest extends FunSuite {
    
    {// Type synonym test
        import io.github.torsteinvik.zetatypes.algebra.morphisms.DSL._
        import io.github.torsteinvik.zetatypes.algebra.structures.Integer
        
        test ("Homomorphism notation") {
            assertCompiles("val f : Integer --> Integer = identity")
            assertCompiles("val f : Integer ^-> Integer = identity")
            assertCompiles("val f : Integer ->> Integer = identity")
            assertCompiles("val f : Integer ~~> Integer = identity")
        }
        
    }
    
    {// Composition injectivity and surjectivity test
        
    }
    
    {// Composition application test
        
    }
    
    {// Identitity application test
        import io.github.torsteinvik.zetatypes.algebra.morphisms.DSL._
        import io.github.torsteinvik.zetatypes.algebra.structures.Integer
        
        test ("Identity morphism test") {
            val id : Isomorphism[Integer, Integer] = identity
            
            assert(id(Integer(5435543)) === Integer(5435543))
            assert(id(Integer(-10)) === Integer(-10))
            assert(id(Integer(0)) === Integer(0))
            assert(id(Integer(7)) === Integer(7))
            
            assert(id.inverse(Integer(5435543)) === Integer(5435543))
            assert(id.inverse(Integer(-10)) === Integer(-10))
            assert(id.inverse(Integer(0)) === Integer(0))
            assert(id.inverse(Integer(7)) === Integer(7))
        }
    }
}
