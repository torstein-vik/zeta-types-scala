package io.github.torsteinvik.zetatypes.local

import Avatar._
import transforms._

abstract class ZetaType[E, A <: Avatar with Concrete] {
    def to[B <: Avatar with Concrete](implicit transform : Transform[A, B, E]) : ZetaType[transform.newbase, B] = transform(this)
}
