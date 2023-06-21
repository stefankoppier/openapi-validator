package io.github.stefankoppier.openapi.validator.junit

import io.github.stefankoppier.openapi.validator.core.ValidationResult
import io.github.stefankoppier.openapi.validator.core.Validator
import io.github.stefankoppier.openapi.validator.core.rules.openapi.OpenAPIRule
import org.junit.jupiter.api.fail

fun assertDocumentIsValidFor(rule: OpenAPIRule) {
    val result = Validator(rule).validate(OpenAPIValidationExtension.uri)
    if (result.isFailure) {
        fail(
            """
            |Validation for document failed
            |${amountLine(result)}
            |
            |${result.summarize()}
            """.trimMargin(),
        )
    }
}

fun assertDocumentIsValidFor(rule: () -> OpenAPIRule) =
    assertDocumentIsValidFor(rule())

private fun amountLine(result: ValidationResult): String {
    return when (val size = result.failures.size) {
        1 -> "There is 1 problem."
        else -> "There are $size problems."
    }
}
