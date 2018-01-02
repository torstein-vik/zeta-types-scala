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
    
    /** Implicit functor of a monoid to its [[TS]]-ring */
    implicit def tannakiansymbols[E <: MonoidElement](implicit monoid : Monoid[E]) : TS[E] = TS(monoid)
    
    /** Provides multiset methods in a generic setting */
    object General {
        /** Shorthand for new [[AlgebraicMultiset]]*/
        def ms[T <: MonoidElement] (x : T*) : AlgebraicMultiset[T] = new AlgebraicMultiset(x : _*)
        
        /** Shorthand for new empty [[AlgebraicMultiset]]*/
        def Ø[T <: MonoidElement] = new AlgebraicMultiset[T]()
    }
    
    /** Provides multiset methods in an integral setting */
    object Integer {
        /** Shorthand for new [[AlgebraicMultiset]]*/
        def ms (x : Integer*) : AlgebraicMultiset[Integer] = new AlgebraicMultiset(x : _*)
        
        /** Shorthand for new empty [[AlgebraicMultiset]]*/
        def Ø = new AlgebraicMultiset[Integer]()
    }
    
    /** Provides multiset methods in a rational setting */
    object Rational {
        /** Shorthand for new [[AlgebraicMultiset]]*/
        def ms (x : Rational*) : AlgebraicMultiset[Rational] = new AlgebraicMultiset(x : _*)
        
        /** Shorthand for new empty [[AlgebraicMultiset]]*/
        def Ø = new AlgebraicMultiset[Rational]()
    }
    
    /** Provides multiset methods in a complex setting */
    object Complex {
        /** Shorthand for new [[AlgebraicMultiset]]*/
        def ms (x : Complex*) : AlgebraicMultiset[Complex] = new AlgebraicMultiset(x : _*)
        
        /** Shorthand for new empty [[AlgebraicMultiset]]*/
        def Ø = new AlgebraicMultiset[Complex]()
    }
    
    /** Provides multiset methods in a complex-polynomial setting */
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