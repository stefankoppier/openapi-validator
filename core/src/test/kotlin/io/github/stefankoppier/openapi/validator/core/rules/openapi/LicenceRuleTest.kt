package io.github.stefankoppier.openapi.validator.core.rules.openapi

import io.github.stefankoppier.openapi.validator.core.ValidationFailure
import io.github.stefankoppier.openapi.validator.core.assertThatResult
import io.github.stefankoppier.openapi.validator.core.rules.RuleGroup
import io.swagger.v3.oas.models.info.License
import kotlin.test.Test

class LicenceRuleTest {

    @Test
    fun `name is required`() {
        val rule = LicenceRule()
        assertThatResult(rule.validate(License())).isFailure(
            ValidationFailure(RuleGroup.named("name", RuleGroup.Category.FIELD, "", RuleGroup.unknown()), "Was required but is not given"),
        )
    }

    @Test
    fun `name succeeds`() {
        val rule = LicenceRule()
            .name { exactly("name") }

        assertThatResult(rule.validate(fixture)).isSuccess()
    }

    @Test
    fun `name fails`() {
        val rule = LicenceRule()
            .name { exactly("fail") }

        assertThatResult(rule.validate(fixture)).isFailure(
            ValidationFailure(
                RuleGroup.named("name", RuleGroup.Category.FIELD, "", RuleGroup.unknown()),
                "Was supposed to be 'fail' but is 'name'",
            ),
        )
    }

    @Test
    fun `identifier succeeds`() {
        val rule = LicenceRule()
            .identifier { exactly("identifier") }

        assertThatResult(rule.validate(fixture)).isSuccess()
    }

    @Test
    fun `identifier fails`() {
        val rule = LicenceRule()
            .identifier { exactly("fail") }

        assertThatResult(rule.validate(fixture)).isFailure(
            ValidationFailure(
                RuleGroup.named("identifier", RuleGroup.Category.FIELD, "", RuleGroup.unknown()),
                "Was supposed to be 'fail' but is 'identifier'",
            ),
        )
    }

    @Test
    fun `url succeeds`() {
        val rule = LicenceRule()
            .url { exactly("http://localhost") }

        assertThatResult(rule.validate(fixture)).isSuccess()
    }

    @Test
    fun `url fails`() {
        val rule = LicenceRule()
            .url { exactly("https://localhost") }

        assertThatResult(rule.validate(fixture)).isFailure(
            ValidationFailure(
                RuleGroup.named("url", RuleGroup.Category.FIELD, "", RuleGroup.unknown()),
                "Was supposed to be 'https://localhost' but is 'http://localhost'",
            ),
        )
    }

    companion object {
        val fixture = License().apply {
            name = "name"
            identifier = "identifier"
            url = "http://localhost"
        }
    }
}
