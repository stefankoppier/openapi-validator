package io.github.stefankoppier.openapi.validator.rules.primitives

import io.github.stefankoppier.openapi.validator.rules.ValidationRule
import io.github.stefankoppier.openapi.validator.rules.RuleGroup
import io.github.stefankoppier.openapi.validator.rules.ValidationFailure
import io.github.stefankoppier.openapi.validator.rules.ValidationResult

class OptionalStringRule(group: RuleGroup = RuleGroup.unknown()) : ValidationRule<String?>(group) {

    fun exactly(value: String): OptionalStringRule {
        add {
            val message = "Was supposed to be '$value' but is '$it'"
            ValidationResult.condition(ValidationFailure(group, message)) {
                it == value
            }
        }
        return this
    }

    fun required(): OptionalStringRule {
        add {
            val message = "Was required but is '$it'"
            ValidationResult.condition(ValidationFailure(group, message)) {
                it != null
            }
        }
        return this
    }

    fun lowercase(): OptionalStringRule {
        add {
            val message = "Was supposed to be lowercase but is '$it'"
            ValidationResult.condition(ValidationFailure(group, message)) {
                it == null || it.all { c -> c.isLowerCase() }
            }
        }
        return this
    }

    fun uppercase(): OptionalStringRule {
        add {
            val message = "Was supposed to be uppercase but is '$it'"
            ValidationResult.condition(ValidationFailure(group, message)) {
                it == null || it.all { c -> c.isUpperCase() }
            }
        }
        return this
    }
}