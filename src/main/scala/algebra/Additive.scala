package io.github.torsteinvik.zetatypes.algebra

trait ZeroProvider[+T] {
    def zero : T
}

trait AdditionProvider[-X, -Y, +T] { 
    def add (x : X, y : Y) : T 
}

