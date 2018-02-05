package io.github.torsteinvik.zetatypes.algebra

trait OneProvider[+T] {
    def one : T
}

trait MultiplicationProvider[-X, -Y, +T] { 
    def multiply (x : X, y : Y) : T 
}

trait ExponentiationProvider[-X, +T] {
    def raise (x : X)(y : Int) : T
}

trait InverseProvider[-X, +T] { 
    def invert (x : X) : T 
}

trait DivisionProvider[-X, -Y, +T] { 
    def divide (x : X, y : Y) : T 
}

trait MultiplicativeMonoid[T] extends MultiplicationProvider[T, T, T] with OneProvider[T]
    with ExponentiationProvider[T, T] {
    def raise (x : T)(i : Int) : T = MonoidRepetitionAlgorithm[T](multiply _, i, one, x)
}

trait MultiplicativeGroup[T] extends MultiplicativeMonoid[T] with InverseProvider[T, T]
    with DivisionProvider[T, T, T] {
    def divide (x : T, y : T) = multiply(x, invert(y))
}
