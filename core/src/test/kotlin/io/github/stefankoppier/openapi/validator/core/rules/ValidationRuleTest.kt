package io.github.stefankoppier.openapi.validator.core.rules

import io.github.stefankoppier.openapi.validator.core.ValidationFailure
import io.github.stefankoppier.openapi.validator.core.assertThat
import io.github.stefankoppier.openapi.validator.core.rules.primitives.StringRule
import java.time.LocalDate
import kotlin.test.Test

class ValidationRuleTest {

    @Test
    fun `given only affects inner rule succeeds`() {
        val rule = string {
            lowercase()
            given({ it == "string" }) {
                exactly("string")
            }
        }

        assertThat(rule.validate("string")).isSuccess()
    }

    @Test
    fun `given only affects inner rule fails`() {
        val rule = string {
            lowercase()
            given({ it == "string" }) {
                uppercase()
            }
        }

        assertThat(rule.validate("string")).isFailure(
            ValidationFailure(
                RuleGroup.named("rule", "", RuleGroup.Category.FIELD),
                "Was supposed to be uppercase but is 'string'",
            ),
        )
    }

    @Test
    fun `given fails inner rule succeeds`() {
        val rule = string {
            uppercase()
            given({ it == "string" }) {
                exactly("fail")
            }
        }

        assertThat(rule.validate("string")).isFailure(
            ValidationFailure(
                RuleGroup.named("rule", "", RuleGroup.Category.FIELD),
                "Was supposed to be uppercase but is 'string'",
            ),

            ValidationFailure(
                RuleGroup.named("rule", "", RuleGroup.Category.FIELD),
                "Was supposed to be 'fail' but is 'string'",
            ),
        )
    }

    @Test
    fun `since in past succeeds`() {
        val rule = string {
            since(LocalDate.MIN) {
                lowercase()
            }
        }

        assertThat(rule.validate("string")).isSuccess()
    }

    @Test
    fun `since in past fails`() {
        val rule = string {
            since(LocalDate.MIN) {
                uppercase()
            }
        }

        assertThat(rule.validate("string")).isFailure(
            ValidationFailure(
                RuleGroup.named("rule", "", RuleGroup.Category.FIELD),
                "Was supposed to be uppercase but is 'string'",
            ),
        )
    }

    @Test
    fun `since in future succeeds`() {
        val rule = string {
            since(LocalDate.MAX) {
                uppercase()
            }
        }

        assertThat(rule.validate("string")).isSuccess()
    }

    @Test
    fun `optional on null value succeeds`() {
        val rule = string {
            optional({ it }) {
                uppercase()
            }
        }

        assertThat(rule.validate(null)).isSuccess()
    }

    @Test
    fun `optional on non-null value succeeds`() {
        val rule = string {
            optional({ it }) {
                lowercase()
            }
        }

        assertThat(rule.validate("string")).isSuccess()
    }

    @Test
    fun `optional on non-null value fails`() {
        val rule = string {
            optional({ it }) {
                uppercase()
            }
        }

        assertThat(rule.validate("string")).isFailure(
            ValidationFailure(
                RuleGroup.named("rule", "", RuleGroup.Category.FIELD),
                "Was supposed to be uppercase but is 'string'",
            ),
        )
    }

    @Test
    fun `required on non-null value succeeds`() {
        val rule = string {
            required()
        }

        assertThat(rule.validate("string")).isSuccess()
    }

    @Test
    fun `required on null value fails`() {
        val rule = string {
            required()
        }

        assertThat(rule.validate(null)).isFailure(
            ValidationFailure(
                RuleGroup.named("rule", "", RuleGroup.Category.FIELD),
                "Was required but is not given",
            ),
        )
    }

    @Test
    fun `exactly on value succeeds`() {
        val rule = string {
            exactly("string")
        }

        assertThat(rule.validate("string")).isSuccess()
    }

    @Test
    fun `exactly on value fails`() {
        val rule = string {
            exactly("string")
        }

        assertThat(rule.validate("fail")).isFailure(
            ValidationFailure(
                RuleGroup.named("rule", "", RuleGroup.Category.FIELD),
                "Was supposed to be 'string' but is 'fail'",
            ),
        )
    }

    companion object {
        private fun string(rule: StringRule.() -> StringRule): StringRule {
            return rule(StringRule(RuleGroup.named("rule", "", RuleGroup.Category.FIELD)))
        }
    }
}
