package io.github.stefankoppier.openapi.validator.core.rules.openapi.collections

import io.swagger.v3.oas.models.PathItem
import org.assertj.core.api.Assertions.assertThat
import kotlin.test.Test


class PathsRuleTest {

    @Test
    fun `all succeeds`() {
        val rule = PathsRule().all {
            summary { exactly("Summary") }
        }

        val result = rule.validate(paths.toList())
        assertThat(result.isSuccess).isTrue()
    }

    @Test
    fun `all fails`() {
        val rule = PathsRule().all {
            summary { exactly("Invalid") }
        }

        val result = rule.validate(paths.toList())
        assertThat(result.isFailure).isTrue()
    }

    @Test
    fun `path succeeds`() {
        val rule = PathsRule().path(named = "/find") {
            summary { exactly("Summary") }
        }

        val result = rule.validate(paths.toList())
        assertThat(result.isSuccess).isTrue()
    }

    @Test
    fun `path fails`() {
        val rule = PathsRule().path(named = "/find") {
            summary { exactly("Invalid") }
        }

        val result = rule.validate(paths.toList())
        assertThat(result.isFailure).isTrue()
    }

    companion object {
        private val paths = mapOf(
            "/find" to PathItem().apply {
                summary = "Summary"
            }
        )
    }
}