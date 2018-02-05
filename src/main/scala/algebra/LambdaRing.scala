package io.github.torsteinvik.zetatypes.algebra

trait PsiProvider[-X, +T] { 
    def psi (x : X)(n : Int) : T 
}

