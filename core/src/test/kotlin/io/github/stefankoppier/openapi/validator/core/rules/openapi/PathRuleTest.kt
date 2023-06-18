package io.github.stefankoppier.openapi.validator.core.rules.openapi

import io.github.stefankoppier.openapi.validator.core.ValidationFailure
import io.github.stefankoppier.openapi.validator.core.assertThat
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

        assertThat(rule.validate(fixture)).isSuccess()
    }

    @Test
    fun `summary fails`() {
        val rule = PathRule()
            .summary { exactly("Fail") }

        assertThat(rule.validate(fixture)).isFailure(
            ValidationFailure(
                RuleGroup.named("summary", "", RuleGroup.Category.FIELD, RuleGroup.unknown()),
                "Was supposed to be 'Fail' but is 'Summary'")
        )
    }

    @Test
    fun `description succeeds`() {
        val rule = PathRule()
            .description { exactly("Description") }

        assertThat(rule.validate(fixture)).isSuccess()
    }

    @Test
    fun `description fails`() {
        val rule = PathRule()
            .description { exactly("Fail") }

        assertThat(rule.validate(fixture)).isFailure(
            ValidationFailure(
                RuleGroup.named("description", "", RuleGroup.Category.FIELD, RuleGroup.unknown()),
                "Was supposed to be 'Fail' but is 'Description'")
        )
    }

    @Test
    fun `servers succeeds`() {
        val rule = PathRule()
            .servers { exactly(listOf(Server())) }

        assertThat(rule.validate(fixture)).isSuccess()
    }

    @Test
    fun `servers fails`() {
        val rule = PathRule()
            .servers { exactly(listOf(Server().apply { url = "http://localhost" })) }

        assertThat(rule.validate(fixture))
            .isFailure(RuleGroup.named("servers", "", RuleGroup.Category.OBJECT, RuleGroup.unknown())
        )
    }

    @Test
    fun `parameters succeeds`() {
        val rule = PathRule()
            .parameters { exactly(listOf(Parameter())) }

        assertThat(rule.validate(fixture)).isSuccess()
    }

    @Test
    fun `parameters fails`() {
        val rule = PathRule()
            .parameters { exactly(listOf(Parameter().apply { name = "parameter" })) }

        assertThat(rule.validate(fixture))
            .isFailure(RuleGroup.named("parameters", "", RuleGroup.Category.OBJECT, RuleGroup.unknown()))
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