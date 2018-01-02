package io.github.torsteinvik.zetatypes.tannakiansymbols

import io.github.torsteinvik.zetatypes.algebra.structures._
import io.github.torsteinvik.zetatypes.algebra._

/** Provides methods for creating [[Multiset]]s, which in turn can be used to create
 *  Tannakian symbols. Multisets can always be created easily, but to covert it
 *  into a Tannakian symbol, there needs to be an implicit 
 *  [[io.github.torsteinvik.zetatypes.algebra.Monoid]] in the scope.
 *  This is explained further in [[Multiset./]]
 */
package object DSL {
    object General {
        /** Shorthand for new [[Multiset]]*/
        def ms[T <: MonoidElement] (x : T*) : Multiset[T] = new Multiset(x : _*)
        
        /** Shorthand for new empty [[Multiset]]*/
        def Ø[T <: MonoidElement] = new Multiset[T]()
    }
    
    object Integer {
        /** Shorthand for new [[Multiset]]*/
        def ms (x : Integer*) : Multiset[Integer] = new Multiset(x : _*)
        
        /** Shorthand for new empty [[Multiset]]*/
        def Ø = new Multiset[Integer]()
    }
    
    object Rational {
        /** Shorthand for new [[Multiset]]*/
        def ms (x : Rational*) : Multiset[Rational] = new Multiset(x : _*)
        
        /** Shorthand for new empty [[Multiset]]*/
        def Ø = new Multiset[Rational]()
    }
    
    object Complex {
        /** Shorthand for new [[Multiset]]*/
        def ms (x : Complex*) : Multiset[Complex] = new Multiset(x : _*)
        
        /** Shorthand for new empty [[Multiset]]*/
        def Ø = new Multiset[Complex]()
    }
    
    object ComplexPolynomial {
        /** Shorthand for new [[Multiset]]*/
        def ms (x : Polynomial[Complex]*) : Multiset[Polynomial[Complex]] = new Multiset(x : _*)
        
        /** Shorthand for new empty [[Multiset]]*/
        def Ø = new Multiset[Polynomial[Complex]]()
    }
}

package DSL {
    
    /** Dummy class so that scaladoc shows DSL */
    protected class DummyClass
}