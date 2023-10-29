package io.github.stefankoppier.openapi.validator.core

import io.github.stefankoppier.openapi.validator.core.internal.RoseTree
import io.github.stefankoppier.openapi.validator.core.rules.RuleGroup

class ValidationResult internal constructor(val failures: MutableList<ValidationFailure>) {

    val isSuccess: Boolean
        get() = failures.isEmpty()

    val isFailure: Boolean
        get() = !isSuccess

    internal constructor(exception: Throwable) :
        this(
            mutableListOf(
                ValidationFailure(RuleGroup.named("", RuleGroup.Category.EXCEPTION), exception.message ?: exception.toString()),
            ),
        )

    infix fun merge(other: ValidationResult): ValidationResult {
        return apply { failures.addAll(other.failures) }
    }

    fun summarize(): String {
        return failures
            .map { it.construct() }
            .reduce(RoseTree<ValidationNode>::merge)
            .fold(StringBuilder()) { level, builder, it ->
                builder.apply {
                    append((0 until level).joinToString(separator = "") { "    " })
                    append(
                        when (it.category) {
                            RuleGroup.Category.GROUP -> "For ${it.content}:"
                            RuleGroup.Category.FIELD -> "Field '${it.content}' does not comply:"
                            RuleGroup.Category.MESSAGE -> "- ${it.content}"
                            RuleGroup.Category.EXCEPTION -> "An exception occurred: ${it.content}"
                            RuleGroup.Category.UNKNOWN -> it.content
                        },
                    )
                    appendLine()
                }
            }.toString()
    }

    fun negate(group: RuleGroup, message: String): ValidationResult {
        return if (isSuccess) failure(ValidationFailure(group, message)) else success()
    }

    companion object {
        fun success(): ValidationResult {
            return ValidationResult(mutableListOf())
        }

        fun failure(vararg failure: ValidationFailure): ValidationResult {
            require(failure.isNotEmpty()) { "At least one failure must be given" }

            return ValidationResult(failure.toMutableList())
        }

        fun failure(failure: ValidationFailure): ValidationResult {
            return ValidationResult(mutableListOf(failure))
        }

        fun condition(failure: ValidationFailure, condition: () -> Boolean) =
            if (condition()) success() else failure(failure)
    }
}

data class ValidationFailure(val group: RuleGroup, val message: String) {

    internal fun construct() = construct(group, emptyList())

    // TODO: can use some refactoring
    private fun construct(current: RuleGroup, parents: List<RuleGroup>): RoseTree<ValidationNode> {
        return if (current.parent == null) {
            val rootContent = current.name + if (current.description.isBlank()) "" else " (${current.description})"
            val root = RoseTree(ValidationNode(rootContent, current.category))
            parents.foldRight(root) { parent, it ->
                val content = parent.name + if (parent.description.isBlank()) "" else " (${parent.description})"
                val child = RoseTree(ValidationNode(content, parent.category))
                it.apply { children.add(child) }
                child
            }.apply {
                val content = message + if (group.description.isBlank()) "" else " (${group.description})"
                children.add(RoseTree(ValidationNode(content, RuleGroup.Category.MESSAGE)))
            }
            root
        } else {
            construct(current.parent, parents.toMutableList().apply { add(current) })
        }
    }
}

data class ValidationNode(
    val content: String,
    val category: RuleGroup.Category,
)
