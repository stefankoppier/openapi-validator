package io.github.stefankoppier.openapi.validator.core.rules

import io.github.stefankoppier.openapi.validator.core.ValidationFailure
import io.github.stefankoppier.openapi.validator.core.ValidationResult

abstract class ValidationRule<T : Any>(val group: RuleGroup) {

    private var preconditions = { _: T? -> true }

    private var rules = unit()

    fun validate(fixture: T?) = if (preconditions(fixture)) rules(fixture) else unit()(fixture)

    fun <R : ValidationRule<T>> given(precondition: (T?) -> Boolean, rule: () -> R): R {
        val copy = preconditions
        preconditions = { fixture -> precondition(fixture) && copy(fixture) }
        return rule()
    }

    fun <R : ValidationRule<T>> R.holds(message: (T?) -> String = { "Was supposed to hold for '$it' but did not" }, predicate: (T?) -> Boolean) =
        apply {
            add {
                ValidationResult.condition(ValidationFailure(group, message(it))) { predicate(it) }
            }
        }

    // TODO: cool to have, if we have a ValidationRule which is nullable, after required, we can return a non nullable ValidationRule
    fun <R : ValidationRule<T>> R.required() =
        holds( { "Was required but is not given" } ) { it != null }

    fun <R : ValidationRule<T>> R.exactly(value: T) =
        holds( { "Was supposed to be '$value' but is '$it'" } ) { it == value }

    protected fun add(rule: (T?) -> ValidationResult): ValidationRule<T> {
        val copy = rules
        rules = { fixture -> copy(fixture) merge rule(fixture) }
        return this
    }

    private fun unit(): (T?) -> ValidationResult = { ValidationResult.success() }
}
