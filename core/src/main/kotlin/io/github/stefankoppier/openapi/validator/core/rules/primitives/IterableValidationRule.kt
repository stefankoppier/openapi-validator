package io.github.stefankoppier.openapi.validator.core.rules.primitives

import io.github.stefankoppier.openapi.validator.core.rules.RuleGroup
import io.github.stefankoppier.openapi.validator.core.rules.ValidationRule

// TODO: add variants where we can pass rules instead of predicates
abstract class IterableValidationRule<T>(group: RuleGroup) : ValidationRule<Iterable<T>>(group) {

    fun <R : IterableValidationRule<T>> R.all(description: String = "", predicate: (T) -> Boolean): R =
        holds( { "All were supposed to match '$description' but not all did" } ) {
            it?.all(predicate) ?: true
        }

    fun <R : IterableValidationRule<T>> R.any(description: String = "", predicate: (T) -> Boolean): R =
        holds( { "At least one was supposed to match '$description' but none did" } ) {
            it?.any(predicate) ?: false
        }

    fun <R : IterableValidationRule<T>> R.none(description: String = "", predicate: (T) -> Boolean): R =
        holds( { "None were supposed to match '$description' but at least one did" } ) {
            it?.none(predicate) ?: false
        }
}
