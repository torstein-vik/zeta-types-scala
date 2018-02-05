package io.github.torsteinvik.zetatypes.algebra

/** An element in the algebraic system */
// Macros would be so much better!
trait Element {
    def +[T, S](y : T)(implicit provider : AdditionProvider       [this.type, T, S]) : S = provider.add(this, y)
    def *[T, S](y : T)(implicit provider : MultiplicationProvider [this.type, T, S]) : S = provider.multiply(this, y)
    
}

object Element {
    def zero[T](implicit provider : ZeroProvider [T]) = provider.zero
    def one [T](implicit provider : OneProvider  [T]) = provider.one
}
