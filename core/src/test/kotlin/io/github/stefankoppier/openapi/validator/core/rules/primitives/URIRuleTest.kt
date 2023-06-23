package io.github.stefankoppier.openapi.validator.core.rules.primitives

import io.github.stefankoppier.openapi.validator.core.ValidationFailure
import io.github.stefankoppier.openapi.validator.core.assertThatResult
import io.github.stefankoppier.openapi.validator.core.rules.RuleGroup
import kotlin.test.Test

class URIRuleTest {

    @Test
    fun `exactly of string succeeds`() {
        val rule = uri {
            exactly(URI)
        }

        assertThatResult(rule.validate(URI)).isSuccess()
    }

    @Test
    fun `exactly of string fails`() {
        val rule = uri {
            exactly(URI)
        }

        assertThatResult(rule.validate("https://localhost")).isFailure(
            ValidationFailure(
                RuleGroup.named("rule", RuleGroup.Category.FIELD),
                "Was supposed to be '$URI' but is 'https://localhost'",
            ),
        )
    }

    @Test
    fun `exactly of string non-uri format fails`() {
        val rule = uri {
            exactly(URI)
        }

        assertThatResult(rule.validate("fail")).isFailure(
            ValidationFailure(
                RuleGroup.named("rule", RuleGroup.Category.FIELD),
                "Was supposed to be '$URI' but is 'fail'",
            ),
        )
    }

    companion object {
        private const val URI = "http://localhost"

        private fun uri(rule: URIRule.() -> URIRule): URIRule {
            return rule(URIRule(RuleGroup.named("rule", RuleGroup.Category.FIELD)))
        }
    }
}
