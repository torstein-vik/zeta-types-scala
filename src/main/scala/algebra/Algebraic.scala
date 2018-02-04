package io.github.torsteinvik.zetatypes.algebra

/** An element of some [[AlgebraicStructure]] */
trait AlgebraicElement

/** Some structure of [[AlgebraicElement]] */
trait AlgebraicStructure[T <: AlgebraicElement]
