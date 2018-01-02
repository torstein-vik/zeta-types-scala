package io.github.torsteinvik.zetatypes.tannakiansymbols

import scala.language.implicitConversions

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
    
    
    
}

package DSL {
    
    /** Dummy class so that scaladoc shows DSL */
    protected class DummyClass
}