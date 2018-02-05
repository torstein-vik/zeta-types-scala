package io.github.torsteinvik.zetatypes.algebra

/** An element in the algebraic system */
// Macros would be so much better!
trait Element {
}

object Element {
    def zero[T](implicit provider : ZeroProvider [T]) = provider.zero
    def one [T](implicit provider : OneProvider  [T]) = provider.one
}
