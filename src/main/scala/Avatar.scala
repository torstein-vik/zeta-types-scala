package io.github.torsteinvik.zetatypes

import io.github.torsteinvik.zetatypes.numbertheory._

sealed trait Avatar {
    def localavatar (p : Prime) : LocalAvatar
}

object Avatar {
    case object TS extends Avatar {
        def localavatar(p : Prime) = LocalAvatar.TS
    }
    
    case object RationalLZF extends Avatar {
        def localavatar(p : Prime) = LocalAvatar.RationalLZF
    }
    
    case object BellCoeffs extends Avatar {
        def localavatar(p : Prime) = LocalAvatar.BellCoeffs
    }
    
    case object PointCounts extends Avatar {
        def localavatar(p : Prime) = LocalAvatar.PointCounts
    }

}
