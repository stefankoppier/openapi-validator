package io.github.stefankoppier.openapi.validator.core.rules.openapi

import io.github.stefankoppier.openapi.validator.core.assertThatResult
import io.github.stefankoppier.openapi.validator.core.rules.RuleGroup
import io.swagger.v3.oas.models.Components
import io.swagger.v3.oas.models.examples.Example
import io.swagger.v3.oas.models.headers.Header
import io.swagger.v3.oas.models.media.Schema
import io.swagger.v3.oas.models.parameters.Parameter
import io.swagger.v3.oas.models.responses.ApiResponse
import kotlin.test.Test

class ComponentsRuleTest {

    @Test
    fun `schemas succeeds`() {
        val rule = ComponentsRule()
            .schemas { exactly(listOf("schema" to Schema<String>())) }

        assertThatResult(rule.validate(fixture)).isSuccess()
    }

    @Test
    fun `schemas fails`() {
        val rule = ComponentsRule()
            .schemas { exactly(listOf()) }

        assertThatResult(rule.validate(fixture)).isFailure(
            RuleGroup.named("schemas", RuleGroup.Category.GROUP, "", RuleGroup.unknown()),
        )
    }

    @Test
    fun `responses succeeds`() {
        val rule = ComponentsRule()
            .responses { exactly(listOf("response" to ApiResponse())) }

        assertThatResult(rule.validate(fixture)).isSuccess()
    }

    @Test
    fun `responses fails`() {
        val rule = ComponentsRule()
            .responses { exactly(listOf()) }

        assertThatResult(rule.validate(fixture)).isFailure(
            RuleGroup.named("responses", RuleGroup.Category.GROUP, "", RuleGroup.unknown()),
        )
    }

    @Test
    fun `parameters succeeds`() {
        val rule = ComponentsRule()
            .parameters { exactly(listOf(Parameter())) }

        assertThatResult(rule.validate(fixture)).isSuccess()
    }

    @Test
    fun `parameters fails`() {
        val rule = ComponentsRule()
            .parameters { exactly(listOf()) }

        assertThatResult(rule.validate(fixture)).isFailure(
            RuleGroup.named("parameters", RuleGroup.Category.GROUP, "", RuleGroup.unknown()),
        )
    }

    @Test
    fun `examples succeeds`() {
        val rule = ComponentsRule()
            .examples { exactly(listOf("example" to Example())) }

        assertThatResult(rule.validate(fixture)).isSuccess()
    }

    @Test
    fun `examples fails`() {
        val rule = ComponentsRule()
            .examples { exactly(listOf()) }

        assertThatResult(rule.validate(fixture)).isFailure(
            RuleGroup.named("examples", RuleGroup.Category.GROUP, "", RuleGroup.unknown()),
        )
    }

    @Test
    fun `headers succeeds`() {
        val rule = ComponentsRule()
            .headers { exactly(listOf("header" to Header())) }

        assertThatResult(rule.validate(fixture)).isSuccess()
    }

    @Test
    fun `headers fails`() {
        val rule = ComponentsRule()
            .headers { exactly(listOf()) }

        assertThatResult(rule.validate(fixture)).isFailure(
            RuleGroup.named("headers", RuleGroup.Category.GROUP, "", RuleGroup.unknown()),
        )
    }

    companion object {
        val fixture = Components().apply {
            schemas = mapOf("schema" to Schema<String>())
            responses = mapOf("response" to ApiResponse())
            parameters = mapOf("parameter" to Parameter())
            examples = mapOf("example" to Example())
            headers = mapOf("header" to Header())
        }
    }
}
