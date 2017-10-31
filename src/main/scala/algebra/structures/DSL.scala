package io.zetatypes.algebra.structures

import scala.language.implicitConversions

package object DSL {
    implicit def toInteger (x : Int) : Integer = Integer(x)
}