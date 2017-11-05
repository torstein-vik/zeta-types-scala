package org.torsteinv.zetatypes.tannakiansymbols

import scala.language.implicitConversions

package object DSL extends DSLPackage

trait DSLPackage {
    implicit def toMS[T] (x : T) : Multiset[T] = new Multiset(x)
    
    def ms[T] (x : T*) : Multiset[T] = new Multiset(x : _*)
    
    def Ø[T] = new Multiset[T]()
}