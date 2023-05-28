package io.github.stefankoppier.openapi.validator.junit

import io.github.stefankoppier.openapi.validator.core.rules.ValidationResult
import io.github.stefankoppier.openapi.validator.core.rules.openapi.OpenAPIRule
import io.swagger.v3.oas.models.OpenAPI
import org.junit.jupiter.api.fail

fun assertDocumentIsValidFor(document: OpenAPI, rule: OpenAPIRule) {
    val result = rule.validate(document)
    if (result.isFailure) {
        fail("""
            |Validation for document failed
            |${amountLine(result)}
            |
            |${result.summarize()}
        """.trimMargin())
    }
}

private fun amountLine(result: ValidationResult): String {
    return when (val size = result.failures.size) {
        1 -> "There is 1 problem."
        else -> "There are $size problems."
    }
}