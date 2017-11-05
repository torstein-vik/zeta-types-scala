package org.torsteinv.zetatypes.algebra

import structures._

trait structural
    extends IntegralPackage
    with RationallikePackage
    with ComplexlikePackage
    with DSLPackageCommon

package object structures extends structural
