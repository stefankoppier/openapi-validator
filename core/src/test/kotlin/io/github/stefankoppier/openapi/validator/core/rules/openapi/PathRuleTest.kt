package io.github.stefankoppier.openapi.validator.core.rules.openapi

import io.github.stefankoppier.openapi.validator.core.ValidationFailure
import io.github.stefankoppier.openapi.validator.core.assertThatResult
import io.github.stefankoppier.openapi.validator.core.rules.RuleGroup
import io.swagger.v3.oas.models.PathItem
import io.swagger.v3.oas.models.parameters.Parameter
import io.swagger.v3.oas.models.servers.Server
import kotlin.test.Test

class PathRuleTest {

    @Test
    fun `summary succeeds`() {
        val rule = PathRule()
            .summary { exactly("Summary") }

        assertThatResult(rule.validate(fixture)).isSuccess()
    }

    @Test
    fun `summary fails`() {
        val rule = PathRule()
            .summary { exactly("Fail") }

        assertThatResult(rule.validate(fixture)).isFailure(
            ValidationFailure(
                RuleGroup.named("summary", RuleGroup.Category.FIELD, "", RuleGroup.unknown()),
                "Was supposed to be 'Fail' but is 'Summary'",
            ),
        )
    }

    @Test
    fun `description succeeds`() {
        val rule = PathRule()
            .description { exactly("Description") }

        assertThatResult(rule.validate(fixture)).isSuccess()
    }

    @Test
    fun `description fails`() {
        val rule = PathRule()
            .description { exactly("Fail") }

        assertThatResult(rule.validate(fixture)).isFailure(
            ValidationFailure(
                RuleGroup.named("description", RuleGroup.Category.FIELD, "", RuleGroup.unknown()),
                "Was supposed to be 'Fail' but is 'Description'",
            ),
        )
    }

    @Test
    fun `servers succeeds`() {
        val rule = PathRule()
            .servers { exactly(listOf(Server())) }

        assertThatResult(rule.validate(fixture)).isSuccess()
    }

    @Test
    fun `servers fails`() {
        val rule = PathRule()
            .servers { exactly(listOf(Server().apply { url = "http://localhost" })) }

        assertThatResult(rule.validate(fixture))
            .isFailure(
                RuleGroup.named("servers", RuleGroup.Category.GROUP, "", RuleGroup.unknown()),
            )
    }

    @Test
    fun `parameters succeeds`() {
        val rule = PathRule()
            .parameters { exactly(listOf(Parameter())) }

        assertThatResult(rule.validate(fixture)).isSuccess()
    }

    @Test
    fun `parameters fails`() {
        val rule = PathRule()
            .parameters { exactly(listOf(Parameter().apply { name = "parameter" })) }

        assertThatResult(rule.validate(fixture))
            .isFailure(RuleGroup.named("parameters", RuleGroup.Category.GROUP, "", RuleGroup.unknown()))
    }

    companion object {
        val fixture = PathItem().apply {
            summary = "Summary"
            description = "Description"
            servers = listOf(Server())
            parameters = listOf(Parameter())
            `$ref` = "ref"
        }
    }
}
