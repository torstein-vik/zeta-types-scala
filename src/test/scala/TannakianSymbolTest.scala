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
            
            import io.zetatypes.algebra.structures.DSL._
            assert(TS(Integers).one.toString() === "{1}/Ø")
            assert(TS(Integers).zero.toString() === "Ø/Ø")
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
    
    {// Using ms(...) / ms(...) notation
        import io.zetatypes.tannakiansymbols.DSL._
        import io.zetatypes.algebra.structures.DSL._
        val X = new TannakianSymbol(Seq((Integer(1), 1), (Integer(2), -1), (Integer(3), 1)))
        val Y = new TannakianSymbol(Seq((Integer(10), 5), (Integer(11), -4), (Integer(2), -1)))
        val Z = new TannakianSymbol(Seq((Integer(2), 2), (Integer(3), -8), (Integer(4), 4)))
        val W = new TannakianSymbol(Seq((Integer(-1), 1), (Integer(10), 0), (Integer(2), 1)))
        
        test ("TS DSL notaion") {
            assert(X === ms(1, 3) / ms(2))
            assert(Y === ms(10, 10, 10, 10, 10) / ms(11, 11, 11, 11, 2))
            assert(Z === ms(2, 2, 4, 4, 4, 4) / ms(3, 3, 3, 3, 3, 3, 3, 3))
            assert(W === ms(-1, 2) / Ø)
            assert(TS(Integers).one === ms(1) / Ø)
            assert(TS(Integers).zero === Ø / Ø)
        }
        
        test ("TS. upstairs / downstairs test") {
            Seq(X, Y, Z, W).foreach(x => assert(x === x.upstairs / x.downstairs))
        }
    }
    
    {// Addition & Multication test
        import io.zetatypes.tannakiansymbols.DSL._
        import io.zetatypes.algebra.structures.DSL._
        
        test ("TS Addition") {
            assert(ms(1, 2) / Ø + ms(1, 2) / Ø === ms(1, 1, 2, 2) / Ø)
            assert(ms(1, 2) / Ø + Ø / ms(1, 2) === Ø / Ø)
            assert(ms(1) / Ø + Ø / ms(2) === ms(1) / ms(2))
            assert(ms(1, 2, 2, 2) / ms(3, 3) + ms(3, 4, 5) / ms(2, 7) === ms(1, 2, 2, 4, 5) / ms(3, 7))
        }
        
        test ("TS Negation") {
            assert(- (ms(1, 2) / Ø) === Ø / ms(1, 2))
            assert(- (ms(2, 3) / ms(5, 5)) === ms(5, 5) / ms(2, 3))
            assert(- (Ø / ms(5, 5)) === ms(5, 5) / Ø)
            assert(- (Ø / Ø) === Ø / Ø)
        }
        
        test ("TS Subtraction") {
            assert(ms(1, 2)/Ø - ms(2)/Ø === ms(1)/Ø)
            assert(ms(1, 2)/Ø - ms(3)/Ø === ms(1, 2)/ms(3))
            assert(ms(1, 2)/Ø - ms(1, 2)/ms(3) === ms(3)/Ø)
            assert(Ø/Ø - ms(1)/ms(3) === ms(3)/ms(1))
        }
        
        test ("TS Multiplication") {
            assert((ms(1)/Ø) * (ms(1)/Ø) === ms(1)/Ø)
            assert((ms(2)/Ø) * (ms(3)/Ø) === ms(6)/Ø)
            assert((ms(1, 2)/Ø) * (ms(1, 2)/Ø) === ms(1, 2, 2, 4)/Ø)
            assert((ms(1)/ms(2, 3)) * (ms(1)/Ø) === ms(1)/ms(2, 3))
            assert((ms(1, 2)/ms(3, 3)) * (ms(2)/Ø) === ms(2, 4)/ms(6, 6))
            assert((ms(1, 2)/ms(3, 3)) * (ms(1, 2)/ms(3, 3)) === ms(1, 2, 2, 4, 9, 9, 9, 9)/ms(3, 3, 6, 6))
            assert((Ø/ms(2, 3)) * (ms(1, 2)/Ø) === Ø/ms(2, 3, 4, 6))
            assert((ms(-1)/ms(1, 2, 3)) * (ms(1, -1)/Ø) === ms(1, -1)/ms(2, 3, -2, -3))
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
