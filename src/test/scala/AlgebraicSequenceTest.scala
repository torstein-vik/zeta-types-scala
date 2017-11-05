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
        
        test ("addition test") {
            assert(ComplexSequences.additive.combine(A, B).createSeq(0, 6) === Seq[Complex](0, 2, 6, 12, 20, 30, 42))
            assert(ComplexSequences.additive.combine(B, C).createSeq(0, 6) === Seq[Complex](2, 6, 12, 20, 30, 42, 56))
            assert(ComplexSequences.additive.combine(C, D).createSeq(0, 6) === Seq[Complex](5, 10, 15, 20, 25, 30, 35))
            assert(ComplexSequences.additive.combine(D, A).createSeq(0, 6) === Seq[Complex](3, 6, 9, 12, 15, 18, 21))
        }
        
        test ("multiplication test") {
            assert(ComplexSequences.multiplicative.combine(A, B).createSeq(0, 6) === Seq[Complex](0, 1, 8, 27, 64, 125, 216))
            assert(ComplexSequences.multiplicative.combine(B, C).createSeq(0, 6) === Seq[Complex](0, 5, 32, 99, 224, 425, 720))
            assert(ComplexSequences.multiplicative.combine(C, D).createSeq(0, 6) === Seq[Complex](6, 25, 56, 99, 154, 221, 300))
            assert(ComplexSequences.multiplicative.combine(D, A).createSeq(0, 6) === Seq[Complex](0, 5, 14, 27, 44, 65, 90))
        }
    }
}