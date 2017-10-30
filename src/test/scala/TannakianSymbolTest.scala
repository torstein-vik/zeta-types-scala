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
    val X = new TannakianSymbol(Seq((1, 1), (1, -1)))
    val Y = new TannakianSymbol(Seq((3, 2), (3, -1), (2, 1)))
    val Z = new TannakianSymbol(Seq((1, 1), (2, -1), (3, 1), (3, -2)))
    
    test ("TannakianSymbol identity") {
        
    }
}