package io.github.stefankoppier.openapi.validator.core.rules.openapi

import io.github.stefankoppier.openapi.validator.core.ValidationFailure
import io.github.stefankoppier.openapi.validator.core.assertThatResult
import io.github.stefankoppier.openapi.validator.core.rules.RuleGroup
import io.swagger.v3.oas.models.examples.Example
import io.swagger.v3.oas.models.headers.Header
import io.swagger.v3.oas.models.media.Content
import io.swagger.v3.oas.models.media.MediaType
import io.swagger.v3.oas.models.media.Schema
import kotlin.test.Test

class HeaderRuleTest {

    @Test
    fun `description succeeds`() {
        val rule = HeaderRule()
            .description { exactly("description") }

        assertThatResult(rule.validate(fixture)).isSuccess()
    }

    @Test
    fun `description fails`() {
        val rule = HeaderRule()
            .description { exactly("fail") }

        assertThatResult(rule.validate(fixture)).isFailure(
            ValidationFailure(
                RuleGroup.named("description", RuleGroup.Category.FIELD, "", RuleGroup.unknown()),
                "Was supposed to be 'fail' but is 'description'",
            ),
        )
    }

    @Test
    fun `required succeeds`() {
        val rule = HeaderRule()
            .required { isTrue() }

        assertThatResult(rule.validate(fixture)).isSuccess()
    }

    @Test
    fun `required fails`() {
        val rule = HeaderRule()
            .required { isFalse() }

        assertThatResult(rule.validate(fixture)).isFailure(
            ValidationFailure(
                RuleGroup.named("required", RuleGroup.Category.FIELD, "", RuleGroup.unknown()),
                "Was supposed to be 'false' but is 'true'",
            ),
        )
    }

    @Test
    fun `deprecated succeeds`() {
        val rule = HeaderRule()
            .deprecated { isTrue() }

        assertThatResult(rule.validate(fixture)).isSuccess()
    }

    @Test
    fun `deprecated fails`() {
        val rule = HeaderRule()
            .deprecated { isFalse() }

        assertThatResult(rule.validate(fixture)).isFailure(
            ValidationFailure(
                RuleGroup.named("deprecated", RuleGroup.Category.FIELD, "", RuleGroup.unknown()),
                "Was supposed to be 'false' but is 'true'",
            ),
        )
    }

    @Test
    fun `style succeeds`() {
        val rule = HeaderRule()
            .style { exactly(Header.StyleEnum.SIMPLE) }

        assertThatResult(rule.validate(fixture)).isSuccess()
    }

    @Test
    fun `explode succeeds`() {
        val rule = HeaderRule()
            .explode { isTrue() }

        assertThatResult(rule.validate(fixture)).isSuccess()
    }

    @Test
    fun `explode fails`() {
        val rule = HeaderRule()
            .explode { isFalse() }

        assertThatResult(rule.validate(fixture)).isFailure(
            ValidationFailure(
                RuleGroup.named("explode", RuleGroup.Category.FIELD, "", RuleGroup.unknown()),
                "Was supposed to be 'false' but is 'true'",
            ),
        )
    }

    @Test
    fun `schema succeeds`() {
        val rule = HeaderRule()
            .schema { exactly(Schema<String>()) }

        assertThatResult(rule.validate(fixture)).isSuccess()
    }

    @Test
    fun `schema fails`() {
        val rule = HeaderRule()
            .schema { exactly(Schema<String>().apply { description = "fail" }) }

        assertThatResult(rule.validate(fixture)).isFailure(
            RuleGroup.named("schema", RuleGroup.Category.GROUP, "", RuleGroup.unknown()),
        )
    }

    @Test
    fun `example succeeds`() {
        val rule = HeaderRule()
            .example { exactly("example") }

        assertThatResult(rule.validate(fixture)).isSuccess()
    }

    @Test
    fun `example fails`() {
        val rule = HeaderRule()
            .example { exactly("fail") }

        assertThatResult(rule.validate(fixture)).isFailure(
            ValidationFailure(
                RuleGroup.named("example", RuleGroup.Category.FIELD, "", RuleGroup.unknown()),
                "Was supposed to be 'fail' but is 'example'",
            ),
        )
    }

    @Test
    fun `examples succeeds`() {
        val rule = HeaderRule()
            .examples { exactly(mapOf("example" to Example()).toList()) }

        assertThatResult(rule.validate(fixture)).isSuccess()
    }

    @Test
    fun `examples fails`() {
        val rule = HeaderRule()
            .examples { exactly(mapOf("fail" to Example()).toList()) }

        assertThatResult(rule.validate(fixture)).isFailure(
            RuleGroup.named("examples", RuleGroup.Category.GROUP, "", RuleGroup.unknown()),
        )
    }

    @Test
    fun `content succeeds`() {
        val rule = HeaderRule()
            .content { exactly(Content().toList()) }

        assertThatResult(rule.validate(fixture)).isSuccess()
    }

    @Test
    fun `content fails`() {
        val rule = HeaderRule()
            .content { exactly(Content().apply { addMediaType("fail", MediaType()) }.toList()) }

        assertThatResult(rule.validate(fixture)).isFailure(
            RuleGroup.named("content", RuleGroup.Category.GROUP, "", RuleGroup.unknown()),
        )
    }

    companion object {
        val fixture = Header().apply {
            description = "description"
            required = true
            deprecated = true
            style = Header.StyleEnum.SIMPLE
            explode = true
            schema = Schema<String>()
            example = "example"
            examples = mapOf("example" to Example())
            content = Content()
        }
    }
}
