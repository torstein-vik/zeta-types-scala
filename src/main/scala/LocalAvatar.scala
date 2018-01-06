package io.github.torsteinvik.zetatypes

/** An avatar for the local data of a zeta-type */
sealed trait LocalAvatar

/** Contains the only legal zeta-type local avatars */
object LocalAvatar {
    
    sealed trait Concrete
    
    type TS = TS.type with Concrete
    type RationalLZF = RationalLZF.type with Concrete
    type BellCoeffs = BellCoeffs.type with Concrete
    type PointCounts = PointCounts.type with Concrete
    
    /** [[LocalAvatar!]] for representation using a Tannakian symbol */
    case object TS extends LocalAvatar
    
    /** [[LocalAvatar!]] for representation using a rational expression for the local zeta-function */
    case object RationalLZF extends LocalAvatar
    
    /** [[LocalAvatar!]] for representation using Bell coefficients */
    case object BellCoeffs extends LocalAvatar
    
    /** [[LocalAvatar!]] for representation using point counts */
    case object PointCounts extends LocalAvatar

}
