import org.scalatest.FunSuite

import org.zetatypes.sequences._

class SequenceFactoryTest extends FunSuite {
    
    {// CachedSequence test
        import org.zetatypes.sequences.DSL._
        
        test ("CachedSequence works") {
            val seq = new CachedSequence((i : Int) => i)
            
            (0 to 100) foreach {i => 
                assert(seq(i) == i)
            }
            
            (0 to 100) foreach {i => 
                assert(seq(i) == i)
            }
        }
        
        test ("CachedSequence caches") {
            var x = 0;
            val seq = new CachedSequence((i : Int) => {x += 1; x})
            
            (0 to 50) foreach {i => 
                assert(seq(1) == 1)
            }
        }
        
        test ("CachedSequence does not cache when factory has internal cache") {
            var x = 0;
            val seq = new CachedSequence(new SequenceFactory[Int]{
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
    }
    
    {// Factory tests
        test ("ConstantSequence factory") {
            Seq[Any](12, .1, "test", Map()) foreach{a => 
                val seq = new CachedSequence(new ConstantSequence(a))
                
                (0 to 100) foreach {i => 
                    assert(seq(i) === a)
                }
                
                assert(seq(1000000) === a)
            }
        }
        
        test ("ShortSequence factory") {
            
        }
    }
    
    {// Various user-defined sequences
        import org.zetatypes.sequences.DSL._
        
        test ("Sequence DSL test - Fibonacci sequence") {
            
            val seq = new CachedSequence(Seq(1, 1) followedBy ((seq : Sequence[Int]) => (i : Int) => seq(i - 1) + seq(i - 2)))
            
            assert(seq(0) === 1)
            assert(seq(1) === 1)
            assert(seq(2) === 2)
            assert(seq(3) === 3)
            assert(seq(4) === 5)
            assert(seq(5) === 8)
            assert(seq(6) === 13)
        }
        
        test ("Sequence DSL test - Fibonacci sequence 2") {
            
            val seq = new CachedSequence(1 upTo 1 followedBy ((seq : Sequence[Int]) => (i : Int) => seq(i - 1) + seq(i - 2)))
            
            assert(seq(0) === 1)
            assert(seq(1) === 1)
            assert(seq(2) === 2)
            assert(seq(3) === 3)
            assert(seq(4) === 5)
            assert(seq(5) === 8)
            assert(seq(6) === 13)
        }
        
        test ("Sequence DSL test - 1 1 1 2 2 2") {
            
            val seq = new CachedSequence(1 upTo 2 followedBy 3)
            
            assert(seq(0) === 1)
            assert(seq(1) === 1)
            assert(seq(2) === 1)
            assert(seq(3) === 3)
            assert(seq(4) === 3)
            assert(seq(5) === 3)
            assert(seq(6) === 3)
        }
        
    }
}
