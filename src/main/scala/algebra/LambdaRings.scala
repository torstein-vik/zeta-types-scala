package io.github.torsteinvik.zetatypes.algebra


/** An element of some [[LambdaRing]] */
trait LambdaRingElement[T <: LambdaRingElement[T]] extends RingElement[T]

/** A type of ring with a [[lambda]] operation and [[psi]] operations, from the algebraic theory of lambda rings.
 *  @tparam E The type of [[LambdaRingElement]] that is passed through the [[psi]]- and [[lambda]]-operations, and the underlying [[Ring]] structure.
 */
trait LambdaRing[E <: LambdaRingElement[E]] extends Ring[E] {
    /** The n-th Adams/psi operation applied to some element x */
    def psi (x : E)(n : Int) : E
    /** The n-th lambda operation applied to some element x */
    def lambda (x : E)(n : Int) : E
}

/** A [[LambdaRingElement]] where lambda is inferred from psi's */
trait PsiRingElement[T <: PsiRingElement[T]] extends LambdaRingElement[T] with PartialQModuleElement[T] { 
    /** The n-th Adams/psi operation applied to this element */
    def psi (n : Int) : T
    
    private var lambdacache : Map[Int, T] = Map()
    
    import structures.DSL.intToInteger
    import structures.Rational
    /** The n-th lambda operation applied to this element */
    final def lambda (n : Int) : T = n match {
        case 0 => one
        case 1 => this * one
        case n if lambdacache.contains(n) => lambdacache(n)
        case n if n > 1 => (0 to n - 1).foldLeft(zero) {
            case (sum, i) if i % 2 == 0 => sum + (this.psi(n - i) * this.lambda(i))
            case (sum, i) if i % 2 == 1 => sum - (this.psi(n - i) * this.lambda(i))
        }.partialQMult(Rational(if (n % 2 == 0) -1 else 1, n)) match {
            case Some(result) => {lambdacache += n -> result; result}
            case None => throw new AlgebraicException("Wilkersons hypothesis did not hold! You may not use lambda in this ring")
        }
        case _ => throw new AlgebraicException("Lambda operation only defined for n >= 0 !")
    }
}

/** A [[LambdaRing]] where lambda is inferred from psi's, using [[PsiRingElement]] */
trait PsiRing[E <: PsiRingElement[E]] extends LambdaRing[E] with PartialQAlgebra[E] {
    final override def psi (x : E)(n : Int) : E = x.psi(n)
    final override def lambda (x : E)(n : Int) : E = x.lambda(n)
}
