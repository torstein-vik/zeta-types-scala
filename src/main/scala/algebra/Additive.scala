package io.github.torsteinvik.zetatypes.algebra

trait ZeroProvider[+T] {
    def zero : T
}

trait AdditionProvider[-X, -Y, +T] { 
    def add (x : X, y : Y) : T 
}

trait ModuleMultiplicationProvider[-X, +T] {
    def mmultiply (x : X)(y : Int) : T
}

trait NegationProvider[-X, +T] { 
    def negate (x : X) : T 
}

trait SubtractionProvider[-X, -Y, +T] { 
    def subtract (x : X, y : Y) : T 
}

trait PartialQModuleDivisionProvider[-X, +T] {
    def partialmdivide (x : X)(y : Int) : Option[T]
}

trait QModuleDivisionProvider[-X, +T] extends PartialQModuleDivisionProvider[X, T] {
    def mdivide (x : X)(y : Int) : T 
    override final def partialmdivide (x : X)(y : Int) : Option[T] = Some(mdivide(x)(y))
}

trait AdditiveMonoid[T] extends AdditionProvider[T, T, T] with ZeroProvider[T]
    with ModuleMultiplicationProvider[T, T] {
    def mmultiply (x : T)(i : Int) : T = MonoidRepetitionAlgorithm[T](add _, i, zero, x)
}

trait AdditiveGroup[T] extends AdditiveMonoid[T] with NegationProvider[T, T]
    with SubtractionProvider[T, T, T] {
    def subtract (x : T, y : T) = add(x, negate(y))
    override def mmultiply (x : T)(i : Int) : T = i match {
        case _ if i >= 0 => super.mmultiply(x)(i)
        case _ => super.mmultiply(negate(x))(-i)
    }
}

