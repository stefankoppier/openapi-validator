package io.github.stefankoppier.openapi.validator.core.rules

abstract class ValidationRule<T : Any>(val group: RuleGroup) {

    private var preconditions = { _: T? -> true }

    private var rules = unit()

    fun validate(fixture: T?) = if (preconditions(fixture)) rules(fixture) else unit()(fixture)

    fun <R : ValidationRule<T>> given(precondition: (T?) -> Boolean, rule: () -> R): R {
        val copy = preconditions
        preconditions = { fixture -> precondition(fixture) && copy(fixture) }
        return rule()
    }

    fun <R : ValidationRule<T>> R.holds(predicate: (T?) -> Boolean): R {
        add {
            val message = "Was supposed to hold for '$it' but did not"
            ValidationResult.condition(ValidationFailure(group, message)) {
                predicate(it)
            }
        }
        return this
    }

    // TODO: cool to have, if we have a ValidationRule which is nullable, after required, we can return
    // a non nullable ValidationRule
    fun <R : ValidationRule<T>> R.required(): R {
        add {
            val message = "Was required but is not given"
            ValidationResult.condition(ValidationFailure(group, message)) {
                it != null
            }
        }
        return this
    }

    fun <R : ValidationRule<T>> R.exactly(value: T?): R {
        add {
            val message = "Was supposed to be '$value' but is '$it'"
            ValidationResult.condition(ValidationFailure(group, message)) {
                it == value
            }
        }
        return this
    }

    protected fun add(rule: (T?) -> ValidationResult): ValidationRule<T> {
        val copy = rules
        rules = { fixture -> copy(fixture) merge rule(fixture) }
        return this
    }

    private fun unit(): (T?) -> ValidationResult = { ValidationResult.success() }
}
