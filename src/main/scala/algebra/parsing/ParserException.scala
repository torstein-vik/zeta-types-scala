package io.github.torsteinvik.zetatypes.algebra.parsing

/** Exception coming from lexing the input
 *  @param msg message of the exception
 *  @param pos position in the input
 */
case class ParserException(msg : String, pos : Position) extends 
    Exception("Parsing error at line " + pos.line + " column " + pos.column + "\n" + pos.longString)
