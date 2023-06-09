package io.github.stefankoppier.openapi.validator.core.rules.openapi.collections

import io.github.stefankoppier.openapi.validator.core.assertThatResult
import io.swagger.v3.oas.models.responses.ApiResponse
import kotlin.test.Test

class ResponsesRuleTest {

    @Test
    fun `all succeeds`() {
        val rule = ResponsesRule().all {
            description { exactly("Description") }
        }

        assertThatResult(rule.validate(fixture)).isSuccess()
    }

    @Test
    fun `all fails`() {
        val rule = ResponsesRule().all {
            description { exactly("Invalid") }
        }

        assertThatResult(rule.validate(fixture)).isFailure()
    }

    @Test
    fun `response succeeds`() {
        val rule = ResponsesRule().response(named = "response") {
            description { exactly("Description") }
        }

        assertThatResult(rule.validate(fixture)).isSuccess()
    }

    @Test
    fun `response fails`() {
        val rule = ResponsesRule().response(named = "response") {
            description { exactly("Invalid") }
        }

        assertThatResult(rule.validate(fixture)).isFailure()
    }

    @Test
    fun `response not found`() {
        val rule = ResponsesRule().response(named = "not found") {
            description { exactly("Invalid") }
        }

        assertThatResult(rule.validate(fixture)).isFailure()
    }

    companion object {
        private val fixture = mapOf(
            "response" to ApiResponse().apply {
                description = "Description"
            },
        ).toList()
    }
}
