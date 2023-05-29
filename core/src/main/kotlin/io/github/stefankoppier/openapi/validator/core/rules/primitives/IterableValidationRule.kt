package io.github.stefankoppier.openapi.validator.core.rules.primitives

import io.github.stefankoppier.openapi.validator.core.rules.RuleGroup
import io.github.stefankoppier.openapi.validator.core.rules.ValidationFailure
import io.github.stefankoppier.openapi.validator.core.rules.ValidationResult
import io.github.stefankoppier.openapi.validator.core.rules.ValidationRule

// TODO: add variants where we can pass rules instead of predicates
abstract class IterableValidationRule<T>(group: RuleGroup) : ValidationRule<Iterable<T>>(group) {

    fun <R : IterableValidationRule<T>> R.all(description: String = "", predicate: (T) -> Boolean): R {
        add {
            val message = "All were supposed to match '$description' but not all did"
            ValidationResult.condition(ValidationFailure(group, message)) {
                it?.all(predicate) ?: true
            }
        }
        return this
    }

    fun <R : IterableValidationRule<T>> R.any(description: String = "", predicate: (T) -> Boolean): R {
        add {
            val message = "At least one was supposed to match '$description' but none did"
            ValidationResult.condition(ValidationFailure(group, message)) {
                it?.any(predicate) ?: false
            }
        }
        return this
    }

    fun <R : IterableValidationRule<T>> R.none(description: String = "", predicate: (T) -> Boolean): R {
        add {
            val message = "None were supposed to match '$description' but at least one did"
            ValidationResult.condition(ValidationFailure(group, message)) {
                it?.none(predicate) ?: true
            }
        }
        return this
    }
}
