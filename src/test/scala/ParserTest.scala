package io.github.torsteinvik.zetatypes.test

import org.scalatest.FunSuite

import io.github.torsteinvik.zetatypes.algebra._
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
        implicit val ring : Ring[Integer] = Integers
        
        assert(parse[Rational]("12") === Rational(12))
        assert(parse[Rational]("14/6") === Rational(14, 6))
        assert(parse[Rational]("-1/3432") === Rational(-1, 3432))
        assert(parse[Rational]("((-1))/(3432)") === Rational(-1, 3432))
        assert(parse[Rational]("((-1))/(3432)") === Rational(-1, 3432))
        assert(parse[Rational]("2/ 3") === Rational(2, 3))
        assert(parse[Rational]("2 /3") === Rational(2, 3))
        
        assertThrows[ParserException]{ parse[Rational]("13i + 2i") }
        
    }
    
    test ("complex test") {
        implicit val ringint : Ring[Integer] = Integers
        implicit val ringrat : Ring[Rational] = Rationals
                
        assert(parse[Complex]("12") === Complex(12))
        assert(parse[Complex]("14 + 6i") === Complex(14, 6))
        assert(parse[Complex]("14 - 3i") === Complex(14, -3))
        assert(parse[Complex]("14 - 3/2i") === Complex(14, -Rational(3, 2)))
        assert(parse[Complex]("3i") === Complex(0, 3))
        assert(parse[Complex]("i") === Complex(0, 1))
        assert(parse[Complex]("-i") === Complex(0, -1))
        assert(parse[Complex]("12 + i") === Complex(0, 1))
        assert(parse[Complex]("12 - i") === Complex(0, -1))
        assert(parse[Complex]("13/5") === Complex(Rational(13, 5)))
        assert(parse[Complex]("(13/5) + 5i") === Complex(Rational(13, 5), 5))
        assert(parse[Complex]("13/5 + ((5))i") === Complex(Rational(13, 5), 5))
        
        assertThrows[ParserException]{ parse[Complex]("13i + 2i") }
        assertThrows[ParserException]{ parse[Complex]("13 + 2") }
        assertThrows[ParserException]{ parse[Complex]("433 443") }
        assertThrows[ParserException]{ parse[Complex]("hello") }
            
    }
    
    test ("polynomial test") {
        implicit val ringint : Ring[Integer] = Integers
        implicit val ringrat : Ring[Rational] = Rationals
        implicit val ringcom : Ring[Complex] = Complex
                
        assert(parse[Polynomial[Integer]]("12") === new Polynomial[Integer](Seq((12, 0))))
        assert(parse[Polynomial[Integer]]("12 + 3x") === new Polynomial[Integer](Seq((12, 0), (3, 1))))
        assert(parse[Polynomial[Integer]]("12 + 3x + 452x^241") === new Polynomial[Integer](Seq((12, 0), (3, 1), (452, 241))))
        assert(parse[Polynomial[Integer]]("x") === new Polynomial[Integer](Seq((1, 1))))
        assert(parse[Polynomial[Integer]]("x^12") === new Polynomial[Integer](Seq((1, 12))))
        assert(parse[Polynomial[Integer]]("x + x^12") === new Polynomial[Integer](Seq((1, 1), (1, 12))))
        assert(parse[Polynomial[Integer]]("x + x^12") === new Polynomial[Integer](Seq((1, 1), (1, 12))))
        assert(parse[Polynomial[Integer]]("0") === new Polynomial[Integer](Seq.empty))
        
        assert(parse[Polynomial[Complex]]("21ix + (23/2 + i)x^2") === new Polynomial[Complex](Seq((Complex(0, 21), 1), (Complex(Rational(23, 2), 1), 2))))
        assert(parse[Polynomial[Complex]]("x") === new Polynomial[Complex](Seq((Complex(0), 1))))
        
        assertThrows[ParserException]{ parse[Polynomial[Integer]]("13i * 2") }
        assertThrows[ParserException]{ parse[Polynomial[Integer]]("13 * 2x") }
        assertThrows[ParserException]{ parse[Polynomial[Integer]]("433 443") }
        assertThrows[ParserException]{ parse[Polynomial[Integer]]("hello") }
            
    }
}