package io.github.stefankoppier.openapi.validator.core.rules.openapi.collections

import io.swagger.v3.oas.models.headers.Header
import io.github.stefankoppier.openapi.validator.core.assertThat
import kotlin.test.Test

class HeadersRuleTest {

    @Test
    fun `all succeeds`() {
        val rule = HeadersRule().all {
            required { isTrue() }
        }

        assertThat(rule.validate(fixture)).isSuccess()
    }

    @Test
    fun `all fails`() {
        val rule = HeadersRule().all {
            required { isFalse() }
        }

        assertThat(rule.validate(fixture)).isFailure()
    }

    @Test
    fun `header succeeds`() {
        val rule = HeadersRule().header(named = "header") {
            required { isTrue() }
        }

        assertThat(rule.validate(fixture)).isSuccess()
    }

    @Test
    fun `header fails`() {
        val rule = HeadersRule().header(named = "header") {
            required { isFalse() }
        }

        assertThat(rule.validate(fixture)).isFailure()
    }

    companion object {
        private val fixture = mapOf(
            "header" to Header().apply {
                required = true
            }
        ).toList()
    }
}