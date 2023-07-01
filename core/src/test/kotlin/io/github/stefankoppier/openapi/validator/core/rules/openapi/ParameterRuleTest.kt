package io.github.stefankoppier.openapi.validator.core.rules.openapi

import io.github.stefankoppier.openapi.validator.core.ValidationFailure
import io.github.stefankoppier.openapi.validator.core.assertThatResult
import io.github.stefankoppier.openapi.validator.core.rules.RuleGroup
import io.swagger.v3.oas.models.examples.Example
import io.swagger.v3.oas.models.media.Content
import io.swagger.v3.oas.models.media.MediaType
import io.swagger.v3.oas.models.media.Schema
import io.swagger.v3.oas.models.parameters.Parameter
import kotlin.test.Test

class ParameterRuleTest {

    @Test
    fun `name and in are required`() {
        val rule = ParameterRule()
        assertThatResult(rule.validate(Parameter())).isFailure(
            ValidationFailure(RuleGroup.named("name", RuleGroup.Category.FIELD, "", RuleGroup.unknown()), "Was required but is not given"),
            ValidationFailure(RuleGroup.named("in", RuleGroup.Category.FIELD, "", RuleGroup.unknown()), "Was required but is not given"),
        )
    }

    @Test
    fun `when in 'path' then required must be true`() {
        val rule = ParameterRule()
        val fixture = Parameter().apply {
            name = "Parameter"
            `in` = "path"
            required = false
        }
        assertThatResult(rule.validate(fixture)).isFailure(
            ValidationFailure(RuleGroup.named("required", RuleGroup.Category.FIELD, "", RuleGroup.unknown()), "Was supposed to be 'true' but is 'false'"),
        )
    }

    @Test
    fun `name succeeds`() {
        val rule = ParameterRule()
            .name { exactly("parameter") }

        assertThatResult(rule.validate(fixture)).isSuccess()
    }

    @Test
    fun `name fails`() {
        val rule = ParameterRule()
            .name { exactly("fail") }

        assertThatResult(rule.validate(fixture)).isFailure(
            ValidationFailure(
                RuleGroup.named("name", RuleGroup.Category.FIELD, "", RuleGroup.unknown()),
                "Was supposed to be 'fail' but is 'parameter'",
            ),
        )
    }

    @Test
    fun `in succeeds`() {
        val rule = ParameterRule()
            .`in` { exactly("path") }

        assertThatResult(rule.validate(fixture)).isSuccess()
    }

    @Test
    fun `in fails`() {
        val rule = ParameterRule()
            .`in` { exactly("fail") }

        assertThatResult(rule.validate(fixture)).isFailure(
            ValidationFailure(
                RuleGroup.named("in", RuleGroup.Category.FIELD, "", RuleGroup.unknown()),
                "Was supposed to be 'fail' but is 'path'",
            ),
        )
    }

    @Test
    fun `description succeeds`() {
        val rule = ParameterRule()
            .description { exactly("description") }

        assertThatResult(rule.validate(fixture)).isSuccess()
    }

    @Test
    fun `description fails`() {
        val rule = ParameterRule()
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
        val rule = ParameterRule()
            .required { isTrue() }

        assertThatResult(rule.validate(fixture)).isSuccess()
    }

    @Test
    fun `required fails`() {
        val rule = ParameterRule()
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
        val rule = ParameterRule()
            .deprecated { isTrue() }

        assertThatResult(rule.validate(fixture)).isSuccess()
    }

    @Test
    fun `deprecated fails`() {
        val rule = ParameterRule()
            .deprecated { isFalse() }

        assertThatResult(rule.validate(fixture)).isFailure(
            ValidationFailure(
                RuleGroup.named("deprecated", RuleGroup.Category.FIELD, "", RuleGroup.unknown()),
                "Was supposed to be 'false' but is 'true'",
            ),
        )
    }

    @Test
    fun `allowEmptyValue succeeds`() {
        val rule = ParameterRule()
            .allowEmptyValue { isTrue() }

        assertThatResult(rule.validate(fixture)).isSuccess()
    }

