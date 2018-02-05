package io.github.torsteinvik.zetatypes.algebra

trait Ring[T] extends AdditiveGroup[T] with MultiplicativeMonoid[T]
trait PartialQAlgebra[T] extends Ring[T] with PartialQModuleDivisionProvider[T, T] 
trait QAlgebra[T] extends PartialQAlgebra[T] with QModuleDivisionProvider[T, T] 
