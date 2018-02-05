package io.github.torsteinvik.zetatypes.algebra.structures

import scala.language.implicitConversions

/** Provides various methods for simplifying the syntax of working with algebra.
 */
package object DSL {
    
    /** implicitly converts an int in standard syntax to an [[Integer]] */
    implicit def intToInteger  (x : Int) : Integer  = Integer(x)
}

package DSL {
    
    /** Dummy class so that scaladoc shows DSL */
    protected class DummyClass
}
