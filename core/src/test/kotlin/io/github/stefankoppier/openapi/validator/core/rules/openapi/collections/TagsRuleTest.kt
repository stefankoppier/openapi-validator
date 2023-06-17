package io.github.stefankoppier.openapi.validator.core.rules.openapi.collections

import io.swagger.v3.oas.models.tags.Tag
import io.github.stefankoppier.openapi.validator.core.assertThat
import kotlin.test.Test

class TagsRuleTest {

    @Test
    fun `all succeeds`() {
        val rule = TagsRule().all {
            name { exactly("pet") }
        }

        assertThat(rule.validate(fixture)).isSuccess()
    }

    @Test
    fun `all fails`() {
        val rule = TagsRule().all {
            name { exactly("dog") }
        }

        assertThat(rule.validate(fixture)).isFailure()
    }

    @Test
    fun `tag succeeds`() {
        val rule = TagsRule().tag(named = "pet") {
            required()
        }

        assertThat(rule.validate(fixture)).isSuccess()
    }

    @Test
    fun `tag fails`() {
        val rule = TagsRule().tag(named = "dog") {
            required()
        }

        val result = rule.validate(fixture)
        assertThat(result).isFailure()
    }

    companion object {
        private val fixture = listOf(
            Tag().apply {
                name = "pet"
            }
        )
    }
}