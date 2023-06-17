package io.github.stefankoppier.openapi.validator.core.rules.openapi.collections

import io.swagger.v3.oas.models.tags.Tag
import org.assertj.core.api.Assertions.assertThat
import kotlin.test.Test


class TagsRuleTest {

    @Test
    fun `all succeeds`() {
        val rule = TagsRule().all {
            name { exactly("pet") }
        }

        val result = rule.validate(tags)
        assertThat(result.isSuccess).isTrue()
    }

    @Test
    fun `all fails`() {
        val rule = TagsRule().all {
            name { exactly("dog") }
        }

        val result = rule.validate(tags)
        assertThat(result.isFailure).isTrue()
    }

    @Test
    fun `tag succeeds`() {
        val rule = TagsRule().tag(named = "pet") {
            required()
        }

        val result = rule.validate(tags)
        assertThat(result.isSuccess).isTrue()
    }

    @Test
    fun `tag fails`() {
        val rule = TagsRule().tag(named = "dog") {
            required()
        }

        val result = rule.validate(tags)
        assertThat(result.isFailure).isTrue()
    }

    companion object {
        private val tags = listOf(
            Tag().apply {
                name = "pet"
            }
        )
    }
}