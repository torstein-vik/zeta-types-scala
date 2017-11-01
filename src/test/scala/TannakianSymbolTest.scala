import org.scalatest.FunSuite

class TannakianSymbolTest extends FunSuite{
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
            assert(Y.toString() === "{3, 3, 2}/Ø")
            assert(Z.toString() === "{1, 3}/{2}")
            assert(new TannakianSymbols(Integers.multiplicative).one.toString() === "{1}/Ø")
            assert(new TannakianSymbols(Integers.multiplicative).zero.toString() === "Ø/Ø")
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
        import io.zetatypes.algebra.structures.DSL._
        val X = new TannakianSymbol(Seq((Integer(1), 1), (Integer(1), -1), (Integer(2), 1)))
        val Y = new TannakianSymbol(Seq((Integer(10), 5), (Integer(10), -4), (Integer(2), -1)))
        val Z = new TannakianSymbol(Seq((Integer(3), 2), (Integer(3), -8), (Integer(3), 4)))
        val W = new TannakianSymbol(Seq((Integer(-1), 1), (Integer(10), 0), (Integer(2), 1)))
        
        test ("TS.upstairs works") {
            assert(X.upstairs === ms[Integer](2))
            assert(Y.upstairs === ms[Integer](10))
            assert(Z.upstairs === Ø[Integer])
            assert(W.upstairs === ms[Integer](-1, 2))
        }
        
        test ("TS.downstairs works") {
            assert(X.downstairs === Ø[Integer])
            assert(Y.downstairs === ms[Integer](2))
            assert(Z.downstairs === ms[Integer](3, 3))
            assert(W.downstairs === Ø[Integer])
        }
    }
}

class MultisetTest extends FunSuite {
    
    {// With DSL
        import io.zetatypes.tannakiansymbols.DSL._
        
        test ("Multiset equality") {
            assert(Ø[Int] === ms())
            
            assert(ms(1, 2) === ms(1, 2))
            assert(ms(1, 2, 2, 3) === ms(1, 2, 2, 3))
            
            assert(ms(1, 2, 3) !== ms(1, 2))
            assert(ms(1, 2, 3) !== ms(1, 2, 3, 4))
            assert(ms(1, 2, 3) !== ms(1, 2, 2, 3))
            assert(ms(2, 3) !== ms(2, 2))
            assert(ms(2, 2) !== ms(2, 2, 1))
            assert(ms(2, 2) !== ms(2, 1))
            assert(ms(2, 1, 1) !== ms(2, 2, 1))
            assert(ms(2, 1, 1) !== ms(1, 2, 2))
            
            assert(ms(1, 2) === ms(2, 1))
            assert(ms(1, 2, 2) === ms(2, 1, 2))
            assert(ms(4, 8, 8, 2, 9) === ms(2, 4, 8, 8, 9))
        }
        
        test ("Multiset toString test") {
            assert(Ø[Int].toString === "Ø")
            
            assert(ms(1, 2, 3).toString === "{1, 2, 3}")
            assert(ms(1, 2, 2, 3).toString === "{1, 2, 2, 3}")
            assert(ms(10).toString === "{10}")
        }
        
    }
}
