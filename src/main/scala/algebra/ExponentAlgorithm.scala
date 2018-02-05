package io.github.torsteinvik.zetatypes.algebra

private[algebra] object MonoidRepetitionAlgorithm {

    def apply[T](f : (T, T) => T, n : Int, unit : T, x : T) : T = n match {
        case 0 => unit
        case 1 => x
        case _ if n > 1 => applyImpl[T](f, n, unit, x) 
        case _ => throw new AlgebraicException("Monoidal repeated combination requires n >= 0")
    }
    
    import scala.annotation.tailrec
        
    @tailrec
    private[MonoidRepetitionAlgorithm] def applyImpl[T](f : (T, T) => T, n : Int, acc : T, x : T) : T = n match {
        case 0 => acc
        case _ => applyImpl[T](f, n >> 1, if ((n & 1) == 1) f(acc, x) else acc, f(x, x))
    }
}
