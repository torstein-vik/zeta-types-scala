package io.github.torsteinvik.zetatypes.algebra.parsing

import scala.util.parsing.combinator._

import io.github.torsteinvik.zetatypes.algebra.structures._

object IntegerParser{
    import AlgebraicParser._
    def apply = """-?\d+""".r ^^ (str => Integer(BigInt(str)))
}
