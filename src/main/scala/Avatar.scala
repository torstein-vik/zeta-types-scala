package io.github.torsteinvik.zetatypes

import io.github.torsteinvik.zetatypes.numbertheory._

sealed trait Avatar {
    def localavatar (p : Prime) : LocalAvatar
}

object Avatar {
}
