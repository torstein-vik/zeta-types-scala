package io.github.torsteinvik.zetatypes.algebra

trait PsiProvider[-X, +T] { 
    def psi (x : X)(n : Int) : T 
}

trait LambdaProvider[-X, +T] { 
    def lambda (x : X)(n : Int) : T 
}

