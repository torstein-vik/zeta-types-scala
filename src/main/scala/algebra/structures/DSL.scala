package io.zetatypes.algebra.structures

package object DSL {
    implicit def toInteger (x : Int) : Integer = Integer(x)
}