package org.torsteinv.zetatypes

import sequences._

trait sequencesPackage
    extends SequencesPackaging
    with SequenceFactoriesPackaging
    with AlgebraicSequencePackaging
    with SpecialAlgebraicSequencesPackaging
    with DSLPackagingCommon

package object sequences extends sequencesPackage
