package io.github.torsteinvik.zetatypes.algebra

trait OneProvider[+T] {
    def one : T
}

trait MultiplicationProvider[-X, -Y, +T] { 
    def multiply (x : X, y : Y) : T 
}

