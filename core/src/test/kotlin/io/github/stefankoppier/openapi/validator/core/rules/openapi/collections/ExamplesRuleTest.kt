package io.github.stefankoppier.openapi.validator.core.rules.openapi.collections

import io.swagger.v3.oas.models.examples.Example
import org.assertj.core.api.Assertions.assertThat
import kotlin.test.Test

class ExamplesRuleTest {

    @Test
    fun `all succeeds`() {
        val rule = ExamplesRule().all {
            value { exactly("Example") }
        }

        val result = rule.validate(fixture)
        assertThat(result.isSuccess).isTrue()
    }

    @Test
    fun `all fails`() {
        val rule = ExamplesRule().all {
            value { exactly("Fail") }
        }

        val result = rule.validate(fixture)
        assertThat(result.isFailure).isTrue()
    }

    @Test
    fun `example succeeds`() {
        val rule = ExamplesRule().example(named = "example") {
            value { exactly("Example") }
        }

        val result = rule.validate(fixture)
        assertThat(result.isSuccess).isTrue()
    }

    @Test
    fun `example fails`() {
        val rule = ExamplesRule().example(named = "example") {
            value { exactly("Fail") }
        }

        val result = rule.validate(fixture)
        assertThat(result.isFailure).isTrue()
    }

    companion object {
        private val fixture = mapOf(
            "example" to Example().apply {
                value = "Example"
            }
        ).toList()
    }
}