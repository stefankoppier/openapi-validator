package io.github.stefankoppier.openapi.validator.core.rules.openapi

import io.github.stefankoppier.openapi.validator.core.ValidationFailure
import io.github.stefankoppier.openapi.validator.core.assertThatResult
import io.github.stefankoppier.openapi.validator.core.rules.RuleGroup
import io.swagger.v3.oas.models.examples.Example
import kotlin.test.Test

class ExampleRuleTest {

    @Test
    fun `summary succeeds`() {
        val rule = ExampleRule()
            .summary { exactly("summary") }

        assertThatResult(rule.validate(fixture)).isSuccess()
    }

    @Test
    fun `summary fails`() {
        val rule = ExampleRule()
            .summary { exactly("fail") }

        assertThatResult(rule.validate(fixture)).isFailure(
            ValidationFailure(
                RuleGroup.named("summary", RuleGroup.Category.FIELD, "", RuleGroup.unknown()),
                "Was supposed to be 'fail' but is 'summary'",
            ),
        )
    }

    @Test
    fun `description succeeds`() {
        val rule = ExampleRule()
            .description { exactly("description") }

        assertThatResult(rule.validate(fixture)).isSuccess()
    }

    @Test
    fun `description fails`() {
        val rule = ExampleRule()
            .description { exactly("fail") }

        assertThatResult(rule.validate(fixture)).isFailure(
            ValidationFailure(
                RuleGroup.named("description", RuleGroup.Category.FIELD, "", RuleGroup.unknown()),
                "Was supposed to be 'fail' but is 'description'",
            ),
        )
    }

    @Test
    fun `value succeeds`() {
        val rule = ExampleRule()
            .value { exactly("value") }

        assertThatResult(rule.validate(fixture)).isSuccess()
    }

    @Test
    fun `value fails`() {
        val rule = ExampleRule()
            .value { exactly("fail") }

        assertThatResult(rule.validate(fixture)).isFailure(
            ValidationFailure(
                RuleGroup.named("value", RuleGroup.Category.FIELD, "", RuleGroup.unknown()),
                "Was supposed to be 'fail' but is 'value'",
            ),
        )
    }

    @Test
    fun `externalValue succeeds`() {
        val rule = ExampleRule()
            .externalValue { exactly("http://localhost") }

        assertThatResult(rule.validate(fixture)).isSuccess()
    }

    @Test
    fun `externalValue fails`() {
        val rule = ExampleRule()
            .externalValue { exactly("https://localhost") }

        assertThatResult(rule.validate(fixture)).isFailure(
            ValidationFailure(
                RuleGroup.named("externalValue", RuleGroup.Category.FIELD, "", RuleGroup.unknown()),
                "Was supposed to be 'https://localhost' but is 'http://localhost'",
            ),
        )
    }

    companion object {
        val fixture = Example().apply {
            summary = "summary"
            description = "description"
            value = "value"
            externalValue = "http://localhost"
        }
    }
}
