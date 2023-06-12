package io.github.stefankoppier.openapi.validator.core.rules.primitives

import io.github.stefankoppier.openapi.validator.core.rules.RuleGroup
import io.github.stefankoppier.openapi.validator.core.rules.ValidationRule
import io.github.stefankoppier.openapi.validator.core.rules.openapi.ResponseRule

abstract class IterableValidationRule<T : Any> internal constructor(group: RuleGroup = RuleGroup.unknown()) : ValidationRule<Iterable<T>>(group) {

    /**
     * Validate that all elements satisfy [predicate].
     *
     * @param description The optional description to write to the output if the rule is invalid.
     * @param predicate The predicate which should be validated.
     *
     * @return The original rule on which this method has been invoked.
     */
    fun <R : IterableValidationRule<T>> R.all(description: String = "", predicate: (T) -> Boolean): R =
        holds( { "All were supposed to match '$description' but not all did" } ) {
            it?.all(predicate) ?: true
        }

    /**
     * Validate that at least one element satisfies [predicate].
     *
     * @param description The optional description to write to the output if the rule is invalid.
     * @param predicate The predicate which should be validated.
     *
     * @return The original rule on which this method has been invoked.
     */
    fun <R : IterableValidationRule<T>> R.any(description: String = "", predicate: (T) -> Boolean): R =
        holds( { "At least one was supposed to match '$description' but none did" } ) {
            it?.any(predicate) ?: false
        }

    /**
     * Validate that no element satisfies [predicate].
     *
     * @param description The optional description to write to the output if the rule is invalid.
     * @param predicate The predicate which should be validated.
     *
     * @return The original rule on which this method has been invoked.
     */
    fun <R : IterableValidationRule<T>> R.none(description: String = "", predicate: (T) -> Boolean): R =
        holds( { "None were supposed to match '$description' but at least one did" } ) {
            it?.none(predicate) ?: false
        }
}
