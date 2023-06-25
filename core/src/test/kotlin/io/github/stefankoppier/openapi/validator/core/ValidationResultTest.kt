package io.github.stefankoppier.openapi.validator.core

import io.github.stefankoppier.openapi.validator.core.rules.RuleGroup
import org.assertj.core.api.Assertions.assertThat
import kotlin.test.Test

class ValidationResultTest {

    @Test
    fun `summarize contains correct values`() {
        val obj = RuleGroup.named("object", RuleGroup.Category.GROUP)
        val field = RuleGroup.named("field", RuleGroup.Category.FIELD, "", obj)
        val message = RuleGroup.named("message", RuleGroup.Category.MESSAGE, "", field)
        val exception = RuleGroup.named("exception", RuleGroup.Category.EXCEPTION, "", obj)

        val result = ValidationResult(
            mutableListOf(
                ValidationFailure(obj, "Object"),
                ValidationFailure(field, "Field"),
                ValidationFailure(message, "Message"),
                ValidationFailure(exception, "Exception"),
            ),
        )

        assertThat(result.summarize())
            .contains(
                "For object",
                "- Object",
                "Field 'field' does not comply:",
                "- Field",
                "- message",
                "- Message",
                "An exception occurred: exception",
                "- Exception",
            )
    }
}
