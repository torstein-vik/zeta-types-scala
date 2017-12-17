package io.github.torsteinvik.zetatypes.algebra

/** An element of some [[AlgebraicStructure]] */
trait AlgebraicElement

/** Some structure of [[AlgebraicElement]] */
trait AlgebraicStructure[T <: AlgebraicElement]

/** An exception coming from some algebraic method 
 *  @constructor Create an algebraic exception
 *  @param msg The message to be displayed alongside the exception
 */
class AlgebraicException(msg : String) extends Exception(msg)
