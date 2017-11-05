package org.torsteinv.zetatypes

import algebra._

trait algebraic 
    extends MagmaticPackage
    with MonoidalPackage
    with GrouplikePackage
    with RinglikePackage
    with QAlgebraicPackage
    with LambdaRinglikePackage
    
package object algebra extends algebraic
