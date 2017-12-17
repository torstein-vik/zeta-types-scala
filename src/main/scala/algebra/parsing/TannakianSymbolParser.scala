package io.github.torsteinvik.zetatypes.algebra.parsing

import scala.util.parsing.combinator._

import io.github.torsteinvik.zetatypes.algebra._
import io.github.torsteinvik.zetatypes.tannakiansymbols._
import io.github.torsteinvik.zetatypes.tannakiansymbols.DSL._

/** Parses an algebraic [[io.github.torsteinvik.zetatypes.tannakiansymbols.TannakianSymbol]] from an input String */
object TannakianSymbolParser {
    import AlgebraicParser._
    /** Parses an algebraic [[io.github.torsteinvik.zetatypes.tannakiansymbols.TannakianSymbol]] from an input String */
    def apply[T <: MonoidElement](element : Parser[T], monoid : Monoid[T]) : Parser[TannakianSymbol[T]] = ???
    
}
