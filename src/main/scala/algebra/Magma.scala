package org.torsteinv.zetatypes.algebra

import scala.annotation.tailrec

package object Magmatic extends MagmaticPackage

trait MagmaticPackage {

    trait AlgebraicElement

    class AlgebraicException(msg : String) extends Exception(msg)

    trait Magma[T <: AlgebraicElement] {
        def combine (x : T, y : T) : T
        def repeated (x : T, n : Int) : T = n match {
            case n if n > 0 => expTailRec(n, x, x)
            case _ => throw new AlgebraicException("Magmatic repeated addition requires n > 0")
        }
        
        @tailrec
        private def expTailRec(n : Int, x : T, acc : T) : T = {
            if (n <= 1) return acc
            return expTailRec(n - 1, x, combine(x, acc))
        }  
    }

    trait Additive[that <: Additive[that]] extends AlgebraicElement{
        def +(y : that) : that
        def ++(n : Int)(implicit ev: this.type <:< that) : that = n match {
            case n if n > 0 => expTailRec(n, ev(this))
            case _ => throw new AlgebraicException("Magmatic repeated addition requires n > 0")
        }
        
        @tailrec
        private def expTailRec(n : Int, acc : that) : that = {
            if (n <= 1) return acc
            return expTailRec(n - 1, this + acc)
        } 
    }

    trait Multiplicative[that <: Multiplicative[that]] extends AlgebraicElement {
        def *(y : that) : that
        def **(n : Int)(implicit ev: this.type <:< that) : that = n match {
            case n if n > 0 => expTailRec(n, ev(this))
            case _ => throw new AlgebraicException("Magmatic repeated multiplication requires n > 0")
        }
        
        @tailrec
        private def expTailRec(n : Int, acc : that) : that = {
            if (n <= 1) return acc
            return expTailRec(n - 1, this * acc)
        } 
    }

}