package io.github.torsteinvik.zetatypes

/** An avatar for the local data of a zeta-type */
sealed trait LocalAvatar

/** Contains the only legal zeta-type local avatars */
object LocalAvatar {
    
    case object TS  extends LocalAvatar
    
    case object RationalLZF extends LocalAvatar
    
    case object BellCoeffs extends LocalAvatar
    
    case object PointCounts extends LocalAvatar

}
