package io.github.torsteinvik.zetatypes.local

import LocalAvatar._

abstract class LocalZetaType[E, A <: LocalAvatar with Concrete] {
    def to[B <: LocalAvatar with Concrete](implicit transform : LocalTransform[A, B, E]) : LocalZetaType[transform.out, B] = transform(this)
}
