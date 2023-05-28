package io.github.stefankoppier.openapi.validator.core.rules

abstract class ValidationRule<T>(val group: RuleGroup) {

    private var preconditions = { _: T -> true }

    private var rules = unit()

    fun validate(fixture: T) = if (preconditions(fixture)) rules(fixture) else unit()(fixture)

    fun <R : ValidationRule<T>> given(precondition: (T) -> Boolean, rule: () -> R): R {
        val copy = preconditions
        preconditions = { fixture -> precondition(fixture) && copy(fixture) }
        return rule()
    }

    protected fun add(rule: (T) -> ValidationResult): ValidationRule<T> {
        val copy = rules
        rules = { fixture -> copy(fixture) merge rule(fixture) }
        return this
    }

    private fun unit(): (T) -> ValidationResult = { ValidationResult.success() }
}
