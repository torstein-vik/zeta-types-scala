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
            val idmono : Integer ^-> Integer = new Monomorphism  [Integer, Integer](){def apply(x : Integer) = x}
            val idepi  : Integer ->> Integer = new Epimorphism [Integer, Integer](){def apply(x : Integer) = x}
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
        val twicemono : Integer ^-> Integer = new Monomorphism  [Integer, Integer](){def apply(x : Integer) = x}
        val twiceepi  : Integer ->> Integer = new Epimorphism [Integer, Integer](){def apply(x : Integer) = x}
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
        
        val addone : Integer --> Integer = new Homomorphism [Integer, Integer](){def apply(x : Integer) = x + 1}
        val twice  : Integer --> Integer = new Homomorphism [Integer, Integer](){def apply(x : Integer) = x * 2}
        val square : Integer --> Integer = new Homomorphism [Integer, Integer](){def apply(x : Integer) = x * x}
        
        test("Composition application") {
            assert((addone of addone) (3) === Integer(5))
            assert((addone of addone) (5) === Integer(7))
            assert((addone of twice)  (3) === Integer(7))
            assert((addone of twice)  (5) === Integer(11))
            assert((addone of square) (3) === Integer(10))
            assert((addone of square) (5) === Integer(26))
            
            assert((twice  of addone) (3) === Integer(8))
            assert((twice  of addone) (5) === Integer(12))
            assert((twice  of twice)  (3) === Integer(12))
            assert((twice  of twice)  (5) === Integer(20))
            assert((twice  of square) (3) === Integer(18))
            assert((twice  of square) (5) === Integer(50))
            
            assert((square of addone) (3) === Integer(16))
            assert((square of addone) (5) === Integer(36))
            assert((square of twice)  (3) === Integer(36))
            assert((square of twice)  (5) === Integer(100))
            assert((square of square) (3) === Integer(81))
            assert((square of square) (5) === Integer(625))   
                 
        }
        
    }
        
    
    
    {// Composition inversion test
        import io.github.torsteinvik.zetatypes.algebra.morphisms.DSL._
        import io.github.torsteinvik.zetatypes.algebra.structures.Integer
        import io.github.torsteinvik.zetatypes.algebra.structures.DSL.intToInteger
        
        def m1    : Integer ~~> Integer = new Isomorphism [Integer, Integer](){def apply(x : Integer) = x + 2; def inverse = m1inv}
        def m1inv : Integer ~~> Integer = new Isomorphism [Integer, Integer](){def apply(x : Integer) = x - 2; def inverse = m1}
        
        def m2    : Integer ~~> Integer = new Isomorphism [Integer, Integer](){def apply(x : Integer) = x * 2; def inverse = m2inv}
        def m2inv : Integer ~~> Integer = new Isomorphism [Integer, Integer](){def apply(x : Integer) = x match {case Integer(x) => Integer(x/2)}; def inverse = m2}
        
        def m3    : Integer ~~> Integer = new Isomorphism [Integer, Integer](){def apply(x : Integer) = x + 3; def inverse = m3inv}
        def m3inv : Integer ~~> Integer = new Isomorphism [Integer, Integer](){def apply(x : Integer) = x - 3; def inverse = m3}
        
        test("Composition inverse") {
            for (i <- 1 to 10){
                assert((m1 of m2).inverse(i) == (m2inv of m1inv)(i))
                assert((m2 of m1).inverse(i) == (m1inv of m2inv)(i))
                
                assert((m2 of m3).inverse(i) == (m3inv of m2inv)(i))
                assert((m3 of m2).inverse(i) == (m2inv of m3inv)(i))
                    
                assert((m3 of m1).inverse(i) == (m1inv of m3inv)(i))
                assert((m1 of m3).inverse(i) == (m3inv of m1inv)(i))
            }
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
