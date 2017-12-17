package io.github.torsteinvik.zetatypes.algebra

import scala.util.parsing.combinator._
import scala.util.parsing.input._

/** Some [[AlgebraicStructure]] with elements that are parsable (from Strings) 
 *  @tparam T The type of [[AlgebraicElement]] that is parsed from a string
 */
trait Parsable[T <: AlgebraicElement] extends AlgebraicStructure[T] {
    import AlgebraicParser._
        
    /** Parser creating an [[AlgebraicElement]] from an input string */
    def parser : Parser[T]
}

