package io.github.stefankoppier.openapi.validator.core.rules.primitives

import io.github.stefankoppier.openapi.validator.core.rules.RuleGroup
import io.github.stefankoppier.openapi.validator.core.rules.ValidationFailure
import io.github.stefankoppier.openapi.validator.core.rules.ValidationResult
import io.github.stefankoppier.openapi.validator.core.rules.ValidationRule

class StringRule(group: RuleGroup = RuleGroup.unknown()) : ValidationRule<String?>(group) {

    fun required(): StringRule {
        add {
            val message = "Was required but is '$it'"
            ValidationResult.condition(ValidationFailure(group, message)) {
                it != null
            }
        }
        return this
    }

    fun exactly(value: String): StringRule {
        add {
            val message = "Was supposed to be '$value' but is '$it'"
            ValidationResult.condition(ValidationFailure(group, message)) {
                it == value
            }
        }
        return this
    }

    fun lowercase(): StringRule {
        add {
            val message = "Was supposed to be lowercase but is '$it'"
            ValidationResult.condition(ValidationFailure(group, message)) {
                it == null || it.all { c -> c.isLowerCase() }
            }
        }
        return this
    }

    fun uppercase(): StringRule {
        add {
            val message = "Was supposed to be uppercase but is '$it'"
            ValidationResult.condition(ValidationFailure(group, message)) {
                it == null || it.all { c -> c.isUpperCase() }
            }
        }
        return this
    }
}