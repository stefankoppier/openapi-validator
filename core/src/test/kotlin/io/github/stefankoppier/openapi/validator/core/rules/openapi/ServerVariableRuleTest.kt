package io.github.stefankoppier.openapi.validator.core.rules.openapi

import io.github.stefankoppier.openapi.validator.core.ValidationFailure
import io.github.stefankoppier.openapi.validator.core.assertThatResult
import io.github.stefankoppier.openapi.validator.core.rules.RuleGroup
import io.swagger.v3.oas.models.servers.ServerVariable
import kotlin.test.Test

class ServerVariableRuleTest {

    @Test
    fun `default is required`() {
        val rule = ServerVariableRule()

        assertThatResult(rule.validate(ServerVariable())).isFailure(
            ValidationFailure(
                RuleGroup.named("default", RuleGroup.Category.FIELD, "", RuleGroup.unknown()),
                "Was required but is not given",
            ),
        )
    }

    @Test
    fun `enum contains default if given`() {
        val rule = ServerVariableRule()

        assertThatResult(rule.validate(ServerVariable().apply { enum = listOf("enum"); default = "default" })).isFailure(
            ValidationFailure(RuleGroup.unknown(), "Should contain 'default' but is '[enum]'"),
        )
    }

    @Test
    fun `enum succeeds`() {
        val rule = ServerVariableRule()
            .enum { exactly(listOf("enum", "default")) }

        assertThatResult(rule.validate(fixture)).isSuccess()
    }

    @Test
    fun `enum fails`() {
        val rule = ServerVariableRule()
            .enum { exactly(listOf("fail")) }

        assertThatResult(rule.validate(fixture)).isFailure(
            ValidationFailure(
                RuleGroup.named("enum", RuleGroup.Category.FIELD, "", RuleGroup.unknown()),
                "Was supposed to be '[fail]' but is '[enum, default]'",
            ),
        )
    }

    @Test
    fun `default succeeds`() {
        val rule = ServerVariableRule()
            .default { exactly("default") }

        assertThatResult(rule.validate(fixture)).isSuccess()
    }

    @Test
    fun `default fails`() {
        val rule = ServerVariableRule()
            .default { exactly("fail") }

        assertThatResult(rule.validate(fixture)).isFailure(
            ValidationFailure(
                RuleGroup.named("default", RuleGroup.Category.FIELD, "", RuleGroup.unknown()),
                "Was supposed to be 'fail' but is 'default'",
            ),
        )
    }

    @Test
    fun `description succeeds`() {
        val rule = ServerVariableRule()
            .description { exactly("description") }

        assertThatResult(rule.validate(fixture)).isSuccess()
    }

    @Test
    fun `description fails`() {
        val rule = ServerVariableRule()
            .description { exactly("fail") }

        assertThatResult(rule.validate(fixture)).isFailure(
            ValidationFailure(
                RuleGroup.named("description", RuleGroup.Category.FIELD, "", RuleGroup.unknown()),
                "Was supposed to be 'fail' but is 'description'",
            ),
        )
    }

    companion object {
        val fixture = ServerVariable().apply {
            enum = listOf("enum", "default")
            default = "default"
            description = "description"
        }
    }
}
