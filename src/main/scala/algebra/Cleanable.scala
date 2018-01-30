package io.github.torsteinvik.zetatypes.algebra

trait Cleanable {
    def cleanup : Clean
}

trait Clean extends Cleanable {
    override final def cleanup : this.type with Clean = this
}
