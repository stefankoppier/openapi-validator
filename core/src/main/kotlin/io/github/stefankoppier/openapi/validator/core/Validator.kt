package io.github.stefankoppier.openapi.validator.core

import io.github.stefankoppier.openapi.validator.core.rules.ValidationRule
import io.swagger.v3.oas.models.OpenAPI

class Validator(private val rule: ValidationRule<OpenAPI>) {

    fun validate(document: OpenAPI): ValidationResult {
        return rule.validate(document)
    }
}
