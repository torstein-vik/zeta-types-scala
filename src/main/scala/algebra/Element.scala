package io.github.torsteinvik.zetatypes.algebra

/** An element in the algebraic system */
// Macros would be so much better!
trait Element {
    def +[T, S](y : T)(implicit provider : AdditionProvider       [this.type, T, S]) : S = provider.add(this, y)
    def *[T, S](y : T)(implicit provider : MultiplicationProvider [this.type, T, S]) : S = provider.multiply(this, y)
    
    def -[T, S](y : T)(implicit provider : SubtractionProvider [this.type, T, S]) : S = provider.subtract(this, y)
    def /[T, S](y : T)(implicit provider : DivisionProvider    [this.type, T, S]) : S = provider.divide(this, y)
    
    def unary_-  [T](implicit provider : NegationProvider [this.type, T]) : T = provider.negate(this)
    def unary_~  [T](implicit provider : InverseProvider  [this.type, T]) : T = provider.invert(this)
    def negation [T](implicit provider : NegationProvider [this.type, T]) : T = provider.negate(this)
    def inverse  [T](implicit provider : InverseProvider  [this.type, T]) : T = provider.invert(this)
    
}

object Element {
    def zero[T](implicit provider : ZeroProvider [T]) = provider.zero
    def one [T](implicit provider : OneProvider  [T]) = provider.one
}
