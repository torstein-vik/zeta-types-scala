package io.zetatypes.tannakiansymbols

package object DSL {
    implicit def toMS[T] (x : T) : Multiset[T] = new Multiset(x)
    
}