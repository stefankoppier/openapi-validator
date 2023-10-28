package io.github.stefankoppier.openapi.validator.core.rules.openapi.collections

import io.github.stefankoppier.openapi.validator.core.assertThatResult
import io.swagger.v3.oas.models.links.Link
import kotlin.test.Test

class LinksRuleTest {

    @Test
    fun `all succeeds`() {
        val rule = LinksRule().all {
            description { exactly("description") }
        }

        assertThatResult(rule.validate(fixture)).isSuccess()
    }

    @Test
    fun `all fails`() {
        val rule = LinksRule().all {
            description { exactly("fail") }
        }

        assertThatResult(rule.validate(fixture)).isFailure()
    }

    @Test
    fun `tag succeeds`() {
        val rule = LinksRule().link(named = "link") {
            required()
        }

        assertThatResult(rule.validate(fixture)).isSuccess()
    }

    @Test
    fun `link fails`() {
        val rule = LinksRule().link(named = "link") {
            required()
            description { exactly("fail") }
        }

        val result = rule.validate(fixture)
        assertThatResult(result).isFailure()
    }

    @Test
    fun `link not found`() {
        val rule = LinksRule().link(named = "not found") {
            required()
        }

        val result = rule.validate(fixture)
        assertThatResult(result).isFailure()
    }

    companion object {
        private val fixture = listOf(
            "link" to Link().apply {
                description = "description"
            },
        )
    }
}
