package io.github.torsteinvik.zetatypes.algebra

trait OneProvider[+T] {
    def one : T
}

trait MultiplicationProvider[-X, -Y, +T] { 
    def multiply (x : X, y : Y) : T 
}


trait InverseProvider[-X, +T] { 
    def invert (x : X) : T 
}

trait DivisionProvider[-X, -Y, +T] { 
    def divide (x : X, y : Y) : T 
}

trait MultiplicativeMonoid[T] extends MultiplicationProvider[T, T, T] with OneProvider[T] {
}

trait MultiplicativeGroup[T] extends MultiplicativeMonoid[T] with InverseProvider[T, T] {
}
