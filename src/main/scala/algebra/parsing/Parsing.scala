package io.github.torsteinvik.zetatypes.algebra.parsing

import scala.util.parsing.combinator._
import scala.util.parsing.input._

/** Global parsing object */
object AlgebraicParser extends RegexParsers {
    
    /** Returns [[parser]] applied to the input string */
    def parse[T] (str : String)(implicit parser : Parser[T]) : T = parseAll(parser, str) match {
        case Success(result, _) => result
        case Failure(msg, next) => throw ParserException(msg, next.pos)
        case Error(msg, next) => throw ParserException(msg, next.pos)
    }
    
    def paren[T] (p : Parser[T]) : Parser[T] = p | "(" ~> paren(p) <~ ")"
}
