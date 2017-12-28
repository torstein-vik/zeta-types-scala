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
        
        test ("Homomorphism type relations") {
            val id     : Integer --> Integer = new Homomorphism [Integer, Integer](){def apply(x : Integer) = x}
            val idmono : Integer ^-> Integer = new Epimorphism  [Integer, Integer](){def apply(x : Integer) = x}
            val idepi  : Integer ->> Integer = new Monomorphism [Integer, Integer](){def apply(x : Integer) = x}
            val idiso  : Integer ~~> Integer = new Isomorphism  [Integer, Integer](){def apply(x : Integer) = x; def inverse = this}
            
            assert(idmono.isInstanceOf[Integer --> Integer])
            assert(idepi .isInstanceOf[Integer --> Integer])
            
            assert(idiso.isInstanceOf[Integer ^-> Integer])
            assert(idiso.isInstanceOf[Integer ->> Integer])
            
            
            assert(!id.isInstanceOf[Integer ^-> Integer])
            assert(!id.isInstanceOf[Integer ->> Integer])
            
            assert(!idmono.isInstanceOf[Integer ~~> Integer])
            assert(!idepi .isInstanceOf[Integer ~~> Integer])
        }
    }
    
    {// Composition injectivity and surjectivity test
        import io.github.torsteinvik.zetatypes.algebra.morphisms.DSL._
        import io.github.torsteinvik.zetatypes.algebra.structures.Integer
        
        val twice     : Integer --> Integer = new Homomorphism [Integer, Integer](){def apply(x : Integer) = x}
        val twicemono : Integer ^-> Integer = new Epimorphism  [Integer, Integer](){def apply(x : Integer) = x}
        val twiceepi  : Integer ->> Integer = new Monomorphism [Integer, Integer](){def apply(x : Integer) = x}
        val twiceiso  : Integer ~~> Integer = new Isomorphism  [Integer, Integer](){def apply(x : Integer) = x; def inverse = this}
        
        test("Composition injectivity and surjectivity induction") {
            def none (f : Integer --> Integer) = !f.isInstanceOf[Integer ^-> Integer] && !f.isInstanceOf[Integer ->> Integer]
            def mono (f : Integer --> Integer) =  f.isInstanceOf[Integer ^-> Integer] && !f.isInstanceOf[Integer ->> Integer]
            def epi  (f : Integer --> Integer) = !f.isInstanceOf[Integer ^-> Integer] &&  f.isInstanceOf[Integer ->> Integer]
            def iso  (f : Integer --> Integer) =  f.isInstanceOf[Integer ~~> Integer]
            
            assert(none (twice of twice))
            assert(none (twice of twicemono))
            assert(none (twice of twiceepi))
            assert(none (twice of twiceiso))
            
            assert(none (twicemono of twice))
            assert(mono (twicemono of twicemono))
            assert(none (twicemono of twiceepi))
            assert(mono (twicemono of twiceiso))
            
            assert(none (twiceepi of twice))
            assert(none (twiceepi of twicemono))
            assert(epi  (twiceepi of twiceepi))
            assert(epi  (twiceepi of twiceiso))
            
            assert(none (twiceiso of twice))
            assert(mono (twiceiso of twicemono))
            assert(epi  (twiceiso of twiceepi))
            assert(iso  (twiceiso of twiceiso))
        }
        
    }
    
    {// Composition application test
        import io.github.torsteinvik.zetatypes.algebra.morphisms.DSL._
        import io.github.torsteinvik.zetatypes.algebra.structures.Integer
        import io.github.torsteinvik.zetatypes.algebra.structures.DSL.intToInteger
        
        
        test("Composition application") {
        }
        
    }
        
    
    
    {// Composition inversion test
        import io.github.torsteinvik.zetatypes.algebra.morphisms.DSL._
        import io.github.torsteinvik.zetatypes.algebra.structures.Integer
        import io.github.torsteinvik.zetatypes.algebra.structures.DSL.intToInteger
        
        
        test("Composition inverse") {
        }
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
