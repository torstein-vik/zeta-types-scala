import org.scalatest.FunSuite

import org.zetatypes.sequences._
import org.zetatypes.sequences.DSL._
import org.zetatypes.algebra._
import org.zetatypes.algebra.structures._
import org.zetatypes.algebra.structures.DSL._

class AlgebraicSequenceTest extends FunSuite {
    
    
    test ("identities test") {
        assert(ComplexSequences.additive.identity.createSeq(0, 10)       === Seq[Complex](0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0))
        assert(ComplexSequences.multiplicative.identity.createSeq(0, 10) === Seq[Complex](1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1))
    }
    
    test ("zero and one test") {
        assert(ComplexSequences.zero.createSeq(0, 10) === Seq[Complex](0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0))
        assert(ComplexSequences.one.createSeq(0, 10)  === Seq[Complex](1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1))
    }
    
    {
        val A = ComplexSequence((i : Int) => Complex(i))
        val B = ComplexSequence((i : Int) => Complex(i * i))
        val C = ComplexSequence((i : Int) => Complex(2 + 3 * i))
        val D = ComplexSequence((i : Int) => Complex(3 + 2 * i))
        
        test ("additive inverse test") {
            assert(ComplexSequences.additive.invert(A).createSeq(0, 6) === Seq[Complex](0, -1, -2, -3, -4, -5, -6))
            assert(ComplexSequences.additive.invert(B).createSeq(0, 6) === Seq[Complex](0, -1, -4, -9, -16, -25, -36))
            assert(ComplexSequences.additive.invert(C).createSeq(0, 6) === Seq[Complex](-2, -5, -8, -11, -14, -17, -20))
            assert(ComplexSequences.additive.invert(D).createSeq(0, 6) === Seq[Complex](-3, -5, -7, -9, -11, -13, -15))
        }
        
    }
}