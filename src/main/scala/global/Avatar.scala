package io.github.torsteinvik.zetatypes.global

import io.github.torsteinvik.zetatypes.numbertheory._
import io.github.torsteinvik.zetatypes.local.{Avatar => LocalAvatar}

/** An avatar for the global data of a zeta-type */
sealed trait Avatar {
    /** The local avatar at any given prime */
    def localavatar (p : Prime) : LocalAvatar
}

/** Contains the only legal (global) zeta-type avatars */
object Avatar {
    
    /** [[Avatar!]] for representation using Tannakian symbols */
    case object TS extends Avatar {
        def localavatar(p : Prime) = LocalAvatar.TS
    }
    
    /** [[Avatar!]] for representation using rational expressions for the local zeta-functions */
    case object RationalLZF extends Avatar {
        def localavatar(p : Prime) = LocalAvatar.RationalLZF
    }
    
    /** [[Avatar!]] for representation using Bell coefficients */
    case object BellCoeffs extends Avatar {
        def localavatar(p : Prime) = LocalAvatar.BellCoeffs
    }
    
    /** [[Avatar!]] for representation using point counts */
    case object PointCounts extends Avatar {
        def localavatar(p : Prime) = LocalAvatar.PointCounts
    }

}
