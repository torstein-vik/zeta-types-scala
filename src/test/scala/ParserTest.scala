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
        
    
}
