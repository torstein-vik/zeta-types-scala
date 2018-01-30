package io.github.torsteinvik.zetatypes.algebra

import structures.{Integer, Rational}

/** An element of some [[LambdaRing]] */
trait LambdaRingElement[T <: LambdaRingElement[T]] extends RingElement[T]

/** A type of ring with a [[lambda]] operation and [[psi]] operations, from the algebraic theory of lambda rings.
 *  @tparam E The type of [[LambdaRingElement]] that is passed through the [[psi]]- and [[lambda]]-operations, and the underlying [[Ring]] structure.
 */
trait LambdaRing[E <: LambdaRingElement[E]] extends Ring[E] {
    /** The n-th Adams/psi operation applied to some element x */
    def psi (x : E)(n : Int) : E
    /** The n-th lambda operation applied to some element x.
     *  Also takes some implicit evidence that E can be converted to and from some other type S, which is a PartialQAlgebraElement
     *  @tparam S the PartialQAlgebraElement the elements of this lambda ring visit in order to divide by integers
     *  @param x the [[LambdaRingElement]] that has the lambda opertion applied
     *  @param n specifies which n-th lambda operation to use
     *  @param ev evidence that allows [[LambdaRingElement]] to convert into a [[PartialQAlgebraElement]], allowing division by integers
     *  @param ev2 evidence that allows [[PartialQAlgebraElement]] to be re-embedded in this [[LambdaRing]], after division
     */
    def lambda[S <: PartialQAlgebraElement[S]] (x : E)(n : Int)(implicit ev: E => S, ev2: S => E) : E
}

/** Standard implementation of [[LambdaRingElement]] */
trait STDLambdaRingElement[T <: STDLambdaRingElement[T]] extends LambdaRingElement[T] {
    /** The n-th Adams/psi operation applied to this element */
    def psi (n : Int) : T
    
    private var lambdacache : Map[Int, T] = Map()
    /** The n-th lambda operation applied to this element.
     *  Also takes some implicit evidence that E can be converted to and from some other type S, which is a PartialQAlgebraElement
     *  @tparam S the PartialQAlgebraElement the elements of this lambda ring visit in order to divide by integers
     *  @param n specifies which n-th lambda operation to use
     *  @param ev evidence that allows this to be converted into a [[PartialQAlgebraElement]], allowing division by integers
     *  @param ev2 evidence that allows [[PartialQAlgebraElement]] to be re-embedded into this type, after division
     */
    def lambda[S <: PartialQAlgebraElement[S]] (n : Int)(implicit ev: T => S, ev2: S => T) : T = n match {
        case 0 => one
        case 1 => this * one
        case n if lambdacache.contains(n) => lambdacache(n)
        case n if n > 1 => {
            var res = zero
            for (i <- 0 to n - 1) {
                val ires = this.psi(n - i) * this.lambda(i)(ev, ev2)
                res += (if (i % 2 == 0) ires else ires.negation)
            }
            val coeff = Rational(if (n % 2 == 0) Integer(-1) else Integer(1), Integer(n))
            ev(res).partialQMult(coeff).map(ev2) match {
                case None => throw new AlgebraicException("Wilkersons hypothesis did not hold! You may not use lambda in this ring")
                case Some(r) => {
                    lambdacache += n -> r
                    return r
                }
            } 
        }
        case _ => throw new AlgebraicException("Lambda operation only defined for n >= 0 !")
    }
}

/** Standard implementation of [[LambdaRing]], using [[STDLambdaRingElement]] */
trait STDLambdaRing[E <: STDLambdaRingElement[E]] extends LambdaRing[E]{
    override def psi (x : E)(n : Int) : E = x.psi(n)
    override def lambda[S <: PartialQAlgebraElement[S]] (x : E)(n : Int)(implicit ev: E => S, ev2: S => E) : E = x.lambda(n)(ev, ev2)
}
