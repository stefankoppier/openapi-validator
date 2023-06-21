package io.github.stefankoppier.openapi.validator.core.internal

internal data class RoseTree<T>(val data: T, val children: MutableList<RoseTree<T>> = mutableListOf()) {

    fun merge(other: RoseTree<T>): RoseTree<T> {
        require(data == other.data) { "Expecting node '$data' to be equal to node '${other.data}'" }

        for (child in other.children) {
            val childd = children.firstOrNull { child.data == it.data }?.merge(child)
            if (childd != null) {
                childd.merge(child)
            } else {
                children.add(child)
            }
        }
        return this
    }

    fun <R> map(action: (T) -> R): RoseTree<R> =
        mapNodes { action(it.data) }

    fun <R> mapNodes(action: (RoseTree<T>) -> R): RoseTree<R> {
        return RoseTree(action(this), children.map { it.mapNodes(action) }.toMutableList())
    }

    fun <R> foldWithLevel(acc: R, operation: (level: Int, acc: R, T) -> R) = foldWithLevel(0, acc, operation)

    private fun <R> foldWithLevel(level: Int, acc: R, operation: (level: Int, acc: R, T) -> R): R {
        return if (children.isEmpty()) {
            operation(level, acc, data)
        } else {
            children.fold(operation(level, acc, data)) { acc, it -> it.foldWithLevel(level + 1, acc, operation) }
        }
    }
}
