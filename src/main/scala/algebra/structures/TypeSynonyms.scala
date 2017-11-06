package org.torsteinv.zetatypes.algebra

package object structures {
    type Rational = Fraction[Integer]
    type Complex  = ComplexNumber[Rational]
}