package io.github.torsteinvik.zetatypes.algebra.parsing

import io.github.torsteinvik.zetatypes.algebra._
import io.github.torsteinvik.zetatypes.tannakiansymbols._
import io.github.torsteinvik.zetatypes.tannakiansymbols.DSL._

/** Parses an algebraic [[io.github.torsteinvik.zetatypes.tannakiansymbols.TannakianSymbol]] from an input String */
object TannakianSymbolParser {
    import AlgebraicParser._
    /** Parses an algebraic [[io.github.torsteinvik.zetatypes.tannakiansymbols.TannakianSymbol]] from an input String */
    def apply[T <: MonoidElement](element : Parser[T], monoid : Monoid[T]) : Parser[TannakianSymbol[T]] = multiset(element, monoid) ~ ("/" ~> multiset(element, monoid)) ^^ {
        case upstairs ~ downstairs => (ms(upstairs : _*) / ms(downstairs : _*))(monoid, implicitly)
    }
    
    private def multiset[T <: MonoidElement](element : Parser[T], monoid : Monoid[T]) : Parser[Seq[T]] = emptyset | ("{" ~> repsep(element, ",") <~ "}")
    
    private def emptyset[T] : Parser[Seq[T]] = "Ø" ^^^ Seq.empty[T]
    
}
