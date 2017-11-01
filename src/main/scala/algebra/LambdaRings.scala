package io.zetatypes.algebra

trait LambdaRingElement[T <: LambdaRingElement[T]] extends RingElement[T]

trait LambdaRing[E <: LambdaRingElement[E]] extends Ring[E] {
    def psi (x : E)(n : Int) : E
}

trait StandardLambdaRingElement[T <: StandardLambdaRingElement[T]] extends LambdaRingElement[T] {
    def psi (n : Int) : T
}

trait StandardLambdaRing[E <: StandardLambdaRingElement[E]] extends LambdaRing[E]{
    def psi (x : E)(n : Int) : E = x.psi(n)
}
