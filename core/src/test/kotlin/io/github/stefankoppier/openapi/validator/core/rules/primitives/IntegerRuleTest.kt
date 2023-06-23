package io.github.stefankoppier.openapi.validator.core.rules.primitives

import io.github.stefankoppier.openapi.validator.core.ValidationFailure
import io.github.stefankoppier.openapi.validator.core.assertThatResult
import io.github.stefankoppier.openapi.validator.core.rules.RuleGroup
import kotlin.test.Test

class IntegerRuleTest {

    @Test
    fun `min succeeds`() {
        val rule = integer {
            min(1)
        }

        assertThatResult(rule.validate(1)).isSuccess()
    }

    @Test
    fun `min fails`() {
        val rule = integer {
            min(1)
        }

        assertThatResult(rule.validate(0)).isFailure(
            ValidationFailure(
                RuleGroup.named("rule", RuleGroup.Category.FIELD),
                "Was supposed to be greater than or equal to '1' but is '0'",
            ),
        )
    }

    @Test
    fun `max succeeds`() {
        val rule = integer {
            max(1)
        }

        assertThatResult(rule.validate(1)).isSuccess()
    }

    @Test
    fun `max fails`() {
        val rule = integer {
            max(1)
        }

        assertThatResult(rule.validate(2)).isFailure(
            ValidationFailure(
                RuleGroup.named("rule", RuleGroup.Category.FIELD),
                "Was supposed to be less than or equal to '1' but is '2'",
            ),
        )
    }

    @Test
    fun `between succeeds`() {
        val rule = integer {
            between(1, 10)
        }

        assertThatResult(rule.validate(1)).isSuccess()
    }

    @Test
    fun `between under min fails`() {
        val rule = integer {
            between(1, 10)
        }

        assertThatResult(rule.validate(0)).isFailure(
            ValidationFailure(
                RuleGroup.named("rule", RuleGroup.Category.FIELD),
                "Was supposed to be between '1' and '10' but is '0'",
            ),
        )
    }

    @Test
    fun `between over max fails`() {
        val rule = integer {
            between(1, 10)
        }

        assertThatResult(rule.validate(11)).isFailure(
            ValidationFailure(
                RuleGroup.named("rule", RuleGroup.Category.FIELD),
                "Was supposed to be between '1' and '10' but is '11'",
            ),
        )
    }

    companion object {
        private fun integer(rule: IntegerRule.() -> IntegerRule): IntegerRule {
            return rule(IntegerRule(RuleGroup.named("rule", RuleGroup.Category.FIELD)))
        }
    }
}
