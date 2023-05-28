package io.github.stefankoppier.openapi.validator.core.rules.primitives

import io.github.stefankoppier.openapi.validator.core.rules.RuleGroup
import io.github.stefankoppier.openapi.validator.core.rules.ValidationFailure
import io.github.stefankoppier.openapi.validator.core.rules.ValidationResult
import io.github.stefankoppier.openapi.validator.core.rules.ValidationRule

class BooleanRule(group: RuleGroup) : ValidationRule<Boolean?>(group) {

    fun required(): BooleanRule {
        add {
            val message = "Was required but is '$it'"
            ValidationResult.condition(ValidationFailure(group, message)) {
                it != null
            }
        }
        return this
    }

    fun exactly(value: Boolean): BooleanRule {
        add {
            val message = "Was supposed to be '$value' but was '$it'"
            ValidationResult.condition(ValidationFailure(group, message)) {
                it == null || it == value
            }
        }
        return this
    }
}
