import org.scalatest.FunSuite

class TannakianSymbolTest extends FunSuite{
    import scala.language.implicitConversions
    import io.zetatypes.tannakiansymbols.DSL._
        
    test ("Defining TannakianSymbol using multisets"){
        assertCompiles("ms(1, 1, 2) / Ø")
        assertCompiles("Ø / Ø")
        assertCompiles("ms(1, 2, 3) / ms(1, 2, 3, -4)")
    }
}

class TannakianSymbolRigorousTest extends FunSuite{
    import io.zetatypes.tannakiansymbols._
    import io.zetatypes.algebra.structures._
    
    {// Most basic tests
    
        val X     = new TannakianSymbol(Seq((Integer(1), 1), (Integer(2), -1)))
        val Xcopy = new TannakianSymbol(Seq((Integer(1), 1), (Integer(2), -1)))
        val Y     = new TannakianSymbol(Seq((Integer(3), 2), (Integer(2), 1)))
        val Ycopy = new TannakianSymbol(Seq((Integer(3), 2), (Integer(2), 1)))
        val Z     = new TannakianSymbol(Seq((Integer(1), 1), (Integer(2), -1), (Integer(3), 1)))
        val Zcopy = new TannakianSymbol(Seq((Integer(1), 1), (Integer(2), -1), (Integer(3), 1)))
        
        test ("TannakianSymbol basic equality") {
            assert(X !== Y)
            assert(Y !== Z)
            assert(Z !== X)
            
            assert(X === Xcopy)
            assert(Y === Ycopy)
            assert(Z === Zcopy)
        }
        
        test ("TannakianSymbol toString") {
            assert(X.toString() === "{1}/{2}")
            assert(Y.toString() === "{3, 3}/{2}")
            assert(Z.toString() === "{1, 3}/{2}")
        }
    }
    
    {// Cancellation tests
        
        val X     = new TannakianSymbol(Seq((Integer(1), 1), (Integer(1), -1), (Integer(2), 1)))
        val Xsimp = new TannakianSymbol(Seq((Integer(2), 1)))
        val Y     = new TannakianSymbol(Seq((Integer(10), 5), (Integer(10), -4), (Integer(2), -1)))
        val Ysimp = new TannakianSymbol(Seq((Integer(10), 1), (Integer(2), -1)))
        val Z     = new TannakianSymbol(Seq((Integer(3), 2), (Integer(3), -44), (Integer(3), 4)))
        val Zsimp = new TannakianSymbol(Seq((Integer(3), -38)))
        val W     = new TannakianSymbol(Seq((Integer(-1), 1), (Integer(10), 0), (Integer(2), 1)))
        val Wsimp = new TannakianSymbol(Seq((Integer(-1), 1), (Integer(2), 1)))
        
        test ("Cleanup works") {
            assert(X.cleanup.elements === Xsimp.elements)
            assert(Y.cleanup.elements === Ysimp.elements)
            assert(Z.cleanup.elements === Zsimp.elements)
            assert(W.cleanup.elements === Wsimp.elements)
        }
        
        test ("Equality works") {
            assert(X === Xsimp)
            assert(Y === Ysimp)
            assert(Z === Zsimp)
            assert(W === Wsimp)
            
            assert(X + Ysimp === Xsimp + Y)
            assert(Y + Zsimp === Ysimp + Z)
            assert(Z + Wsimp === Zsimp + W)
            assert(W + Xsimp === Wsimp + X)
            
            assert(X + Y + Z + W + Y + Y === Xsimp + Y + Z + Wsimp + Ysimp + Y)
        }
        
    }
    
    {// Upstairs & Downstairs
        import io.zetatypes.tannakiansymbols.DSL._
        val X = new TannakianSymbol(Seq((Integer(1), 1), (Integer(1), -1), (Integer(2), 1)))
        val Y = new TannakianSymbol(Seq((Integer(10), 5), (Integer(10), -4), (Integer(2), -1)))
        val Z = new TannakianSymbol(Seq((Integer(3), 2), (Integer(3), -8), (Integer(3), 4)))
        val W = new TannakianSymbol(Seq((Integer(-1), 1), (Integer(10), 0), (Integer(2), 1)))
        
        test ("TS.upstairs works") {
            assert(X.upstairs === ms(2))
            assert(Y.upstairs === ms(10))
            assert(Z.upstairs === Ø)
            assert(W.upstairs === ms(-1, 2))
        }
        
        test ("TS.downstairs works") {
            assert(X.downstairs === Ø)
            assert(Y.downstairs === ms(2))
            assert(Z.downstairs === ms(3, 3))
            assert(W.downstairs === ms(-1, 2))
        }
    }
}

class MultisetTest extends FunSuite {
    
    {// With DSL
        import io.zetatypes.tannakiansymbols.DSL._
        
        
        
    }
}
