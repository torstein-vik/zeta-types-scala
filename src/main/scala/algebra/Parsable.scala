package io.github.torsteinvik.zetatypes.algebra

import scala.util.parsing.combinator._
import scala.util.parsing.input._

case class ParserException(msg : String, pos : Position) extends 
    Exception("Parsing error at line " + pos.line + " column " + pos.column + "\n" + pos.longString)
