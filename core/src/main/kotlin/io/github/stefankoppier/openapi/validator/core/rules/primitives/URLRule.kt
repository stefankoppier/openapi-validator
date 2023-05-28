package io.github.stefankoppier.openapi.validator.core.rules.primitives

import io.github.stefankoppier.openapi.validator.core.rules.RuleGroup
import io.github.stefankoppier.openapi.validator.core.rules.ValidationFailure
import io.github.stefankoppier.openapi.validator.core.rules.ValidationResult
import io.github.stefankoppier.openapi.validator.core.rules.ValidationRule
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
}