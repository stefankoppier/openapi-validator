package io.github.stefankoppier.openapi.validator.core.rules.openapi.collections

import io.github.stefankoppier.openapi.validator.core.assertThatResult
import io.swagger.v3.oas.models.media.MediaType
import kotlin.test.Test

class ContentRuleTest {

    @Test
    fun `all succeeds`() {
        val rule = ContentRule().all {
            example { exactly("Example") }
        }

        assertThatResult(rule.validate(fixture)).isSuccess()
    }

    @Test
    fun `all fails`() {
        val rule = ContentRule().all {
            example { exactly("Fail") }
        }

        assertThatResult(rule.validate(fixture)).isFailure()
    }

    @Test
    fun `mediaType succeeds`() {
        val rule = ContentRule().mediaType(named = "application/json") {
            example { exactly("Example") }
        }

        assertThatResult(rule.validate(fixture)).isSuccess()
    }

    @Test
    fun `mediaType fails`() {
        val rule = ContentRule().mediaType(named = "application/json") {
            example { exactly("Fail") }
        }

        assertThatResult(rule.validate(fixture)).isFailure()
    }

    @Test
    fun `mediaType not found`() {
        val rule = ContentRule().mediaType(named = "application/nothing") {
            example { exactly("Fail") }
        }

        assertThatResult(rule.validate(fixture)).isFailure()
    }

    companion object {
        private val fixture = mapOf(
            "application/json" to MediaType().apply {
                example = "Example"
            },
        ).toList()
    }
}
