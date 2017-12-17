package io.github.torsteinvik.zetatypes.test

import org.scalatest.FunSuite

import io.github.torsteinvik.zetatypes.algebra.structures._
import io.github.torsteinvik.zetatypes.algebra.structures.DSL._
import io.github.torsteinvik.zetatypes.algebra.parsing._

class ParserTest extends FunSuite {
    
    test ("integer test") {
        assert(parse[Integer]("12") === Integer(12))
        assert(parse[Integer]("1232423423423253245235") === Integer(BigInt("1232423423423253245235")))
        assert(parse[Integer]("-7214172481") === Integer(-BigInt("7214172481")))
        
        assertThrows[ParserException]{ parse[Integer]("342342 43242") }
        assertThrows[ParserException]{ parse[Integer]("- 34234243242") }
        assertThrows[ParserException]{ parse[Integer]("hello") }
        
    }
        
    test ("rational test") {
        assert(parse[Rational]("12") === Rational(12))
        assert(parse[Rational]("14/6") === Rational(14, 6))
        assert(parse[Rational]("-1/3432") === Rational(-1, 3432))
        
        assertThrows[ParserException]{ parse[Rational]("13i + 2i") }
        assertThrows[ParserException]{ parse[Rational]("13/ 2") }
        assertThrows[ParserException]{ parse[Rational]("13 /2") }
        
    }
    
    
}
