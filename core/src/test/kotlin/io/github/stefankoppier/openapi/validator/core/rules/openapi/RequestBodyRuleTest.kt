package io.github.stefankoppier.openapi.validator.core.rules.openapi

import io.github.stefankoppier.openapi.validator.core.ValidationFailure
import io.github.stefankoppier.openapi.validator.core.assertThatResult
import io.github.stefankoppier.openapi.validator.core.rules.RuleGroup
import io.swagger.v3.oas.models.media.Content
import io.swagger.v3.oas.models.media.MediaType
import io.swagger.v3.oas.models.parameters.RequestBody
import kotlin.test.Test

class RequestBodyRuleTest {

    @Test
    fun `description succeeds`() {
        val rule = RequestBodyRule()
            .description { exactly("description") }

        assertThatResult(rule.validate(fixture)).isSuccess()
    }

    @Test
    fun `description fails`() {
        val rule = RequestBodyRule()
            .description { exactly("fail") }

        assertThatResult(rule.validate(fixture)).isFailure(
            ValidationFailure(
                RuleGroup.named("description", RuleGroup.Category.FIELD, "", RuleGroup.unknown()),
                "Was supposed to be 'fail' but is 'description'",
            ),
        )
    }

    @Test
    fun `content succeeds`() {
        val rule = RequestBodyRule()
            .content { exactly(Content().toList()) }

        assertThatResult(rule.validate(fixture)).isSuccess()
    }

    @Test
    fun `content fails`() {
        val rule = RequestBodyRule()
            .content { exactly(Content().addMediaType("fail", MediaType()).toList()) }

        assertThatResult(rule.validate(fixture)).isFailure(
            RuleGroup.named("content", RuleGroup.Category.GROUP, "", RuleGroup.unknown()),
        )
    }

    @Test
    fun `required succeeds`() {
        val rule = RequestBodyRule()
            .required { isTrue() }

        assertThatResult(rule.validate(fixture)).isSuccess()
    }

    @Test
    fun `required fails`() {
        val rule = RequestBodyRule()
            .required { isFalse() }

        assertThatResult(rule.validate(fixture)).isFailure(
            ValidationFailure(
                RuleGroup.named("required", RuleGroup.Category.FIELD, "", RuleGroup.unknown()),
                "Was supposed to be 'false' but is 'true'",
            ),
        )
    }

    companion object {
        val fixture = RequestBody().apply {
            description = "description"
            content = Content()
            required = true
        }
    }
}
