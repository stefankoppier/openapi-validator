package io.github.stefankoppier.openapi.validator.core.rules.openapi

import io.github.stefankoppier.openapi.validator.core.ValidationFailure
import io.github.stefankoppier.openapi.validator.core.assertThatResult
import io.github.stefankoppier.openapi.validator.core.rules.RuleGroup
import io.swagger.v3.oas.models.headers.Header
import io.swagger.v3.oas.models.media.Encoding
import kotlin.test.Test

class EncodingRuleTest {

    @Test
    fun `contentType succeeds`() {
        val rule = EncodingRule()
            .contentType { exactly("application/json") }

        assertThatResult(rule.validate(fixture)).isSuccess()
    }

    @Test
    fun `contentType fails`() {
        val rule = EncodingRule()
            .contentType { exactly("application/xml") }

        assertThatResult(rule.validate(fixture)).isFailure(
            ValidationFailure(
                RuleGroup.named("contentType", RuleGroup.Category.FIELD, "", RuleGroup.unknown()),
                "Was supposed to be 'application/xml' but is 'application/json'",
            ),
        )
    }

    @Test
    fun `headers succeeds`() {
        val rule = EncodingRule()
            .headers { exactly(listOf("Header" to Header())) }

        assertThatResult(rule.validate(fixture)).isSuccess()
    }

    @Test
    fun `headers fails`() {
        val rule = EncodingRule()
            .headers { exactly(listOf("Fail" to Header())) }

        assertThatResult(rule.validate(fixture)).isFailure(
            RuleGroup.named("headers", RuleGroup.Category.GROUP, "", RuleGroup.unknown()),
        )
    }

    @Test
    fun `style succeeds`() {
        val rule = EncodingRule()
            .style { exactly(Encoding.StyleEnum.FORM) }

        assertThatResult(rule.validate(fixture)).isSuccess()
    }

    @Test
    fun `style fails`() {
        val rule = EncodingRule()
            .style { exactly(Encoding.StyleEnum.SPACE_DELIMITED) }

        assertThatResult(rule.validate(fixture)).isFailure(
            ValidationFailure(
                RuleGroup.named("style", RuleGroup.Category.FIELD, "", RuleGroup.unknown()),
                "Was supposed to be 'spaceDelimited' but is 'form'",
            ),
        )
    }

    @Test
    fun `explode succeeds`() {
        val rule = EncodingRule()
            .explode { isTrue() }

        assertThatResult(rule.validate(fixture)).isSuccess()
    }

    @Test
    fun `explode fails`() {
        val rule = EncodingRule()
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
        val rule = EncodingRule()
            .allowReserved { isTrue() }

        assertThatResult(rule.validate(fixture)).isSuccess()
    }

    @Test
    fun `allowReserved fails`() {
        val rule = EncodingRule()
            .allowReserved { isFalse() }

        assertThatResult(rule.validate(fixture)).isFailure(
            ValidationFailure(
                RuleGroup.named("allowReserved", RuleGroup.Category.FIELD, "", RuleGroup.unknown()),
                "Was supposed to be 'false' but is 'true'",
            ),
        )
    }

    companion object {
        val fixture = Encoding().apply {
            contentType = "application/json"
            headers = mapOf("Header" to Header())
            style = Encoding.StyleEnum.FORM
            explode = true
            allowReserved = true
        }
    }
}
