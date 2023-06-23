package io.github.stefankoppier.openapi.validator.core.rules.primitives

import io.github.stefankoppier.openapi.validator.core.ValidationFailure
import io.github.stefankoppier.openapi.validator.core.assertThatResult
import io.github.stefankoppier.openapi.validator.core.rules.RuleGroup
import kotlin.test.Test

class EnumRuleTest {

    @Test
    fun `either succeeds`() {
        val rule = enum {
            either(Color.RED, Color.GREEN)
        }

        assertThatResult(rule.validate(Color.RED)).isSuccess()
    }

    @Test
    fun `either fails`() {
        val rule = enum {
            either(Color.RED, Color.GREEN)
        }

        assertThatResult(rule.validate(Color.BLUE)).isFailure(
            ValidationFailure(
                RuleGroup.named("rule", RuleGroup.Category.FIELD),
                "Was supposed to be one of 'RED, GREEN' but is 'BLUE'",
            ),
        )
    }

    companion object {
        private enum class Color {
            RED,
            GREEN,
            BLUE,
        }

        private fun enum(rule: EnumRule<Color>.() -> EnumRule<Color>): EnumRule<Color> {
            return rule(EnumRule(RuleGroup.named("rule", RuleGroup.Category.FIELD)))
        }
    }
}
