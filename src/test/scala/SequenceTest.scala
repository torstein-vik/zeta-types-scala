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
        
    }
}
