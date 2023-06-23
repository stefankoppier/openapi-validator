package io.github.stefankoppier.openapi.validator.core.rules.openapi.collections

import io.github.stefankoppier.openapi.validator.core.assertThatResult
import io.swagger.v3.oas.models.tags.Tag
import kotlin.test.Test

class TagsRuleTest {

    @Test
    fun `all succeeds`() {
        val rule = TagsRule().all {
            name { exactly("pet") }
        }

        assertThatResult(rule.validate(fixture)).isSuccess()
    }

    @Test
    fun `all fails`() {
        val rule = TagsRule().all {
            name { exactly("dog") }
        }

        assertThatResult(rule.validate(fixture)).isFailure()
    }

    @Test
    fun `tag succeeds`() {
        val rule = TagsRule().tag(named = "pet") {
            required()
        }

        assertThatResult(rule.validate(fixture)).isSuccess()
    }

    @Test
    fun `tag fails`() {
        val rule = TagsRule().tag(named = "dog") {
            required()
        }

        val result = rule.validate(fixture)
        assertThatResult(result).isFailure()
    }

    @Test
    fun `tag not found`() {
        val rule = TagsRule().tag(named = "not found") {
            required()
        }

        val result = rule.validate(fixture)
        assertThatResult(result).isFailure()
    }

    companion object {
        private val fixture = listOf(
            Tag().apply {
                name = "pet"
            },
        )
    }
}
