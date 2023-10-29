package io.github.stefankoppier.openapi.validator.core.rules

import io.github.stefankoppier.openapi.validator.core.ValidationFailure
import io.github.stefankoppier.openapi.validator.core.assertThatResult
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

        assertThatResult(rule.validate("string")).isSuccess()
    }

    @Test
    fun `given only affects inner rule fails`() {
        val rule = string {
            lowercase()
            given({ it == "string" }) {
                uppercase()
            }
        }

        assertThatResult(rule.validate("string")).isFailure(
            ValidationFailure(
                RuleGroup.named("rule", RuleGroup.Category.FIELD),
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

        assertThatResult(rule.validate("string")).isFailure(
            ValidationFailure(
                RuleGroup.named("rule", RuleGroup.Category.FIELD),
                "Was supposed to be uppercase but is 'string'",
            ),

            ValidationFailure(
                RuleGroup.named("rule", RuleGroup.Category.FIELD),
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

        assertThatResult(rule.validate("string")).isSuccess()
    }

    @Test
    fun `since in past fails`() {
        val rule = string {
            since(LocalDate.MIN) {
                uppercase()
            }
        }

        assertThatResult(rule.validate("string")).isFailure(
            ValidationFailure(
                RuleGroup.named("rule", RuleGroup.Category.FIELD),
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

        assertThatResult(rule.validate("string")).isSuccess()
    }

    @Test
    fun `optional on null value succeeds`() {
        val rule = string {
            optional({ it }) {
                uppercase()
            }
        }

        assertThatResult(rule.validate(null)).isSuccess()
    }

    @Test
    fun `optional on non-null value succeeds`() {
        val rule = string {
            optional({ it }) {
                lowercase()
            }
        }

        assertThatResult(rule.validate("string")).isSuccess()
    }

    @Test
    fun `optional on non-null value fails`() {
        val rule = string {
            optional({ it }) {
                uppercase()
            }
        }

        assertThatResult(rule.validate("string")).isFailure(
            ValidationFailure(
                RuleGroup.named("rule", RuleGroup.Category.FIELD),
                "Was supposed to be uppercase but is 'string'",
            ),
        )
    }

    @Test
    fun `required on non-null value succeeds`() {
        val rule = string {
            required()
        }

        assertThatResult(rule.validate("string")).isSuccess()
    }

    @Test
    fun `required on null value fails`() {
        val rule = string {
            required()
        }

        assertThatResult(rule.validate(null)).isFailure(
            ValidationFailure(
                RuleGroup.named("rule", RuleGroup.Category.FIELD),
                "Was required but is not given",
            ),
        )
    }

    @Test
    fun `exactly on value succeeds`() {
        val rule = string {
            exactly("string")
        }

        assertThatResult(rule.validate("string")).isSuccess()
    }

    @Test
    fun `exactly on value fails`() {
        val rule = string {
            exactly("string")
        }

        assertThatResult(rule.validate("fail")).isFailure(
            ValidationFailure(
                RuleGroup.named("rule", RuleGroup.Category.FIELD),
                "Was supposed to be 'string' but is 'fail'",
            ),
        )
    }

    @Test
    fun `not succeeds`() {
        val rule = string {
            not { exactly("string") }
        }

        assertThatResult(rule.validate("fail")).isSuccess()
    }

    @Test
    fun `not fails`() {
        val rule = string {
            not { exactly("string") }
        }

        assertThatResult(rule.validate("string")).isFailure()
    }

    @Test
    fun `not not succeeds`() {
        val rule = string {
            not { not { exactly("string") } }
        }

        assertThatResult(rule.validate("string")).isSuccess()
    }

    companion object {
        private fun string(rule: StringRule.() -> StringRule): StringRule {
            return rule(StringRule(RuleGroup.named("rule", RuleGroup.Category.FIELD)))
        }
    }
}
