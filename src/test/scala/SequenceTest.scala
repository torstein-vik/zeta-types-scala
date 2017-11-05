import org.scalatest.FunSuite

import org.zetatypes.sequences._

class SequenceTest extends FunSuite {
    
    {// CachedSequence test
        import org.zetatypes.sequences.DSL._
        
        test ("CachedSequence works") {
            val seq = Sequence((i : Int) => i)
            
            (0 to 100) foreach {i => 
                assert(seq(i) == i)
            }
            
            (0 to 100) foreach {i => 
                assert(seq(i) == i)
            }
        }
        
        test ("CachedSequence caches") {
            var x = 0
            val seq = Sequence((i : Int) => {x += 1; x})
            
            (0 to 50) foreach {i => 
                assert(seq(1) == 1)
            }
        }
        
        test ("CachedSequence does not cache when factory has internal cache") {
            var x = 0
            val seq = Sequence(new SequenceFactory[Int]{
                def apply(seq : Sequence[Int])(i : Int) = {x += 1; x}
                override def hasInternalCache = true
            })
            
            (1 to 50) foreach {i => 
                assert(seq(1) == i)
            }
            
            (1 to 50) foreach {i => 
                assert(seq(1) == i + 50)
            }
        }
        
        test ("CachedSequence does not allow out of bounds") {
            val seq = Sequence(((i : Int) => i).upTo(7))
            
            assertThrows[SequenceException] {
                seq(-1)
            }
            
            assertThrows[SequenceException] {
                seq(-4)
            }
            
            assertThrows[SequenceException] {
                seq(8)
            }
            
            assert(seq(7) === 7)
            assert(seq(0) === 0)
            
            assert(seq.length === Some(8))
        }
        
        test ("CachedSequence flush test") {
            var x = 0
            val seq = Sequence((i : Int) => {x += 1; x})
            
            (0 to 50) foreach {i => 
                assert(seq(1) == 1)
            }
            
            seq.flush
            
            (0 to 50) foreach {i => 
                assert(seq(1) == 2)
            }
            
        }
        
        test ("CachedSequence createSeq test") {
            val seq = Sequence((i : Int) => i * i)
            
            assert(seq.createSeq(2, 5) === Seq(4, 9, 16, 25))
            assert(seq.createSeq(0, 2) === Seq(0, 1, 4))
            assert(seq.createSeq(2, 2) === Seq(4))
        }
        
        test ("CachedSequence asSeq test") {
            val seq1 = Sequence(((i : Int) => i * i) upTo 2)
            val seq2 = Sequence(((i : Int) => i * i) upTo 3)
            val seq3 = Sequence(((i : Int) => i * i) upTo 4)
            val seq4 = Sequence(((i : Int) => i * i) upTo 5)
            
            val seq = Sequence(((i : Int) => i * i))
            
            assert(seq1.asSeq === Some(Seq(0, 1, 4)))
            assert(seq2.asSeq === Some(Seq(0, 1, 4, 9)))
            assert(seq3.asSeq === Some(Seq(0, 1, 4, 9, 16)))
            assert(seq4.asSeq === Some(Seq(0, 1, 4, 9, 16, 25)))
            
            assert(seq.asSeq === None)
            
        }
        
        test ("CachedSequence iteration works") {
            val seq = Sequence(((i : Int) => i) upTo 100)
            
            var x = 0
            for {y <- seq} {
                assert(y === x)
                x += 1
            }
            
            assert(x === 101)
        }
        
        test ("CachedSequence toString works") {
            val seq = Sequence(((i : Int) => i))
            
            assert(seq.toString === "0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, ...")
            
            assert(seq.toString(5) === "0, 1, 2, 3, 4, 5, ...")
            
            val seq2 = Sequence(((i : Int) => i) upTo 7)
            
            assert(seq2.toString     === "0, 1, 2, 3, 4, 5, 6, 7, ...")
            assert(seq2.toString(10) === "0, 1, 2, 3, 4, 5, 6, 7, ...")
            assert(seq2.toString(7) === "0, 1, 2, 3, 4, 5, 6, 7, ...")
            assert(seq2.toString(8) === "0, 1, 2, 3, 4, 5, 6, 7, ...")
            
            val seq3 = Sequence(7)
            
            assert(seq3.toString(3) === "7, 7, 7, 7, ...")
        }
        
