package io.github.stefankoppier.openapi.validator.core.rules.primitives

import io.github.stefankoppier.openapi.validator.core.ValidationFailure
import io.github.stefankoppier.openapi.validator.core.assertThatResult
import io.github.stefankoppier.openapi.validator.core.rules.RuleGroup
import kotlin.test.Test

class URLRuleTest {

    @Test
    fun `exactly of string succeeds`() {
        val rule = url {
            exactly(URL)
        }

        assertThatResult(rule.validate(URL)).isSuccess()
    }

    @Test
    fun `exactly of string fails`() {
        val rule = url {
            exactly(URL)
        }

        assertThatResult(rule.validate("https://localhost")).isFailure(
            ValidationFailure(
                RuleGroup.named("rule", RuleGroup.Category.FIELD),
                "Was supposed to be '$URL' but is 'https://localhost'",
            ),
        )
    }

    @Test
    fun `exactly of string non-url format fails`() {
        val rule = url {
            exactly(URL)
        }

        assertThatResult(rule.validate("fail")).isFailure(
            ValidationFailure(
                RuleGroup.named("rule", RuleGroup.Category.FIELD),
                "Was required to be in the form of a URL but was 'fail'",
            ),
        )
    }

    companion object {
        private const val URL = "http://localhost"

        private fun url(rule: URLRule.() -> URLRule): URLRule {
            return rule(URLRule(RuleGroup.named("rule", RuleGroup.Category.FIELD)))
        }
    }
}
