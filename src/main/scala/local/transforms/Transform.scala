package io.github.torsteinvik.zetatypes.local.transforms

import io.github.torsteinvik.zetatypes.local._
import Avatar._

trait Transform[A <: Avatar with Concrete, B <: Avatar with Concrete, E] {
    type E1
    def apply (input : ZetaType[E, A]) : ZetaType[E1, B]
}
