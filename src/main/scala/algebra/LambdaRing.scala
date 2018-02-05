package io.github.torsteinvik.zetatypes.algebra

trait PsiProvider[-X, +T] { 
    def psi (x : X)(n : Int) : T 
}

trait LambdaProvider[-X, +T] { 
    def lambda (x : X)(n : Int) : T 
}

trait LambdaRing[T] extends Ring[T] with PsiProvider[T, T] with LambdaProvider[T, T]

trait PsiRing[T] extends LambdaRing[T] with PartialQAlgebra[T] {
    // Unpure optimization (memoization)
    import collection.mutable.Map
    
    private[PsiRing] var lambdacache : Map[(T, Int), T] = Map()
    
    override final def lambda (x : T)(n : Int) = n match {
        case 0 => one
        case 1 => x
        case n if lambdacache.contains((x, n)) => lambdacache((x, n))
        case n if n > 1 => partialmdivide((0 to n - 1).foldLeft(zero) {
            case (sum, i) if i % 2 == 0 =>      add(sum, multiply(psi(x)(n - i), lambda(x)(i)))
            case (sum, i) if i % 2 == 1 => subtract(sum, multiply(psi(x)(n - i), lambda(x)(i)))
        })(if (n % 2 == 0) -n else n) match {
            case Some(result) => {lambdacache += (x, n) -> result; result}
            case None => throw new AlgebraicException("Wilkersons hypothesis did not hold! You may not use lambda in this ring")
        }
        case _ => throw new AlgebraicException("Lambda operation only defined for n >= 0 !")
    }
}

