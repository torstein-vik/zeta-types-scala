package org.zetatypes.algebra

import structures.{Integer, Rational}

trait LambdaRingElement[T <: LambdaRingElement[T]] extends RingElement[T]

trait LambdaRing[E <: LambdaRingElement[E]] extends Ring[E] {
    def psi (x : E)(n : Int) : E
    def lambda[S <: PartialQAlgebraElement[S]] (x : E)(n : Int)(implicit ev: E => S, ev2: S => E) : E
}

trait StandardLambdaRingElement[T <: StandardLambdaRingElement[T]] extends LambdaRingElement[T] {
    def psi (n : Int) : T
    
    private var lambdacache : Map[Int, T] = Map()
    def lambda[S <: PartialQAlgebraElement[S]] (n : Int)(implicit ev: T => S, ev2: S => T) : T = n match {
        case 0 => canonicalRing.one
        case 1 => this * canonicalRing.one
        case n if lambdacache.contains(n) => lambdacache(n)
        case n if n > 1 => {
            var res = canonicalRing.additive.zero
            for (i <- 0 to n - 1) {
                val ires = this.psi(n - i) * this.lambda(i)
                res += (if (i % 2 == 0) ires else ires.negation)
            }
            val mayberes : Option[T] = ev(res).partialQMult(Rational(if (n % 2 == 0) Integer(-1) else Integer(1), Integer(n))).map(ev2)
            mayberes match {
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

trait StandardLambdaRing[E <: StandardLambdaRingElement[E]] extends LambdaRing[E]{
    override def psi (x : E)(n : Int) : E = x.psi(n)
    override def lambda[S <: PartialQAlgebraElement[S]] (x : E)(n : Int)(implicit ev: E => S, ev2: S => E) : E = x.lambda(n)(ev, ev2)
}
