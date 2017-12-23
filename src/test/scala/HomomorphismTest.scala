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
            
    }
}
