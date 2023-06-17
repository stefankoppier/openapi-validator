package io.github.stefankoppier.openapi.validator.core

import org.assertj.core.api.ObjectAssert
import java.util.concurrent.atomic.AtomicReference


fun assertThat(actual: ValidationResult) =
    ValidationResultAssertions(AtomicReference(actual))

class ValidationResultAssertions(actual: AtomicReference<ValidationResult>) : ObjectAssert<ValidationResult>(actual) {

    fun isFailure(vararg failures: ValidationFailure) {
        withFailMessage { "ValidationResult should be failure but was successful" }
            .hasFieldOrPropertyWithValue("failure", true)
            .apply { if (failures.isNotEmpty()) hasFieldOrPropertyWithValue("failures", failures) }
    }

    fun isSuccess() {
        withFailMessage {
            """
            |ValidationResult should be successful but was failure:
            |${actual.summarize()}
            """.trimMargin() }
            .hasFieldOrPropertyWithValue("success", true)
    }
}