        test ("CachedSequence equals works") {
            val seq1  = Sequence(((i : Int) => i) upTo 30)
            val seq2  = Sequence(((i : Int) => i) upTo 30)
            val seq3  = Sequence(((i : Int) => i) upTo 30 followedBy 1 upTo 0)
            val seq4  = Sequence(((i : Int) => i) upTo 31)
            val seq5  = Sequence(((i : Int) => i))
            val seq6  = Sequence(((i : Int) => i))
            val seq7  = Sequence(((i : Int) => i + 1) upTo 30)
            val seq8  = Sequence(((i : Int) => i + 1) upTo 30)
            val seq9  = Sequence(((i : Int) => i + 1) upTo 30 followedBy 1 upTo 0)
            val seq10 = Sequence(((i : Int) => i + 1) upTo 31)
            val seq11 = Sequence(((i : Int) => i + 1))
            val seq12 = Sequence(((i : Int) => i + 1))
            
            assert(seq1 === seq2)
            assert(seq7 === seq8)
            
            assert(seq1 !== seq3)
            assert(seq1 !== seq4)
            assert(seq7 !== seq9)
            assert(seq7 !== seq10)
            
            assert(seq3 !== seq4)
            assert(seq9 !== seq10)
            
            assert(seq5 !== seq11)
            
            assertThrows[SequenceException] {
                seq5 == seq6
            }
            
            assertThrows[SequenceException] {
                seq11 == seq12
            }
            
        }
    }
    
    {// Factory tests
        test ("ConstantSequence factory") {
            Seq[Any](12, .1, "test", Map()) foreach{a => 
                val seq = Sequence(new ConstantSequence(a))
                
                (0 to 100) foreach {i => 
                    assert(seq(i) === a)
                }
                
                assert(seq(1000000) === a)
            }
        }
        
        test ("ShortSequence factory") {
            val seq = Sequence(new ShortSequence(1, 2, 4, 5))
            
            assert(seq(0) == 1)
            assert(seq(1) == 2)
            assert(seq(2) == 4)
            assert(seq(3) == 5)
        }
        
        test ("WrappedSequence factory") {
            import org.zetatypes.sequences.DSL._
            
            val seq = Sequence((i : Int) => i)
            val seq2 = Sequence(new WrappedSequence(seq))
            
            assert(seq2(0) === 0)
            assert(seq2(1) === 1)
            assert(seq2(2) === 2)
            assert(seq2(3) === 3)
            assert(seq2(4) === 4)
        }
        
        test ("CombinedSequence factory") {
            import org.zetatypes.sequences.DSL._
            
            val seq1 = Sequence((i : Int) => i)
            val seq2 = Sequence((i : Int) => i * i)
            
            val seq = Sequence(new CombinedSequence(seq1, seq2)(_ + _)(true))
            
            assert(seq(0) === 0)
            assert(seq(1) === 2)
            assert(seq(2) === 6)
            assert(seq(3) === 12)
            assert(seq(4) === 20)
        }
        
        test ("MappedSequence factory") {
            import org.zetatypes.sequences.DSL._
            
            val seq1 = Sequence((i : Int) => i)
            
            val seq = Sequence(new MappedSequence(seq1)(_ + 7)(true))
            
            assert(seq(0) === 7)
            assert(seq(1) === 8)
            assert(seq(2) === 9)
            assert(seq(3) === 10)
            assert(seq(4) === 11)
        }
    }
    
    {// Various user-defined sequences
        import org.zetatypes.sequences.DSL._
        
        test ("Sequence DSL test - Fibonacci sequence") {
            
            val seq = Sequence(Seq(1, 1) followedBy ((seq : Sequence[Int]) => (i : Int) => seq(i - 1) + seq(i - 2)))
            
            assert(seq(0) === 1)
            assert(seq(1) === 1)
            assert(seq(2) === 2)
            assert(seq(3) === 3)
            assert(seq(4) === 5)
            assert(seq(5) === 8)
            assert(seq(6) === 13)
        }
        
        test ("Sequence DSL test - Fibonacci sequence 2") {
            
            val seq = Sequence(1 upTo 1 followedBy ((seq : Sequence[Int]) => (i : Int) => seq(i - 1) + seq(i - 2)))
            
            assert(seq(0) === 1)
            assert(seq(1) === 1)
            assert(seq(2) === 2)
            assert(seq(3) === 3)
            assert(seq(4) === 5)
            assert(seq(5) === 8)
            assert(seq(6) === 13)
        }
        
        test ("Sequence DSL test - 1 1 1 2 2 2") {
            
            val seq = Sequence(1 upTo 2 followedBy 3)
            
            assert(seq(0) === 1)
            assert(seq(1) === 1)
            assert(seq(2) === 1)
            assert(seq(3) === 3)
            assert(seq(4) === 3)
            assert(seq(5) === 3)
            assert(seq(6) === 3)
        }
        
        test ("Sequence DSL test - 1 2 1 2 1 2 3 3 3") {
            
            val seq = Sequence(Seq(1, 2).repeat.upTo(5) followedBy 3)
            
            assert(seq(0) === 1)
            assert(seq(1) === 2)
            assert(seq(2) === 1)
            assert(seq(3) === 2)
            assert(seq(4) === 1)
            assert(seq(5) === 2)
            assert(seq(6) === 3)
            assert(seq(7) === 3)
            assert(seq(8) === 3)
        }
    }
}
