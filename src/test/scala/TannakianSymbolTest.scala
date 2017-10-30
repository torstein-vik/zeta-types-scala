import org.scalatest.FunSuite

class TannakianSymbolTest extends FunSuite{
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
        
    }
    
}