package io.github.stefankoppier.openapi.validator.core.rules.primitives

import io.github.stefankoppier.openapi.validator.core.ValidationFailure
import io.github.stefankoppier.openapi.validator.core.assertThat
import io.github.stefankoppier.openapi.validator.core.rules.RuleGroup
import java.math.BigDecimal
import kotlin.test.Test

class BigDecimalRuleTest {

    @Test
    fun `min succeeds`() {
        val rule = bigDecimal {
            min(BigDecimal.ONE)
        }

        assertThat(rule.validate(BigDecimal(1.0))).isSuccess()
    }

    @Test
    fun `min fails`() {
        val rule = bigDecimal {
            min(BigDecimal.ONE)
        }

        assertThat(rule.validate(BigDecimal("0.99"))).isFailure(
            ValidationFailure(RuleGroup.named("rule", "", RuleGroup.Category.FIELD),
                "Was supposed to be greater than or equal to '1' but is '0.99'")
        )
    }

    @Test
    fun `max succeeds`() {
        val rule = bigDecimal {
            max(BigDecimal.ONE)
        }

        assertThat(rule.validate(BigDecimal.ONE)).isSuccess()
    }

    @Test
    fun `max fails`() {
        val rule = bigDecimal {
            max(BigDecimal.ONE)
        }

        assertThat(rule.validate(BigDecimal("1.01"))).isFailure(
            ValidationFailure(RuleGroup.named("rule", "", RuleGroup.Category.FIELD),
                "Was supposed to be less than or equal to '1' but is '1.01'")
        )
    }

    @Test
    fun `between succeeds`() {
        val rule = bigDecimal {
            between(BigDecimal.ONE, BigDecimal.TEN)
        }

        assertThat(rule.validate(BigDecimal.ONE)).isSuccess()
    }

    @Test
    fun `between under min fails`() {
        val rule = bigDecimal {
            between(BigDecimal.ONE, BigDecimal.TEN)
        }

        assertThat(rule.validate(BigDecimal("0.99"))).isFailure(
            ValidationFailure(RuleGroup.named("rule", "", RuleGroup.Category.FIELD),
                "Was supposed to be between '1' and '10' but is '0.99'")
        )
    }

    @Test
    fun `between over max fails`() {
        val rule = bigDecimal {
            between(BigDecimal.ONE, BigDecimal.TEN)
        }

        assertThat(rule.validate(BigDecimal("10.01"))).isFailure(
            ValidationFailure(RuleGroup.named("rule", "", RuleGroup.Category.FIELD),
                "Was supposed to be between '1' and '10' but is '10.01'")
        )
    }

    companion object {
        private fun bigDecimal(rule: BigDecimalRule.() -> BigDecimalRule): BigDecimalRule {
            return rule(BigDecimalRule(RuleGroup.named("rule", "", RuleGroup.Category.FIELD)))
        }
    }
}