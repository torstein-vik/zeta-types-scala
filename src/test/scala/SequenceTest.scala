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
        
    }
}
