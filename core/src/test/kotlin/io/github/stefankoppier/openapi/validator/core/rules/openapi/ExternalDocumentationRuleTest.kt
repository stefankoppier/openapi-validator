package io.github.stefankoppier.openapi.validator.core.rules.openapi

import io.github.stefankoppier.openapi.validator.core.ValidationFailure
import io.github.stefankoppier.openapi.validator.core.assertThatResult
import io.github.stefankoppier.openapi.validator.core.rules.RuleGroup
import io.swagger.v3.oas.models.ExternalDocumentation
import kotlin.test.Test

class ExternalDocumentationRuleTest {

    @Test
    fun `url is required`() {
        val rule = ExternalDocumentationRule()
        assertThatResult(rule.validate(ExternalDocumentation())).isFailure(
            ValidationFailure(RuleGroup.named("url", RuleGroup.Category.FIELD, "", RuleGroup.unknown()), "Was required but is not given"),
        )
    }

    @Test
    fun `propertyName succeeds`() {
        val rule = ExternalDocumentationRule()
            .description { exactly("description") }

        assertThatResult(rule.validate(fixture)).isSuccess()
    }

    @Test
    fun `propertyName fails`() {
        val rule = ExternalDocumentationRule()
            .description { exactly("fail") }

        assertThatResult(rule.validate(fixture)).isFailure(
            ValidationFailure(
                RuleGroup.named("description", RuleGroup.Category.FIELD, "", RuleGroup.unknown()),
                "Was supposed to be 'fail' but is 'description'",
            ),
        )
    }

    @Test
    fun `url succeeds`() {
        val rule = ExternalDocumentationRule()
            .url { exactly("http://localhost") }

        assertThatResult(rule.validate(fixture)).isSuccess()
    }

    @Test
    fun `url fails`() {
        val rule = ExternalDocumentationRule()
            .url { exactly("https://localhost") }

        assertThatResult(rule.validate(fixture)).isFailure(
            ValidationFailure(
                RuleGroup.named("url", RuleGroup.Category.FIELD, "", RuleGroup.unknown()),
                "Was supposed to be 'https://localhost' but is 'http://localhost'",
            ),
        )
    }

    companion object {
        val fixture = ExternalDocumentation().apply {
            description = "description"
            url = "http://localhost"
        }
    }
}
