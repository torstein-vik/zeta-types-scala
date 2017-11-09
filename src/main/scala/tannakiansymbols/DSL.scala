package org.torsteinv.zetatypes.tannakiansymbols

import scala.language.implicitConversions

/** Provides methods for creating [[Multiset]]s, which in turn can be used to create
 *  Tannakian symbols. Multisets can always be created easily, but to covert it
 *  into a Tannakian symbol, there needs to be an implicit 
 *  [[org.torsteinv.zetatypes.algebra.Monoid]] in the scope.
 *  This is explained further in [[Multiset./]]
 */
package object DSL {
    /** Returns a singleton multiset from any element */
    implicit def toMS[T] (x : T) : Multiset[T] = new Multiset(x)
    
    /** Shorthand for [[Multiset]]*/
    def ms[T] (x : T*) : Multiset[T] = new Multiset(x : _*)
    
    /** Shorthand for empty [[Multiset]]*/
    def Ø[T] = new Multiset[T]()
    
    /** Dummy class so that scaladoc shows DSL */
    protected class DummyClass
}