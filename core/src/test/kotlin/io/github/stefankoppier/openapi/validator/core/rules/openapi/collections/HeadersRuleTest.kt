package io.github.stefankoppier.openapi.validator.core.rules.openapi.collections

import io.swagger.v3.oas.models.headers.Header
import org.assertj.core.api.Assertions.assertThat
import kotlin.test.Test

class HeadersRuleTest {

    @Test
    fun `all succeeds`() {
        val rule = HeadersRule().all {
            required { isTrue() }
        }

        val result = rule.validate(fixture)
        assertThat(result.isSuccess).isTrue()
    }

    @Test
    fun `all fails`() {
        val rule = HeadersRule().all {
            required { isFalse() }
        }

        val result = rule.validate(fixture)
        assertThat(result.isFailure).isTrue()
    }

    @Test
    fun `header succeeds`() {
        val rule = HeadersRule().header(named = "header") {
            required { isTrue() }
        }

        val result = rule.validate(fixture)
        assertThat(result.isSuccess).isTrue()
    }

    @Test
    fun `header fails`() {
        val rule = HeadersRule().header(named = "header") {
            required { isFalse() }
        }

        val result = rule.validate(fixture)
        assertThat(result.isFailure).isTrue()
    }

    companion object {
        private val fixture = mapOf(
            "header" to Header().apply {
                required = true
            }
        ).toList()
    }
}