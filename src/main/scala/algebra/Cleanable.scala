package io.github.torsteinvik.zetatypes.algebra

/** Something with a cleanup method to (partially) normalize an element */
trait Cleanable extends Element {
    /** Returns a normalized/cleaned up version of this */
    def cleanup : Clean
}

/** Something which is invariant of cleanup, which is used to streamline computation by skipping normaliziation */
trait Clean extends Cleanable {
    override final def cleanup : this.type with Clean = this
}
