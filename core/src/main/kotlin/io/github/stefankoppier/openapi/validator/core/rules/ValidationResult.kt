package io.github.stefankoppier.openapi.validator.core.rules

import io.github.stefankoppier.openapi.validator.core.internal.RoseTree


class ValidationResult internal constructor(val failures: MutableList<ValidationFailure>) {

    val isSuccess: Boolean
        get() = failures.isEmpty()

    val isFailure: Boolean
        get() = failures.isNotEmpty()

    infix fun merge(other: ValidationResult): ValidationResult {
        return apply { failures.addAll(other.failures) }
    }

    fun summarize(): String {
        return failures
            .map { it.construct() }
            .reduce(RoseTree<ValidationNode>::merge)
            .foldWithLevel(StringBuilder()) { level, builder , it ->
                builder.apply {
                    append((0 until level).joinToString(separator = "") { "    " })
                    append(when (it.category) {
                        RuleGroupCategory.OBJECT -> "For object ${it.message}:"
                        RuleGroupCategory.FIELD -> "Field ${it.message} does not comply:"
                        RuleGroupCategory.MESSAGE -> "- ${it.message}"
                        RuleGroupCategory.UNKNOWN -> it.message
                    })
                    appendLine()
                }
            }.toString()
    }

    companion object {
        fun success(): ValidationResult {
            return ValidationResult(mutableListOf())
        }

        fun failure(failure: ValidationFailure): ValidationResult {
            return ValidationResult(mutableListOf(failure))
        }

        fun condition(failure: ValidationFailure, condition: () -> Boolean) =
            if (condition()) success() else failure(failure)
    }
}

data class ValidationFailure(val group: RuleGroup, val message: String) {

    internal fun construct(): RoseTree<ValidationNode> {
        return construct(group, emptyList())
    }

    // TODO: can use some refactoring
    private fun construct(current: RuleGroup, parents: List<RuleGroup>): RoseTree<ValidationNode> {
        return if (current.parent == null) {
            val root = RoseTree(ValidationNode(current.name, current.category))
            parents.foldRight(root) { parent, it ->
                val child = RoseTree(ValidationNode(parent.name, parent.category))
                it.apply { children.add(child) }
                child
            }.apply { children.add(RoseTree(ValidationNode(message, RuleGroupCategory.MESSAGE))) }
            root
        } else {
            construct(current.parent, parents.toMutableList().apply { add(current) })
        }
    }
}

data class ValidationNode(
    val message: String,
    val category: RuleGroupCategory,
)