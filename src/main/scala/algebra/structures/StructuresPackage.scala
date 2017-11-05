package org.torsteinv.zetatypes.algebra

import structures._

trait structuresPackage
    extends IntegerPackaging
    with RationalPackaging
    with ComplexPackaging
    with DSLPackagingCommon

package object structures extends structuresPackage
