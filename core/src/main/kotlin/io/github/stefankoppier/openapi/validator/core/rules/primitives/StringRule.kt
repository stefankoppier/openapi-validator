package io.github.stefankoppier.openapi.validator.core.rules.primitives

import io.github.stefankoppier.openapi.validator.core.rules.RuleGroup
import io.github.stefankoppier.openapi.validator.core.rules.ValidationFailure
import io.github.stefankoppier.openapi.validator.core.rules.ValidationResult
import io.github.stefankoppier.openapi.validator.core.rules.ValidationRule

class StringRule(group: RuleGroup = RuleGroup.unknown()) : ValidationRule<String>(group) {

    fun matches(regex: Regex): StringRule {
        add {
            val message = "Was supposed to match '$regex'"
            ValidationResult.condition(ValidationFailure(group, message)) {
                it == null || regex.matches(it)
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

    fun alpha(): StringRule {
        add {
            val message = "Was supposed to be alphanumeric but is '$it'"
            ValidationResult.condition(ValidationFailure(group, message)) {
                it == null || it.all { c -> c.isLetter() }
            }
        }
        return this
    }

    fun numeric(): StringRule {
        add {
            val message = "Was supposed to be alphanumeric but is '$it'"
            ValidationResult.condition(ValidationFailure(group, message)) {
                it == null || it.all { c -> c.isDigit() }
            }
        }
        return this
    }

    fun alphanumeric(): StringRule {
        add {
            val message = "Was supposed to be alphanumeric but is '$it'"
            ValidationResult.condition(ValidationFailure(group, message)) {
                it == null || it.all { c -> c.isLetterOrDigit() }
            }
        }
        return this
    }


    fun kebabcase(): StringRule {
        add {
            val message = "Was supposed to be alphanumeric but is '$it'"
            ValidationResult.condition(ValidationFailure(group, message)) {
                it == null || it.all { c -> c.isLetterOrDigit() || c == '-' }
            }
        }
        return this
    }

    fun snakecase(): StringRule {
        add {
            val message = "Was supposed to be alphanumeric but is '$it'"
            ValidationResult.condition(ValidationFailure(group, message)) {
                it == null || it.all { c -> c.isLetterOrDigit() || c == '_' }
            }
        }
        return this
    }
}