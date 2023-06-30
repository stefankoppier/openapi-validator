package io.github.stefankoppier.openapi.validator.core.rules.openapi

import io.github.stefankoppier.openapi.validator.core.assertThatResult
import io.github.stefankoppier.openapi.validator.core.rules.RuleGroup
import io.swagger.v3.oas.models.examples.Example
import io.swagger.v3.oas.models.media.Encoding
import io.swagger.v3.oas.models.media.MediaType
import io.swagger.v3.oas.models.media.Schema
import kotlin.test.Test

class MediaTypeRuleTest {

    @Test
    fun `schema succeeds`() {
        val rule = MediaTypeRule()
            .schema { exactly(Schema<String>()) }

        assertThatResult(rule.validate(fixture)).isSuccess()
    }

    @Test
    fun `schema fails`() {
        val rule = MediaTypeRule()
            .schema { exactly(Schema<String>().apply { title = "fail" }) }

        assertThatResult(rule.validate(fixture)).isFailure(
            RuleGroup.named("schema", RuleGroup.Category.GROUP, "", RuleGroup.unknown()),
        )
    }

    @Test
    fun `example succeeds`() {
        val rule = MediaTypeRule()
            .example { exactly(Example()) }

        assertThatResult(rule.validate(fixture)).isSuccess()
    }

    @Test
    fun `example fails`() {
        val rule = MediaTypeRule()
            .example { exactly(Example().apply { summary = "fail" }) }

        assertThatResult(rule.validate(fixture)).isFailure(
            RuleGroup.named("example", RuleGroup.Category.FIELD, "", RuleGroup.unknown()),
        )
    }

    @Test
    fun `examples succeeds`() {
        val rule = MediaTypeRule()
            .examples { exactly(listOf("example" to Example())) }

        assertThatResult(rule.validate(fixture)).isSuccess()
    }

    @Test
    fun `examples fails`() {
        val rule = MediaTypeRule()
            .examples { exactly(listOf("fail" to Example())) }

        assertThatResult(rule.validate(fixture)).isFailure(
            RuleGroup.named("examples", RuleGroup.Category.GROUP, "", RuleGroup.unknown()),
        )
    }

    @Test
    fun `encoding succeeds`() {
        val rule = MediaTypeRule()
            .encoding { exactly(listOf("encoding" to Encoding())) }

        assertThatResult(rule.validate(fixture)).isSuccess()
    }

    @Test
    fun `encoding fails`() {
        val rule = MediaTypeRule()
            .encoding { exactly(listOf("fail" to Encoding())) }

        assertThatResult(rule.validate(fixture)).isFailure(
            RuleGroup.named("encoding", RuleGroup.Category.GROUP, "", RuleGroup.unknown()),
        )
    }

    companion object {
        val fixture = MediaType().apply {
            schema = Schema<String>()
            example = Example()
            examples = mapOf("example" to Example())
            encoding = mapOf("encoding" to Encoding())
        }
    }
}
