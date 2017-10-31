package io.zetatypes.tannakiansymbols

class Multiset[T](val elements : T*) {
    
    override def toString : String = {
        if (elements.length == 0) {
            return "Ø"
        } else {
            return elements.mkString("{", ", ", "}")
        }
    }
    
}