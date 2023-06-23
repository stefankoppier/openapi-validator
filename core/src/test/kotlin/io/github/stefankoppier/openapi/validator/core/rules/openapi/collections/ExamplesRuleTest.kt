package io.github.stefankoppier.openapi.validator.core.rules.openapi.collections

import io.github.stefankoppier.openapi.validator.core.assertThatResult
import io.swagger.v3.oas.models.examples.Example
import kotlin.test.Test

class ExamplesRuleTest {

    @Test
    fun `all succeeds`() {
        val rule = ExamplesRule().all {
            value { exactly("Example") }
        }

        assertThatResult(rule.validate(fixture)).isSuccess()
    }

    @Test
    fun `all fails`() {
        val rule = ExamplesRule().all {
            value { exactly("Fail") }
        }

        assertThatResult(rule.validate(fixture)).isFailure()
    }

    @Test
    fun `example succeeds`() {
        val rule = ExamplesRule().example(named = "example") {
            value { exactly("Example") }
        }

        assertThatResult(rule.validate(fixture)).isSuccess()
    }

    @Test
    fun `example fails`() {
        val rule = ExamplesRule().example(named = "example") {
            value { exactly("Fail") }
        }

        assertThatResult(rule.validate(fixture)).isFailure()
    }

    @Test
    fun `example not found`() {
        val rule = ExamplesRule().example(named = "nothing") {
            value { exactly("Fail") }
        }

        assertThatResult(rule.validate(fixture)).isFailure()
    }

    companion object {
        private val fixture = mapOf(
            "example" to Example().apply {
                value = "Example"
            },
        ).toList()
    }
}
