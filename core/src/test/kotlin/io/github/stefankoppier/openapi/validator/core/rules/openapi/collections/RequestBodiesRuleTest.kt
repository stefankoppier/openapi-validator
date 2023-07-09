package io.github.stefankoppier.openapi.validator.core.rules.openapi.collections

import io.github.stefankoppier.openapi.validator.core.assertThatResult
import io.swagger.v3.oas.models.media.Content
import io.swagger.v3.oas.models.parameters.RequestBody
import kotlin.test.Test

class RequestBodiesRuleTest {

    @Test
    fun `all succeeds`() {
        val rule = RequestBodiesRule().all {
            description { exactly("Description") }
        }

        assertThatResult(rule.validate(fixture)).isSuccess()
    }

    @Test
    fun `all fails`() {
        val rule = RequestBodiesRule().all {
            description { exactly("Invalid") }
        }

        assertThatResult(rule.validate(fixture)).isFailure()
    }

    @Test
    fun `requestBody succeeds`() {
        val rule = RequestBodiesRule().requestBody(named = "requestBody") {
            description { exactly("Description") }
        }

        assertThatResult(rule.validate(fixture)).isSuccess()
    }

    @Test
    fun `requestBody fails`() {
        val rule = RequestBodiesRule().requestBody(named = "requestBody") {
            description { exactly("Invalid") }
        }

        assertThatResult(rule.validate(fixture)).isFailure()
    }

    @Test
    fun `requestBody not found`() {
        val rule = RequestBodiesRule().requestBody(named = "not found") {
            description { exactly("Invalid") }
        }

        assertThatResult(rule.validate(fixture)).isFailure()
    }

    companion object {
        private val fixture = mapOf(
            "requestBody" to RequestBody().apply {
                description = "Description"
                content = Content()
            },
        ).toList()
    }
}
