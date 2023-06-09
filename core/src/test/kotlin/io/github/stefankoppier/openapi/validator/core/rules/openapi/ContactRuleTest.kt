package io.github.stefankoppier.openapi.validator.core.rules.openapi

import io.github.stefankoppier.openapi.validator.core.ValidationFailure
import io.github.stefankoppier.openapi.validator.core.assertThatResult
import io.github.stefankoppier.openapi.validator.core.rules.RuleGroup
import io.swagger.v3.oas.models.info.Contact
import kotlin.test.Test

class ContactRuleTest {

    @Test
    fun `is required`() {
        val rule = ContactRule()

        assertThatResult(rule.validate(null)).isFailure(
            ValidationFailure(
                RuleGroup.named("", RuleGroup.Category.UNKNOWN),
                "Was required but is not given",
            ),
        )
    }

    @Test
    fun `name succeeds`() {
        val rule = ContactRule()
            .name { exactly("name") }

        assertThatResult(rule.validate(fixture)).isSuccess()
    }

    @Test
    fun `name fails`() {
        val rule = ContactRule()
            .name { exactly("Fail") }

        assertThatResult(rule.validate(fixture)).isFailure(
            ValidationFailure(
                RuleGroup.named("name", RuleGroup.Category.FIELD, "", RuleGroup.unknown()),
                "Was supposed to be 'Fail' but is 'name'",
            ),
        )
    }

    @Test
    fun `url succeeds`() {
        val rule = ContactRule()
            .url { exactly("http://localhost") }

        assertThatResult(rule.validate(fixture)).isSuccess()
    }

    @Test
    fun `url fails`() {
        val rule = ContactRule()
            .url { exactly("https://localhost") }

        assertThatResult(rule.validate(fixture)).isFailure(
            ValidationFailure(
                RuleGroup.named("url", RuleGroup.Category.FIELD, "", RuleGroup.unknown()),
                "Was supposed to be 'https://localhost' but is 'http://localhost'",
            ),
        )
    }

    @Test
    fun `email succeeds`() {
        val rule = ContactRule()
            .email { exactly("test@test.com") }

        assertThatResult(rule.validate(fixture)).isSuccess()
    }

    @Test
    fun `email fails`() {
        val rule = ContactRule()
            .email { exactly("fail@fail.com") }

        assertThatResult(rule.validate(fixture)).isFailure(
            ValidationFailure(
                RuleGroup.named("email", RuleGroup.Category.FIELD, "", RuleGroup.unknown()),
                "Was supposed to be 'fail@fail.com' but is 'test@test.com'",
            ),
        )
    }

    companion object {
        val fixture = Contact().apply {
            name = "name"
            url = "http://localhost"
            email = "test@test.com"
        }
    }
}
