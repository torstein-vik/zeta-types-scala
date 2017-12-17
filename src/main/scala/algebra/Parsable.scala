package io.github.torsteinvik.zetatypes.algebra

import scala.util.parsing.combinator._
import scala.util.parsing.input._

/** Some [[AlgebraicStructure]] with elements that are parsable (from Strings) 
 *  @tparam T The type of [[AlgebraicElement]] that is parsed from a string
 */
trait Parsable[T <: AlgebraicElement] extends AlgebraicStructure[T] with RegexParsers {
    /** Returns [[parser]] applied to the input string */
    final def parse (s : String) : T = parseAll(parser, s) match {
        case Success(result, _) => result
        case Failure(msg, next) => throw ParserException(msg, next.pos)
        case Error(msg, next) => throw ParserException(msg, next.pos)
    }
    
    /** Parser creating an [[AlgebraicElement]] from an input string */
    def parser : Parser[T]
}


/** Exception coming from lexing the input
 *  @param msg message of the exception
 *  @param pos position in the input
 */
case class ParserException(msg : String, pos : Position) extends 
    Exception("Parsing error at line " + pos.line + " column " + pos.column + "\n" + pos.longString)
