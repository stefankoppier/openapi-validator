package io.github.stefankoppier.openapi.validator.core.rules.primitives

import io.github.stefankoppier.openapi.validator.core.ValidationFailure
import io.github.stefankoppier.openapi.validator.core.assertThatResult
import io.github.stefankoppier.openapi.validator.core.rules.RuleGroup
import kotlin.test.Test

class StringRuleTest {

    @Test
    fun `matches succeeds`() {
        val rule = string {
            matches(Regex("[0-9]+"))
        }

        assertThatResult(rule.validate("01")).isSuccess()
    }

    @Test
    fun `matches fails`() {
        val rule = string {
            matches(Regex("[0-9]+"))
        }

        assertThatResult(rule.validate("a")).isFailure(
            ValidationFailure(
                RuleGroup.named("rule", "", RuleGroup.Category.FIELD),
                "Was supposed to match '[0-9]+' but is 'a'",
            ),
        )
    }

    @Test
    fun `lowercase succeeds`() {
        val rule = string {
            lowercase()
        }

        assertThatResult(rule.validate("string")).isSuccess()
    }

    @Test
    fun `lowercase of non-alphabetic succeeds`() {
        val rule = string {
            lowercase()
        }

        assertThatResult(rule.validate("a b")).isSuccess()
    }

    @Test
    fun `lowercase fails`() {
        val rule = string {
            lowercase()
        }

        assertThatResult(rule.validate("STRING")).isFailure(
            ValidationFailure(
                RuleGroup.named("rule", "", RuleGroup.Category.FIELD),
                "Was supposed to be lowercase but is 'STRING'",
            ),
        )
    }

    @Test
    fun `uppercase succeeds`() {
        val rule = string {
            uppercase()
        }

        assertThatResult(rule.validate("STRING")).isSuccess()
    }

    @Test
    fun `uppercase of non-alphabetic succeeds`() {
        val rule = string {
            uppercase()
        }

        assertThatResult(rule.validate("A B")).isSuccess()
    }

    @Test
    fun `uppercase fails`() {
        val rule = string {
            uppercase()
        }

        assertThatResult(rule.validate("string")).isFailure(
            ValidationFailure(
                RuleGroup.named("rule", "", RuleGroup.Category.FIELD),
                "Was supposed to be uppercase but is 'string'",
            ),
        )
    }

    @Test
    fun `alpha succeeds`() {
        val rule = string {
            alpha()
        }

        assertThatResult(rule.validate("string")).isSuccess()
    }

    @Test
    fun `alpha fails`() {
        val rule = string {
            alpha()
        }

        assertThatResult(rule.validate("1")).isFailure(
            ValidationFailure(
                RuleGroup.named("rule", "", RuleGroup.Category.FIELD),
                "Was supposed to be alphabetic but is '1'",
            ),
        )
    }

    @Test
    fun `numeric succeeds`() {
        val rule = string {
            numeric()
        }

        assertThatResult(rule.validate("1")).isSuccess()
    }

    @Test
    fun `numeric fails`() {
        val rule = string {
            numeric()
        }

        assertThatResult(rule.validate("string")).isFailure(
            ValidationFailure(
                RuleGroup.named("rule", "", RuleGroup.Category.FIELD),
                "Was supposed to be numeric but is 'string'",
            ),
        )
    }

    @Test
    fun `alphanumeric succeeds`() {
        val rule = string {
            alphanumeric()
        }

        assertThatResult(rule.validate("string1")).isSuccess()
    }

    @Test
    fun `alphanumeric fails`() {
        val rule = string {
            alphanumeric()
        }

        assertThatResult(rule.validate("string_1")).isFailure(
            ValidationFailure(
                RuleGroup.named("rule", "", RuleGroup.Category.FIELD),
                "Was supposed to be alphanumeric but is 'string_1'",
            ),
        )
    }

    @Test
    fun `kebabcase succeeds`() {
        val rule = string {
            kebabcase()
        }

        assertThatResult(rule.validate("kebab-case")).isSuccess()
    }

    @Test
    fun `kebabcase fails`() {
        val rule = string {
            kebabcase()
        }

        assertThatResult(rule.validate("kebab case")).isFailure(
            ValidationFailure(
                RuleGroup.named("rule", "", RuleGroup.Category.FIELD),
                "Was supposed to be kebab case but is 'kebab case'",
            ),
        )
    }

    @Test
    fun `snakecase succeeds`() {
        val rule = string {
            snakecase()
        }

        assertThatResult(rule.validate("snake_case")).isSuccess()
    }

    @Test
    fun `snakecase fails`() {
        val rule = string {
            snakecase()
        }

        assertThatResult(rule.validate("snake case")).isFailure(
            ValidationFailure(
                RuleGroup.named("rule", "", RuleGroup.Category.FIELD),
                "Was supposed to be snake case but is 'snake case'",
            ),
        )
    }

    companion object {
        private fun string(rule: StringRule.() -> StringRule): StringRule {
            return rule(StringRule(RuleGroup.named("rule", "", RuleGroup.Category.FIELD)))
        }
    }
}
