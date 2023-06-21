package io.github.stefankoppier.openapi.validator.core.internal

internal data class RoseTree<T>(val data: T, val children: MutableList<RoseTree<T>> = mutableListOf()) {

    fun merge(other: RoseTree<T>): RoseTree<T> {
        require(data == other.data) { "Expecting node '$data' to be equal to node '${other.data}'" }

        for (child in other.children) {
            val merged = children.firstOrNull { child.data == it.data }?.merge(child)
            if (merged == null) {
                children.add(child)
            }
        }
        return this
    }

    fun <R> fold(acc: R, operation: (Int, R, T) -> R) = fold(0, acc, operation)

    private fun <R> fold(level: Int, acc: R, operation: (Int, R, T) -> R): R =
        if (children.isEmpty()) {
            operation(level, acc, data)
        } else {
            children.fold(operation(level, acc, data)) { acc, it ->
                it.fold(level + 1, acc, operation)
            }
        }
}
