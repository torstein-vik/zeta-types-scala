package org.torsteinv.zetatypes

import algebra._

trait algebraPackage 
    extends MagmaPackaging
    with MonoidPackaging
    with GroupPackaging
    with RingPackaging
    with QAlgebraPackaging
    with LambdaRingPackaging
    
package object algebra extends algebraPackage
