package org.torsteinv.zetatypes.test

import org.scalatest.FunSuite

import org.torsteinv.zetatypes.sequences._
import org.torsteinv.zetatypes.sequences.DSL._
import org.torsteinv.zetatypes.algebra._
import org.torsteinv.zetatypes.algebra.structures._
import org.torsteinv.zetatypes.algebra.structures.DSL._

class SpecialAlgebraicSequenceTest extends FunSuite {
    
    
    test ("identities test") {
        assert(SpecialComplexSequences.additive.identity.createSeq(0, 10)       === Seq[Complex](1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0))
        assert(SpecialComplexSequences.multiplicative.identity.createSeq(0, 10) === Seq[Complex](1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1))
    }
    
    test ("zero and one test") {
        assert(SpecialComplexSequences.zero.createSeq(0, 10) === Seq[Complex](1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0))
        assert(SpecialComplexSequences.one.createSeq(0, 10)  === Seq[Complex](1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1))
    }
    
    {
        val A = SpecialComplexSequence((i : Int) => Complex(i))
        val B = SpecialComplexSequence((i : Int) => Complex(i * i))
        val C = SpecialComplexSequence((i : Int) => Complex(2 + 3 * i))
        val D = SpecialComplexSequence((i : Int) => Complex(3 + 2 * i))
        
        test ("additive inverse test") {
            assert(SpecialComplexSequences.additive.invert(A).createSeq(0, 6) === Seq[Complex](1, -1, -2, -3, -4, -5, -6))
            assert(SpecialComplexSequences.additive.invert(B).createSeq(0, 6) === Seq[Complex](1, -1, -4, -9, -16, -25, -36))
            assert(SpecialComplexSequences.additive.invert(C).createSeq(0, 6) === Seq[Complex](1, -5, -8, -11, -14, -17, -20))
            assert(SpecialComplexSequences.additive.invert(D).createSeq(0, 6) === Seq[Complex](1, -5, -7, -9, -11, -13, -15))
        }
        
        test ("addition test") {
            assert(SpecialComplexSequences.additive.combine(A, B).createSeq(0, 6) === Seq[Complex](1, 2, 6, 12, 20, 30, 42))
            assert(SpecialComplexSequences.additive.combine(B, C).createSeq(0, 6) === Seq[Complex](1, 6, 12, 20, 30, 42, 56))
            assert(SpecialComplexSequences.additive.combine(C, D).createSeq(0, 6) === Seq[Complex](1, 10, 15, 20, 25, 30, 35))
            assert(SpecialComplexSequences.additive.combine(D, A).createSeq(0, 6) === Seq[Complex](1, 6, 9, 12, 15, 18, 21))
        }
        
        test ("multiplication test") {
            assert(SpecialComplexSequences.multiplicative.combine(A, B).createSeq(0, 6) === Seq[Complex](1, 1, 8, 27, 64, 125, 216))
            assert(SpecialComplexSequences.multiplicative.combine(B, C).createSeq(0, 6) === Seq[Complex](1, 5, 32, 99, 224, 425, 720))
            assert(SpecialComplexSequences.multiplicative.combine(C, D).createSeq(0, 6) === Seq[Complex](1, 25, 56, 99, 154, 221, 300))
            assert(SpecialComplexSequences.multiplicative.combine(D, A).createSeq(0, 6) === Seq[Complex](1, 5, 14, 27, 44, 65, 90))
        }
        
        test ("notation additive inverse test") {
            assert((-A).createSeq(0, 6) === Seq[Complex](1, -1, -2, -3, -4, -5, -6))
            assert((-B).createSeq(0, 6) === Seq[Complex](1, -1, -4, -9, -16, -25, -36))
            assert((-C).createSeq(0, 6) === Seq[Complex](1, -5, -8, -11, -14, -17, -20))
            assert((-D).createSeq(0, 6) === Seq[Complex](1, -5, -7, -9, -11, -13, -15))
        }
        
        test ("notation addition test") {
            assert((A + B).createSeq(0, 6) === Seq[Complex](1, 2, 6, 12, 20, 30, 42))
            assert((B + C).createSeq(0, 6) === Seq[Complex](1, 6, 12, 20, 30, 42, 56))
            assert((C + D).createSeq(0, 6) === Seq[Complex](1, 10, 15, 20, 25, 30, 35))
            assert((D + A).createSeq(0, 6) === Seq[Complex](1, 6, 9, 12, 15, 18, 21))
        }
        
        test ("notation multiplication test") {
            assert((A * B).createSeq(0, 6) === Seq[Complex](1, 1, 8, 27, 64, 125, 216))
            assert((B * C).createSeq(0, 6) === Seq[Complex](1, 5, 32, 99, 224, 425, 720))
            assert((C * D).createSeq(0, 6) === Seq[Complex](1, 25, 56, 99, 154, 221, 300))
            assert((D * A).createSeq(0, 6) === Seq[Complex](1, 5, 14, 27, 44, 65, 90))
        }
                
        test ("subtraction test") {
            assert((A - B).createSeq(0, 6) === Seq[Complex](1, 0, -2, -6, -12, -20, -30))
            assert((B - C).createSeq(0, 6) === Seq[Complex](1, -4, -4, -2, 2, 8, 16))
            assert((C - D).createSeq(0, 6) === Seq[Complex](1, 0, 1, 2, 3, 4, 5))
            assert((D - A).createSeq(0, 6) === Seq[Complex](1, 4, 5, 6, 7, 8, 9))
        }
        
        test ("Sequence exponentiation") {
            assert((A ** 1).createSeq(0, 6) === Seq[Complex](1, 1, 2, 3, 4, 5, 6))
            assert((B ** 1).createSeq(0, 6) === Seq[Complex](1, 1, 4, 9, 16, 25, 36))
            assert((C ** 1).createSeq(0, 6) === Seq[Complex](1, 5, 8, 11, 14, 17, 20))
            assert((D ** 1).createSeq(0, 6) === Seq[Complex](1, 5, 7, 9, 11, 13, 15))
            
            assert((A ** 2).createSeq(0, 6) === Seq[Complex](1, 1, 4, 9, 16, 25, 36))
            assert((B ** 2).createSeq(0, 6) === Seq[Complex](1, 1, 16, 81, 256, 625, 1296))
            assert((C ** 2).createSeq(0, 6) === Seq[Complex](1, 25, 64, 121, 196, 289, 400))
            assert((D ** 2).createSeq(0, 6) === Seq[Complex](1, 25, 49, 81, 121, 169, 225))
            
            assert((A ** 3).createSeq(0, 6) === Seq[Complex](1, 1, 8, 27, 64, 125, 216))
            assert((B ** 3).createSeq(0, 6) === Seq[Complex](1, 1, 64, 729, 4096, 15625, 46656))
            assert((C ** 3).createSeq(0, 6) === Seq[Complex](1, 125, 512, 1331, 2744, 4913, 8000))
            assert((D ** 3).createSeq(0, 6) === Seq[Complex](1, 125, 343, 729, 1331, 2197, 3375))
            
            assertThrows[AlgebraicException] {
                A ** 0
            }
            
            assertThrows[AlgebraicException] {
                A ** (-1)
            }
        }
        
        test ("Sequence module multiplication") {
            assert((A ++ 1).createSeq(0, 6) === Seq[Complex](1, 1, 2, 3, 4, 5, 6))
            assert((B ++ 1).createSeq(0, 6) === Seq[Complex](1, 1, 4, 9, 16, 25, 36))
            assert((C ++ 1).createSeq(0, 6) === Seq[Complex](1, 5, 8, 11, 14, 17, 20))
            assert((D ++ 1).createSeq(0, 6) === Seq[Complex](1, 5, 7, 9, 11, 13, 15))
            
            assert((A ++ 2).createSeq(0, 6) === Seq[Complex](1, 2, 4, 6, 8, 10, 12))
            assert((B ++ 2).createSeq(0, 6) === Seq[Complex](1, 2, 8, 18, 32, 50, 72))
            assert((C ++ 2).createSeq(0, 6) === Seq[Complex](1, 10, 16, 22, 28, 34, 40))
            assert((D ++ 2).createSeq(0, 6) === Seq[Complex](1, 10, 14, 18, 22, 26, 30))
            
            assert((A ++ 3).createSeq(0, 6) === Seq[Complex](1, 3, 6, 9, 12, 15, 18))
            assert((B ++ 3).createSeq(0, 6) === Seq[Complex](1, 3, 12, 27, 48, 75, 108))
            assert((C ++ 3).createSeq(0, 6) === Seq[Complex](1, 15, 24, 33, 42, 51, 60))
            assert((D ++ 3).createSeq(0, 6) === Seq[Complex](1, 15, 21, 27, 33, 39, 45))
            
            assertThrows[AlgebraicException] {
                A ++ 0
            }
            
            assertThrows[AlgebraicException] {
                A ++ (-1)
            }
        }
    }
}
