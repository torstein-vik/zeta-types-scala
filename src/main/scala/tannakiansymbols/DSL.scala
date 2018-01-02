package io.github.torsteinvik.zetatypes.tannakiansymbols

import io.github.torsteinvik.zetatypes.algebra.structures._
import io.github.torsteinvik.zetatypes.algebra._

/** Provides methods for creating [[AlgebraicMultiset]]s, which in turn can be used to create
 *  Tannakian symbols. AlgebraicMultisets can always be created easily, but to covert it
 *  into a Tannakian symbol, there needs to be an implicit 
 *  [[io.github.torsteinvik.zetatypes.algebra.Monoid]] in the scope.
 *  This is explained further in [[AlgebraicMultiset./]]
 */
package object DSL {
    object General {
        /** Shorthand for new [[AlgebraicMultiset]]*/
        def ms[T <: MonoidElement] (x : T*) : AlgebraicMultiset[T] = new AlgebraicMultiset(x : _*)
        
        /** Shorthand for new empty [[AlgebraicMultiset]]*/
        def Ø[T <: MonoidElement] = new AlgebraicMultiset[T]()
    }
    
    object Integer {
        /** Shorthand for new [[AlgebraicMultiset]]*/
        def ms (x : Integer*) : AlgebraicMultiset[Integer] = new AlgebraicMultiset(x : _*)
        
        /** Shorthand for new empty [[AlgebraicMultiset]]*/
        def Ø = new AlgebraicMultiset[Integer]()
    }
    
    object Rational {
        /** Shorthand for new [[AlgebraicMultiset]]*/
        def ms (x : Rational*) : AlgebraicMultiset[Rational] = new AlgebraicMultiset(x : _*)
        
        /** Shorthand for new empty [[AlgebraicMultiset]]*/
        def Ø = new AlgebraicMultiset[Rational]()
    }
    
    object Complex {
        /** Shorthand for new [[AlgebraicMultiset]]*/
        def ms (x : Complex*) : AlgebraicMultiset[Complex] = new AlgebraicMultiset(x : _*)
        
        /** Shorthand for new empty [[AlgebraicMultiset]]*/
        def Ø = new AlgebraicMultiset[Complex]()
    }
    
    object ComplexPolynomial {
        /** Shorthand for new [[AlgebraicMultiset]]*/
        def ms (x : Polynomial[Complex]*) : AlgebraicMultiset[Polynomial[Complex]] = new AlgebraicMultiset(x : _*)
        
        /** Shorthand for new empty [[AlgebraicMultiset]]*/
        def Ø = new AlgebraicMultiset[Polynomial[Complex]]()
    }
}

package DSL {
    
    /** Dummy class so that scaladoc shows DSL */
    protected class DummyClass
}