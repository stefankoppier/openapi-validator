package io.github.stefankoppier.openapi.validator.core.rules.openapi.collections

import io.github.stefankoppier.openapi.validator.core.assertThatResult
import io.swagger.v3.oas.models.headers.Header
import kotlin.test.Test

class HeadersRuleTest {

    @Test
    fun `all succeeds`() {
        val rule = HeadersRule().all {
            required { isTrue() }
        }

        assertThatResult(rule.validate(fixture)).isSuccess()
    }

    @Test
    fun `all fails`() {
        val rule = HeadersRule().all {
            required { isFalse() }
        }

        assertThatResult(rule.validate(fixture)).isFailure()
    }

    @Test
    fun `header succeeds`() {
        val rule = HeadersRule().header(named = "header") {
            required { isTrue() }
        }

        assertThatResult(rule.validate(fixture)).isSuccess()
    }

    @Test
    fun `header fails`() {
        val rule = HeadersRule().header(named = "header") {
            required { isFalse() }
        }

        assertThatResult(rule.validate(fixture)).isFailure()
    }

    @Test
    fun `header not found`() {
        val rule = HeadersRule().header(named = "nothing") {
            required { isFalse() }
        }

        assertThatResult(rule.validate(fixture)).isFailure()
    }

    companion object {
        private val fixture = mapOf(
            "header" to Header().apply {
                required = true
            },
        ).toList()
    }
}
