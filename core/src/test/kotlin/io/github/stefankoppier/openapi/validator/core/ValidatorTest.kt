package io.github.stefankoppier.openapi.validator.core

import com.google.common.io.Resources.getResource
import io.github.stefankoppier.openapi.validator.core.rules.RuleGroup
import io.github.stefankoppier.openapi.validator.core.rules.openapi.OpenAPIRule
import kotlin.io.path.Path
import kotlin.test.Test

class ValidatorTest {

    @Test
    fun `validate yaml success`() {
        val validator = Validator(OpenAPIRule())

        assertThatResult(validator.validate(document.readText(Charsets.UTF_8)))
            .isSuccess()
    }

    @Test
    fun `validate yaml fail`() {
        val validator = Validator(OpenAPIRule())

        assertThatResult(validator.validate("invalid yaml")).isFailure(
            ValidationFailure(
                RuleGroup.named("", RuleGroup.Category.EXCEPTION),
                "Failed to parse yaml",
            ),
        )
    }

    @Test
    fun `validate path`() {
        val validator = Validator(OpenAPIRule())

        assertThatResult(validator.validate(document.toURI()))
            .isSuccess()
    }

    @Test
    fun `validate path fail`() {
        val validator = Validator(OpenAPIRule())

        val path = Path(".").toUri()
        assertThatResult(validator.validate(path)).isFailure(
            ValidationFailure(
                RuleGroup.named("", RuleGroup.Category.EXCEPTION),
                "Failed to parse contents from '$path'",
            ),
        )
    }

    companion object {
        private val document = getResource("petstore.yaml")
    }
}
