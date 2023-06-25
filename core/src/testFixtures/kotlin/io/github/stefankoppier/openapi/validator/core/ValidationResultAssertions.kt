package io.github.stefankoppier.openapi.validator.core

import io.github.stefankoppier.openapi.validator.core.rules.RuleGroup
import org.assertj.core.api.ObjectAssert
import java.util.concurrent.atomic.AtomicReference

fun assertThatResult(actual: ValidationResult) =
    ValidationResultAssertions(AtomicReference(actual))

class ValidationResultAssertions(actual: AtomicReference<ValidationResult>) : ObjectAssert<ValidationResult>(actual) {

    fun isFailure(group: RuleGroup) {
        withFailMessage { "ValidationResult should be failure but was successful" }
            .hasFieldOrPropertyWithValue("failure", true)
            .apply {
                withFailMessage {
                    """
                    |Rule group was expected to be
                    |$group
                    |But was
                    |${actual.failures.joinToString(separator = System.lineSeparator()) { it.group.toString() }}
                    """.trimMargin()
                }
                    .usingRecursiveComparison()
                    .isEqualTo(ValidationResult.failure(*actual.failures.map { it.copy(group = group) }.toTypedArray()))
            }
    }

    fun isFailure(vararg failures: ValidationFailure) {
        withFailMessage { "ValidationResult should be failure but was successful" }
            .hasFieldOrPropertyWithValue("failure", true)
            .apply {
                if (failures.isNotEmpty()) {
                    withFailMessage {
                        """"
                        |Failures was expected to be 
                        |${failures.joinToString(separator = System.lineSeparator())}
                        |but was
                        |${actual.failures.joinToString(separator = System.lineSeparator())}
                        |
                        """.trimMargin()
                    }
                        .usingRecursiveComparison()
                        .ignoringCollectionOrder()
                        .isEqualTo(ValidationResult.failure(*failures))
                }
            }
    }

    fun isSuccess() {
        withFailMessage {
            """
            |ValidationResult should be successful but was failure:
            |${actual.summarize()}
            |
            """.trimMargin()
        }
            .hasFieldOrPropertyWithValue("success", true)
    }
}
