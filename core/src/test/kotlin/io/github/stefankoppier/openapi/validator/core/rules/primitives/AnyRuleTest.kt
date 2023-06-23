package io.github.stefankoppier.openapi.validator.core.rules.primitives

import io.github.stefankoppier.openapi.validator.core.ValidationFailure
import io.github.stefankoppier.openapi.validator.core.assertThatResult
import io.github.stefankoppier.openapi.validator.core.rules.RuleGroup
import kotlin.test.Test

class AnyRuleTest {

    @Test
    fun `instanceof of same type succeeds`() {
        val rule = any {
            instanceof(String::class)
        }

        assertThatResult(rule.validate("string")).isSuccess()
    }

    @Test
    fun `instanceof of other type fails`() {
        val rule = any {
            instanceof(String::class)
        }

        assertThatResult(rule.validate(true)).isFailure(
            ValidationFailure(
                RuleGroup.named("rule", "", RuleGroup.Category.FIELD),
                "Was supposed to be of type 'String' but is of type 'Boolean'",
            ),
        )
    }

    companion object {
        private fun any(rule: AnyRule.() -> AnyRule): AnyRule {
            return rule(AnyRule(RuleGroup.named("rule", "", RuleGroup.Category.FIELD)))
        }
    }
}
