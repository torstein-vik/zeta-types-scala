package io.github.torsteinvik.zetatypes.algebra.parsing

import scala.util.parsing.combinator._

import io.github.torsteinvik.zetatypes.algebra._
import io.github.torsteinvik.zetatypes.tannakiansymbols._
import io.github.torsteinvik.zetatypes.tannakiansymbols.DSL._

object TannakianSymbolParser {
    import AlgebraicParser._
    def apply[T <: MonoidElement](element : Parser[T], monoid : Monoid[T]) : Parser[TannakianSymbol[T]] = ???
    
}
