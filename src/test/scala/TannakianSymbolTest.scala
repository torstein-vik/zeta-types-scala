import org.scalatest.FunSuite

import org.zetatypes.algebra.structures.DSL.{Rational, Complex}

class TannakianSymbolTest extends FunSuite{
    import org.zetatypes.tannakiansymbols._
    import org.zetatypes.algebra.structures._
    import org.zetatypes.algebra._
    
    
    {// Most basic tests
        implicit val monoid : Monoid[Integer] = Integers.multiplicative
        
        val X     = new TannakianSymbol(Seq((Integer(1), 1 : BigInt), (Integer(2), -1 : BigInt)))
        val Xcopy = new TannakianSymbol(Seq((Integer(1), 1 : BigInt), (Integer(2), -1 : BigInt)))
        val Y     = new TannakianSymbol(Seq((Integer(3), 2 : BigInt), (Integer(2), 1 : BigInt)))
        val Ycopy = new TannakianSymbol(Seq((Integer(3), 2 : BigInt), (Integer(2), 1 : BigInt)))
        val Z     = new TannakianSymbol(Seq((Integer(1), 1 : BigInt), (Integer(2), -1 : BigInt), (Integer(3), 1 : BigInt)))
        val Zcopy = new TannakianSymbol(Seq((Integer(1), 1 : BigInt), (Integer(2), -1 : BigInt), (Integer(3), 1 : BigInt)))
        
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
            
            import org.zetatypes.algebra.structures.DSL._
            assert(TS(Integers).one.toString() === "{1}/Ø")
            assert(TS(Integers).zero.toString() === "Ø/Ø")
        }
    }
    
    {// Cancellation tests
        implicit val monoid : Monoid[Integer] = Integers.multiplicative
        
        val X     = new TannakianSymbol(Seq((Integer(1), 1 : BigInt), (Integer(1), -1 : BigInt), (Integer(2), 1 : BigInt)))
        val Xsimp = new TannakianSymbol(Seq((Integer(2), 1 : BigInt)))
        val Y     = new TannakianSymbol(Seq((Integer(10), 5 : BigInt), (Integer(10), -4 : BigInt), (Integer(2), -1 : BigInt)))
        val Ysimp = new TannakianSymbol(Seq((Integer(10), 1 : BigInt), (Integer(2), -1 : BigInt)))
        val Z     = new TannakianSymbol(Seq((Integer(3), 2 : BigInt), (Integer(3), -44 : BigInt), (Integer(3), 4 : BigInt)))
        val Zsimp = new TannakianSymbol(Seq((Integer(3), -38 : BigInt)))
        val W     = new TannakianSymbol(Seq((Integer(-1), 1 : BigInt), (Integer(10), 0 : BigInt), (Integer(2), 1 : BigInt)))
        val Wsimp = new TannakianSymbol(Seq((Integer(-1), 1 : BigInt), (Integer(2), 1 : BigInt)))
        
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
        implicit val monoid : Monoid[Integer] = Integers.multiplicative
        
        import org.zetatypes.tannakiansymbols.DSL._
        import org.zetatypes.algebra.structures.DSL._
        val X = new TannakianSymbol(Seq((Integer(1), 1 : BigInt), (Integer(1), -1 : BigInt), (Integer(2), 1 : BigInt)))
        val Y = new TannakianSymbol(Seq((Integer(10), 5 : BigInt), (Integer(10), -4 : BigInt), (Integer(2), -1 : BigInt)))
        val Z = new TannakianSymbol(Seq((Integer(3), 2 : BigInt), (Integer(3), -8 : BigInt), (Integer(3), 4 : BigInt)))
        val W = new TannakianSymbol(Seq((Integer(-1), 1 : BigInt), (Integer(10), 0 : BigInt), (Integer(2), 1 : BigInt)))
        
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
        
        test ("TS. upstairs / downstairs test") {
            Seq(X, Y, Z, W).foreach(x => assert(x === x.upstairs / x.downstairs))
        }
    }
    
    {// Using ms(...) / ms(...) notation integer
        implicit val monoid : Monoid[Integer] = Integers.multiplicative
    
        import org.zetatypes.tannakiansymbols.DSL._
        import org.zetatypes.algebra.structures.DSL.{intToRational => _, intToComplex => _, _}
        val X = new TannakianSymbol(Seq((Integer(1), 1 : BigInt), (Integer(2), -1 : BigInt), (Integer(3), 1 : BigInt)))
        val Y = new TannakianSymbol(Seq((Integer(10), 5 : BigInt), (Integer(11), -4 : BigInt), (Integer(2), -1 : BigInt)))
        val Z = new TannakianSymbol(Seq((Integer(2), 2 : BigInt), (Integer(3), -8 : BigInt), (Integer(4), 4 : BigInt)))
        val W = new TannakianSymbol(Seq((Integer(-1), 1 : BigInt), (Integer(10), 0 : BigInt), (Integer(2), 1 : BigInt)))
        
        test ("TS DSL notation integral") {
            assert(X === ms(1, 3) / ms(2))
            assert(Y === ms(10, 10, 10, 10, 10) / ms(11, 11, 11, 11, 2))
            assert(Z === ms(2, 2, 4, 4, 4, 4) / ms(3, 3, 3, 3, 3, 3, 3, 3))
            assert(W === ms(-1, 2) / Ø)
            assert(TS(Integers).one === ms(1) / Ø)
            assert(TS(Integers).zero === Ø / Ø)
        }
    }
    
    {// Using ms(...) / ms(...) notation rational
        implicit val monoidrat : Monoid[Rational] = Rationals.multiplicative
        
        import org.zetatypes.tannakiansymbols.DSL._
        import org.zetatypes.algebra.structures.DSL.{intToInteger => _, intToComplex => _, _}
        val X = new TannakianSymbol(Seq((Rational(Integer(1)), 1 : BigInt), (Rational(Integer(2)), -1 : BigInt), (Rational(Integer(3)), 1 : BigInt)))
        val Y = new TannakianSymbol(Seq((Rational(Integer(10)), 5 : BigInt), (Rational(Integer(11)), -4 : BigInt), (Rational(Integer(2)), -1 : BigInt)))
        val Z = new TannakianSymbol(Seq((Rational(Integer(2)), 2 : BigInt), (Rational(Integer(3)), -8 : BigInt), (Rational(Integer(4)), 4 : BigInt)))
        val W = new TannakianSymbol(Seq((Rational(Integer(-1)), 1 : BigInt), (Rational(Integer(10)), 0 : BigInt), (Rational(Integer(2)), 1 : BigInt)))
        
        test ("TS DSL notation rational") {
            assert(X === ms(1, 3) / ms(2))
            assert(Y === ms(10, 10, 10, 10, 10) / ms(11, 11, 11, 11, 2))
            assert(Z === ms(2, 2, 4, 4, 4, 4) / ms(3, 3, 3, 3, 3, 3, 3, 3))
            assert(W === ms(-1, 2) / Ø)
            assert(TS(Rationals).one === ms(1) / Ø)
            assert(TS(Rationals).zero === Ø / Ø)
        }
    }
    
    {// Addition & Multication test
        implicit val monoid : Monoid[Integer] = Integers.multiplicative
        
        import org.zetatypes.tannakiansymbols.DSL._
        import org.zetatypes.algebra.structures.DSL.{intToRational => _, intToComplex => _, _}
        
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
            assert((ms(1, 2)/ms(3, 3)) * (ms(1, 2)/ms(3, 3)) === ms(1, 2, 2, 4, 9, 9, 9, 9)/ms(3, 3, 3, 3, 6, 6, 6, 6))
            assert((Ø/ms(2, 3)) * (ms(1, 2)/Ø) === Ø/ms(2, 3, 4, 6))
            assert((ms(-1)/ms(1, 2, 3)) * (ms(1, -1)/Ø) === Ø/ms(2, 3, -2, -3))
        }
        
        test ("TS psi^k") {
            assert((ms(1) / Ø psi(10)) === ms(1) / Ø)
            assert((ms(2) / Ø psi(6)) === ms(64) / Ø)
            assert((ms(1, -1) / Ø psi(2)) === ms(1, 1) / Ø)
            assert((ms(1, -1) / Ø psi(2000)) === ms(1, 1) / Ø)
            assert((ms(1, -7) / ms(7, 2, 5) psi(2)) === ms(1) / ms(4, 25))
        }
        
        test ("TS q algebra test") {            
            assert((ms(1)/Ø).partialQMult(Rational(2)) === Some(ms(1, 1)/Ø))
            assert((ms(1)/Ø).partialQMult(Rational(4, 2)) === Some(ms(1, 1)/Ø))
            assert((ms(1)/Ø).partialQMult(Rational(2, 2)) === Some(ms(1)/Ø))
            assert((ms(1)/ms(2)).partialQMult(Rational(3)) === Some(ms(1, 1, 1)/ms(2, 2, 2)))
            
            assert((ms(1)/Ø).partialQMult(Rational(1, 2)) === None)
            assert((ms(1, 1, 1)/Ø).partialQMult(Rational(1, 2)) === None)
            assert((ms(1, 1)/Ø).partialQMult(Rational(1, 3)) === None)
            assert((ms(1, 2)/Ø).partialQMult(Rational(1, 2)) === None)
            
            assert((ms(1)/ms(2)).partialQMult(Rational(1, 2)) === None)
            assert((ms(1)/ms(2, 2)).partialQMult(Rational(1, 2)) === None)
            assert((ms(1, 1)/ms(2, 2)).partialQMult(Rational(1, 4)) === None)
            assert((ms(1, 1, 1)/ms(2, 2, 2)).partialQMult(Rational(1, 6)) === None)
            
            assert((ms(1, 1, 1)/Ø).partialQMult(Rational(1, 3)) === Some(ms(1)/Ø))
            assert((ms(1, 1, 1)/ms(2, 2, 2)).partialQMult(Rational(1, 3)) === Some(ms(1)/ms(2)))
            assert((ms(1, 1, 1, 1, 1, 1)/Ø).partialQMult(Rational(1, 3)) === Some(ms(1, 1)/Ø))
            assert((ms(1, 1, 1, 1)/Ø).partialQMult(Rational(1, 2)) === Some(ms(1, 1)/Ø))
            
            assert((ms(1, 1, 1)/Ø).partialQMult(Rational(2, 3)) === Some(ms(1, 1)/Ø))
            assert((ms(1, 1, 1)/Ø).partialQMult(Rational(4, 3)) === Some(ms(1, 1, 1, 1)/Ø))
            assert((ms(1, 1, 1)/Ø).partialQMult(Rational(12, 9)) === Some(ms(1, 1, 1, 1)/Ø))
            assert((ms(1, 1, 1, 1, 1, 1)/Ø).partialQMult(Rational(2, 3)) === Some(ms(1, 1, 1, 1)/Ø))
        }
        
        test ("TS lambda operation") {
            assert((ms(2)/ms(1)).lambda(0) === ms(1)/Ø)
            assert((ms(2)/ms(1)).lambda(1) === ms(2)/ms(1))
            assert((ms(2, 3)/Ø).lambda(0) === ms(1)/Ø)
            assert((ms(2, 3)/Ø).lambda(1) === ms(2, 3)/Ø)
            
            assert((ms(3)/Ø).lambda(2) === Ø/Ø)
            assert((ms(2, 3)/Ø).lambda(3) === Ø/Ø)
            assert((ms(2, 3, 4)/Ø).lambda(4) === Ø/Ø)
            assert((ms(2, 3, 4, 5)/Ø).lambda(5) === Ø/Ø)
            
            assert((ms(2, 3)/Ø).lambda(2) === ms(6)/Ø)
            assert((ms(-1, 3, 5)/Ø).lambda(2) === ms(-3, -5, 15)/Ø)
            assert((ms(2, 2, 3, 3)/Ø).lambda(2) === ms(4, 6, 6, 6, 6, 9)/Ø)
            assert((ms(-1, -1, -1)/Ø).lambda(2) === ms(1, 1, 1)/Ø)
            
            assert((ms(-1, 3, 5)/Ø).lambda(3) === ms(-15)/Ø)
            assert((ms(1, 2, 3)/Ø).lambda(3) === ms(6)/Ø)
            assert((ms(2, 2, 3, 3)/Ø).lambda(3) === ms(12, 12, 18, 18)/Ø)
            assert((ms(-1, -1, -1, -1)/Ø).lambda(3) === ms(-1, -1, -1, -1)/Ø)
            
            assert((ms(1, 2, 3, 4)/Ø).lambda(4) === ms(24)/Ø)
            assert((ms(-1, 2, -2, 2)/Ø).lambda(4) === ms(8)/Ø)
            assert((ms(1, 2, 3, 4, 5)/Ø).lambda(4) === ms(24, 30, 40, 60, 120)/Ø)
            assert((ms(1, 2, 3, 4, -2)/Ø).lambda(4) === ms(24, -12, -16, -24, -48)/Ø)
            
            assert((ms(1)/ms(2)).lambda(2) === ms(4)/ms(2))
            assert((ms(1)/ms(2)).lambda(3) === ms(4)/ms(8))
            assert((ms(1)/ms(2)).lambda(4) === ms(16)/ms(8))
            assert((ms(1)/ms(2)).lambda(5) === ms(16)/ms(32))
            
            assert((ms(1, 1)/ms(-1)).lambda(2) === ms(1, 1)/ms(-1, -1))
            assert((ms(1, 1)/ms(-1)).lambda(3) === ms(1, 1)/ms(-1, -1))
            assert((ms(1, 1)/ms(-1)).lambda(4) === ms(1, 1)/ms(-1, -1))
            assert((ms(1, 1)/ms(-1)).lambda(5) === ms(1, 1)/ms(-1, -1))
            
            assert((ms(1, 2, 3)/ms(4, 5)).lambda(2) === ms(2, 3, 6, 16, 20, 25)/ms(4, 5, 8, 10, 12, 15))
            assert((ms(1, 2, 3)/ms(4, 5)).lambda(3) === ms(32, 6, 75, 48, 40, 50, 20, 25, 60, 16)/ms(64, 100, 8, 80, 10, 12, 15, 24, 125, 30))
            assert((ms(1, 2, 3)/ms(4, 5)).lambda(4) === ms(256, 320, 150, 32, 40, 48, 50, 60, 75, 96, 400, 625, 500, 120)/ms(128, 64, 24, 30, 300, 192, 160, 200, 80, 100, 240, 375, 250, 125))
            assert((ms(1, 2, 3)/ms(4, 5)).lambda(5) === ms(256, 320, 150, 640, 768, 1200, 960, 800, 96, 1875, 1500, 400, 1250, 1000, 512, 625, 500, 120)/ms(128, 192, 384, 160, 1024, 300, 1280, 3125, 1600, 2500, 200, 2000, 600, 480, 750, 240, 375, 250))
            
            assert((Ø/ms(1, 2)).lambda(2) === ms(1, 2, 4)/Ø)
            assert((Ø/ms(1, 2)).lambda(3) === Ø/ms(8, 1, 2, 4))
            assert((Ø/ms(1, 2)).lambda(4) === ms(8, 1, 2, 4, 16)/Ø)
            assert((Ø/ms(1, 2)).lambda(5) === Ø/ms(32, 1, 2, 4, 8, 16))
            
            assertThrows[AlgebraicException] {
                (ms(1)/Ø).lambda(-1)
            }
        }
        
        test ("TS superdimension") {
            assert((ms(1, 2)/Ø).superdimension === (2, 0))
            assert((ms(1)/ms(2)).superdimension === (1, 1))
            assert((ms(1)/ms(1)).superdimension === (0, 0))
            assert((Ø/ms(1, 2, 4, 4)).superdimension === (0, 4))
        }
        
        test ("TS even & odd dimension") {
            assert((ms(1, 2)/Ø).evendimension === 2)
            assert((ms(1)/ms(2)).evendimension === 1)
            assert((ms(1)/ms(1)).evendimension === 0)
            assert((Ø/ms(1, 2, 4, 4)).evendimension === 0)
            
            assert((ms(1, 2)/Ø).odddimension === 0)
            assert((ms(1)/ms(2)).odddimension === 1)
            assert((ms(1)/ms(1)).odddimension === 0)
            assert((Ø/ms(1, 2, 4, 4)).odddimension === 4)
        }
        
        test ("TS islineelement") {
            assert((ms(1)/Ø).islineelement)
            assert((ms(3)/Ø).islineelement)
            assert((ms(1, 2)/ms(2)).islineelement)
            assert((ms(1, 2, 2)/ms(1, 2)).islineelement)
            
            assert(!(Ø/Ø).islineelement)
            assert(!(ms(2, 2)/Ø).islineelement)
            assert(!(Ø/ms(1)).islineelement)
            assert(!(ms(1)/ms(1)).islineelement)
        }
        
        test ("TS augmentation") {
            assert((ms(1, 2)/Ø).augmentation === 2)
            assert((ms(1)/ms(2)).augmentation === 0)
            assert((ms(1)/ms(1)).augmentation === 0)
            assert((Ø/ms(1, 2, 4, 4)).augmentation === -4)
        }
    }
}
