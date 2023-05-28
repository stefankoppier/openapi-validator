package io.github.stefankoppier.openapi.validator.rules.primitives

import io.github.stefankoppier.openapi.validator.rules.RuleGroup
import io.github.stefankoppier.openapi.validator.rules.ValidationFailure
import io.github.stefankoppier.openapi.validator.rules.ValidationResult
import io.github.stefankoppier.openapi.validator.rules.ValidationRule
import java.net.URL

class URLRule(group: RuleGroup = RuleGroup.unknown()) : ValidationRule<URL?>(group) {

    fun validate(fixture: String?): ValidationResult {
        return if (fixture != null) {
            runCatching { URL(fixture) }
                .map { super.validate(it) }
                .getOrElse {
                    val message = "Was required to be in the form of a URL but was '$fixture'"
                    ValidationResult.failure(ValidationFailure(group, message))
                }
        } else {
            super.validate(null)
        }
    }

    fun required(): URLRule {
        add {
            val message = "Was required but is '$it'"
            ValidationResult.condition(ValidationFailure(group, message)) {
                it != null
            }
        }
        return this
    }
}