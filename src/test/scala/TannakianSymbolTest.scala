import org.scalatest.FunSuite

class TannakianSymbolTest extends FunSuite{
    import io.zetatypes.tannakiansymbols.DSL._
        
    test ("Defining TannakianSymbol using multisets"){
        assertCompiles("ms(1, 1, 2) / Ø")
        assertCompiles("Ø / Ø")
        assertCompiles("ms(1, 2, 3) / ms(1, 2, 3, -4)")
    }
}