package io.github.torsteinvik.zetatypes.algebra

/** An element in the algebraic system */
// Macros would be so much better!
// Add NoSuchImplicit errors
trait Element {
    final def +[T, S](y : T)(implicit provider : AdditionProvider       [this.type, T, S]) : S = provider.add(this, y)
    final def *[T, S](y : T)(implicit provider : MultiplicationProvider [this.type, T, S]) : S = provider.multiply(this, y)
    
    final def -[T, S](y : T)(implicit provider : SubtractionProvider [this.type, T, S]) : S = provider.subtract(this, y)
    final def /[T, S](y : T)(implicit provider : DivisionProvider    [this.type, T, S]) : S = provider.divide(this, y)
    
    final def ++[T](y : Int)(implicit provider : ModuleMultiplicationProvider [this.type, T]) : T = provider.mmultiply(this)(y)
    final def **[T](y : Int)(implicit provider : ExponentiationProvider       [this.type, T]) : T = provider.raise(this)(y)
    
    final def unary_-  [T](implicit provider : NegationProvider [this.type, T]) : T = provider.negate(this)
    final def unary_~  [T](implicit provider : InverseProvider  [this.type, T]) : T = provider.invert(this)
    final def negation [T](implicit provider : NegationProvider [this.type, T]) : T = provider.negate(this)
    final def inverse  [T](implicit provider : InverseProvider  [this.type, T]) : T = provider.invert(this)
    
    final def /# [T](y : Int)(implicit provider : QModuleDivisionProvider        [this.type, T]) : T         = provider.mdivide(this)(y)
    final def /##[T](y : Int)(implicit provider : PartialQModuleDivisionProvider [this.type, T]) : Option[T] = provider.partialmdivide(this)(y)
    
    final def psi   [T](y : Int)(implicit provider : PsiProvider    [this.type, T]) : T = provider.psi(this)(y)
    final def adams [T](y : Int)(implicit provider : PsiProvider    [this.type, T]) : T = provider.psi(this)(y)
    final def lambda[T](y : Int)(implicit provider : LambdaProvider [this.type, T]) : T = provider.lambda(this)(y)
    
}

object Element {
    def zero[T](implicit provider : ZeroProvider [T]) = provider.zero
    def one [T](implicit provider : OneProvider  [T]) = provider.one
}
