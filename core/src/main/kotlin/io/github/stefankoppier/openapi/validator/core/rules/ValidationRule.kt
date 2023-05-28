package io.github.stefankoppier.openapi.validator.core.rules

abstract class ValidationRule<T>(val group: RuleGroup) {

    private var predicate: (T) -> ValidationResult = { ValidationResult.success() }

    fun validate(fixture: T) = predicate(fixture)

    protected fun add(predicate: (T) -> ValidationResult): ValidationRule<T> {
        val copy = this.predicate
        this.predicate = { fixture -> copy(fixture) merge predicate(fixture) }
        return this
    }
}