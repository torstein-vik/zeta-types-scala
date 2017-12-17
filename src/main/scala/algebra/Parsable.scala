package io.github.torsteinvik.zetatypes.algebra

import scala.util.parsing.combinator._
import scala.util.parsing.input._

/** Some [[AlgebraicStructure]] with elements that are parsable (from Strings) 
 *  @tparam T The type of [[AlgebraicElement]] that is parsed from a string
 */
trait Parsable[T <: AlgebraicElement] extends AlgebraicStructure[T] with RegexParsers {
    def parser : Parser[T]
}


/** Exception coming from lexing the input
 *  @param msg message of the exception
 *  @param pos position in the input
 */
case class ParserException(msg : String, pos : Position) extends 
    Exception("Parsing error at line " + pos.line + " column " + pos.column + "\n" + pos.longString)
