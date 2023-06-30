package io.github.stefankoppier.openapi.validator.core.rules.openapi

import io.github.stefankoppier.openapi.validator.core.ValidationFailure
import io.github.stefankoppier.openapi.validator.core.assertThatResult
import io.github.stefankoppier.openapi.validator.core.rules.RuleGroup
import io.swagger.v3.oas.models.media.Content
import io.swagger.v3.oas.models.media.MediaType
import io.swagger.v3.oas.models.responses.ApiResponse
import kotlin.test.Test

class ResponseRuleTest {

    @Test
    fun `description succeeds`() {
        val rule = ResponseRule()
            .description { exactly("description") }

        assertThatResult(rule.validate(fixture)).isSuccess()
    }

    @Test
    fun `description fails`() {
        val rule = ResponseRule()
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
        val rule = ResponseRule()
            .content { exactly(Content().toList()) }

        assertThatResult(rule.validate(fixture)).isSuccess()
    }

    @Test
    fun `content fails`() {
        val rule = ResponseRule()
            .content { exactly(Content().apply { addMediaType("fail", MediaType()) }.toList()) }

        assertThatResult(rule.validate(fixture)).isFailure(
            RuleGroup.named("content", RuleGroup.Category.GROUP, "", RuleGroup.unknown()),
        )
    }

    companion object {
        val fixture = ApiResponse().apply {
            description = "description"
            content = Content()
        }
    }
}
