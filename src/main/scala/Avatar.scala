package io.github.torsteinvik.zetatypes

import io.github.torsteinvik.zetatypes.numbertheory._

sealed trait Avatar {
    def localavatar (p : Prime) : LocalAvatar
}

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