    @Test
    fun `allowEmptyValue fails`() {
        val rule = ParameterRule()
            .allowEmptyValue { isFalse() }

        assertThatResult(rule.validate(fixture)).isFailure(
            ValidationFailure(
                RuleGroup.named("allowEmptyValue", RuleGroup.Category.FIELD, "", RuleGroup.unknown()),
                "Was supposed to be 'false' but is 'true'",
            ),
        )
    }

    @Test
    fun `style succeeds`() {
        val rule = ParameterRule()
            .style { exactly(Parameter.StyleEnum.SIMPLE) }

        assertThatResult(rule.validate(fixture)).isSuccess()
    }

    @Test
    fun `style fails`() {
        val rule = ParameterRule()
            .style { exactly(Parameter.StyleEnum.LABEL) }

        assertThatResult(rule.validate(fixture)).isFailure(
            ValidationFailure(
                RuleGroup.named("style", RuleGroup.Category.FIELD, "", RuleGroup.unknown()),
                "Was supposed to be 'label' but is 'simple'",
            ),
        )
    }

    @Test
    fun `explode succeeds`() {
        val rule = ParameterRule()
            .explode { isTrue() }

        assertThatResult(rule.validate(fixture)).isSuccess()
    }

    @Test
    fun `explode fails`() {
        val rule = ParameterRule()
            .explode { isFalse() }

        assertThatResult(rule.validate(fixture)).isFailure(
            ValidationFailure(
                RuleGroup.named("explode", RuleGroup.Category.FIELD, "", RuleGroup.unknown()),
                "Was supposed to be 'false' but is 'true'",
            ),
        )
    }

    @Test
    fun `allowReserved succeeds`() {
        val rule = ParameterRule()
            .allowReserved { isTrue() }

        assertThatResult(rule.validate(fixture)).isSuccess()
    }

    @Test
    fun `allowReserved fails`() {
        val rule = ParameterRule()
            .allowReserved { isFalse() }

        assertThatResult(rule.validate(fixture)).isFailure(
            ValidationFailure(
                RuleGroup.named("allowReserved", RuleGroup.Category.FIELD, "", RuleGroup.unknown()),
                "Was supposed to be 'false' but is 'true'",
            ),
        )
    }

    @Test
    fun `schema succeeds`() {
        val rule = ParameterRule()
            .schema { exactly(Schema<String>()) }

        assertThatResult(rule.validate(fixture)).isSuccess()
    }

    @Test
    fun `schema fails`() {
        val rule = ParameterRule()
            .schema { exactly(Schema<String>().apply { title = "fail" }) }

        assertThatResult(rule.validate(fixture)).isFailure(
            RuleGroup.named("schema", RuleGroup.Category.GROUP, "", RuleGroup.unknown()),
        )
    }

    @Test
    fun `example succeeds`() {
        val rule = ParameterRule()
            .example { exactly("example") }

        assertThatResult(rule.validate(fixture)).isSuccess()
    }

    @Test
    fun `example fails`() {
        val rule = ParameterRule()
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
        val rule = ParameterRule()
            .examples { exactly(listOf("example" to Example())) }

        assertThatResult(rule.validate(fixture)).isSuccess()
    }

    @Test
    fun `examples fails`() {
        val rule = ParameterRule()
            .examples { exactly(listOf()) }

        assertThatResult(rule.validate(fixture)).isFailure(
            RuleGroup.named("examples", RuleGroup.Category.GROUP, "", RuleGroup.unknown()),
        )
    }

    @Test
    fun `content succeeds`() {
        val rule = ParameterRule()
            .content { exactly(emptyList()) }

        assertThatResult(rule.validate(fixture)).isSuccess()
    }

    @Test
    fun `content fails`() {
        val rule = ParameterRule()
            .content { exactly(listOf("fail" to MediaType())) }

        assertThatResult(rule.validate(fixture)).isFailure(
            RuleGroup.named("content", RuleGroup.Category.GROUP, "", RuleGroup.unknown()),
        )
    }

    companion object {
        val fixture = Parameter().apply {
            name = "parameter"
            `in` = "path"
            description = "description"
            required = true
            deprecated = true
            allowEmptyValue = true
            style = Parameter.StyleEnum.SIMPLE
            explode = true
            allowReserved = true
            schema = Schema<String>()
            example = "example"
            examples = mapOf("example" to Example())
            content = Content()
        }
    }
}
