package io.github.stefankoppier.openapi.validator.core.rules.openapi.collections

import io.swagger.v3.oas.models.PathItem
import io.github.stefankoppier.openapi.validator.core.assertThat
import kotlin.test.Test


class PathsRuleTest {

    @Test
    fun `all succeeds`() {
        val rule = PathsRule().all {
            summary { exactly("Summary") }
        }

        assertThat(rule.validate(fixture)).isSuccess()
    }

    @Test
    fun `all fails`() {
        val rule = PathsRule().all {
            summary { exactly("Invalid") }
        }

        assertThat(rule.validate(fixture)).isFailure()
    }

    @Test
    fun `path succeeds`() {
        val rule = PathsRule().path(named = "/find") {
            summary { exactly("Summary") }
        }

        assertThat(rule.validate(fixture)).isSuccess()
    }

    @Test
    fun `path fails`() {
        val rule = PathsRule().path(named = "/find") {
            summary { exactly("Invalid") }
        }

        assertThat(rule.validate(fixture)).isFailure()
    }

    @Test
    fun `path not found`() {
        val rule = PathsRule().path(named = "not found") {
            summary { exactly("Invalid") }
        }

        assertThat(rule.validate(fixture)).isFailure()
    }

    companion object {
        private val fixture = mapOf(
            "/find" to PathItem().apply {
                summary = "Summary"
            }
        ).toList()
    }
}