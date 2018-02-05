package io.github.torsteinvik.zetatypes.algebra

trait ZeroProvider[+T] {
    def zero : T
}

trait AdditionProvider[-X, -Y, +T] { 
    def add (x : X, y : Y) : T 
}


trait NegationProvider[-X, +T] { 
    def negate (x : X) : T 
}

trait SubtractionProvider[-X, -Y, +T] { 
    def subtract (x : X, y : Y) : T 
}

trait AdditiveMonoid[T] extends AdditionProvider[T, T, T] with ZeroProvider[T] {
}

trait AdditiveGroup[T] extends AdditiveMonoid[T] with NegationProvider[T, T]
    with SubtractionProvider[T, T, T] {
    def subtract (x : T, y : T) = add(x, negate(y))
}

