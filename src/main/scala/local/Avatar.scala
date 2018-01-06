package io.github.torsteinvik.zetatypes.local

/** An avatar for the local data of a zeta-type */
sealed trait Avatar

/** Contains the only legal zeta-type local avatars */
object Avatar {
    
    /** Trait used to restrict possible choices in LocalZetaType type params */
    sealed trait Concrete
    
    /** Type synonym for [[TS#]] with [[Concrete]] */
    type TS = TS.type with Concrete
    /** Type synonym for [[RationalLZF#]] with [[Concrete]] */
    type RationalLZF = RationalLZF.type with Concrete
    /** Type synonym for [[BellCoeffs#]] with [[Concrete]] */
    type BellCoeffs = BellCoeffs.type with Concrete
    /** Type synonym for [[PointCounts#]] with [[Concrete]] */
    type PointCounts = PointCounts.type with Concrete
    
    /** [[Avatar!]] for representation using a Tannakian symbol */
    case object TS extends Avatar
    
    /** [[Avatar!]] for representation using a rational expression for the local zeta-function */
    case object RationalLZF extends Avatar
    
    /** [[Avatar!]] for representation using Bell coefficients */
    case object BellCoeffs extends Avatar
    
    /** [[Avatar!]] for representation using point counts */
    case object PointCounts extends Avatar

}
