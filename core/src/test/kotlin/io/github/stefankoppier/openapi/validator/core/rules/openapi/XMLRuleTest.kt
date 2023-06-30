package io.github.stefankoppier.openapi.validator.core.rules.openapi

import io.github.stefankoppier.openapi.validator.core.ValidationFailure
import io.github.stefankoppier.openapi.validator.core.assertThatResult
import io.github.stefankoppier.openapi.validator.core.rules.RuleGroup
import io.swagger.v3.oas.models.media.XML
import kotlin.test.Test

class XMLRuleTest {

    @Test
    fun `name succeeds`() {
        val rule = XMLRule()
            .name { exactly("name") }

        assertThatResult(rule.validate(fixture)).isSuccess()
    }

    @Test
    fun `name fails`() {
        val rule = XMLRule()
            .name { exactly("fail") }

        assertThatResult(rule.validate(fixture)).isFailure(
            ValidationFailure(
                RuleGroup.named("name", RuleGroup.Category.FIELD, "", RuleGroup.unknown()),
                "Was supposed to be 'fail' but is 'name'",
            ),
        )
    }

    @Test
    fun `namespace succeeds`() {
        val rule = XMLRule()
            .namespace { exactly("namespace") }

        assertThatResult(rule.validate(fixture)).isSuccess()
    }

    @Test
    fun `namespace fails`() {
        val rule = XMLRule()
            .namespace { exactly("fail") }

        assertThatResult(rule.validate(fixture)).isFailure(
            ValidationFailure(
                RuleGroup.named("namespace", RuleGroup.Category.FIELD, "", RuleGroup.unknown()),
                "Was supposed to be 'fail' but is 'namespace'",
            ),
        )
    }

    @Test
    fun `prefix succeeds`() {
        val rule = XMLRule()
            .prefix { exactly("prefix") }

        assertThatResult(rule.validate(fixture)).isSuccess()
    }

    @Test
    fun `prefix fails`() {
        val rule = XMLRule()
            .prefix { exactly("fail") }

        assertThatResult(rule.validate(fixture)).isFailure(
            ValidationFailure(
                RuleGroup.named("prefix", RuleGroup.Category.FIELD, "", RuleGroup.unknown()),
                "Was supposed to be 'fail' but is 'prefix'",
            ),
        )
    }

    @Test
    fun `attribute succeeds`() {
        val rule = XMLRule()
            .attribute { isTrue() }

        assertThatResult(rule.validate(fixture)).isSuccess()
    }

    @Test
    fun `attribute fails`() {
        val rule = XMLRule()
            .attribute { isFalse() }

        assertThatResult(rule.validate(fixture)).isFailure(
            ValidationFailure(
                RuleGroup.named("attribute", RuleGroup.Category.FIELD, "", RuleGroup.unknown()),
                "Was supposed to be 'false' but is 'true'",
            ),
        )
    }

    @Test
    fun `wrapped succeeds`() {
        val rule = XMLRule()
            .wrapped { isTrue() }

        assertThatResult(rule.validate(fixture)).isSuccess()
    }

    @Test
    fun `wrapped fails`() {
        val rule = XMLRule()
            .wrapped { isFalse() }

        assertThatResult(rule.validate(fixture)).isFailure(
            ValidationFailure(
                RuleGroup.named("wrapped", RuleGroup.Category.FIELD, "", RuleGroup.unknown()),
                "Was supposed to be 'false' but is 'true'",
            ),
        )
    }

    companion object {
        val fixture = XML().apply {
            name = "name"
            namespace = "namespace"
            prefix = "prefix"
            attribute = true
            wrapped = true
        }
    }
}
