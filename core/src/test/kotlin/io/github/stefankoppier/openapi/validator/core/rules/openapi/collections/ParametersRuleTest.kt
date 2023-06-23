package io.github.stefankoppier.openapi.validator.core.rules.openapi.collections

import io.github.stefankoppier.openapi.validator.core.assertThatResult
import io.swagger.v3.oas.models.parameters.Parameter
import kotlin.test.Test

class ParametersRuleTest {

    @Test
    fun `all succeeds`() {
        val rule = ParametersRule().all {
            deprecated { isTrue() }
        }

        assertThatResult(rule.validate(fixture)).isSuccess()
    }

    @Test
    fun `all fails`() {
        val rule = ParametersRule().all {
            deprecated { isFalse() }
        }

        assertThatResult(rule.validate(fixture)).isFailure()
    }

    @Test
    fun `parameter succeeds`() {
        val rule = ParametersRule().parameter(named = "parameter") {
            deprecated { isTrue() }
        }

        assertThatResult(rule.validate(fixture)).isSuccess()
    }

    @Test
    fun `parameter fails`() {
        val rule = ParametersRule().parameter(named = "parameter") {
            deprecated { isFalse() }
        }

        assertThatResult(rule.validate(fixture)).isFailure()
    }

    @Test
    fun `parameter not found`() {
        val rule = ParametersRule().parameter(named = "nothing") {
            deprecated { isFalse() }
        }

        assertThatResult(rule.validate(fixture)).isFailure()
    }

    companion object {
        private val fixture = listOf(
            Parameter().apply {
                name = "parameter"
                deprecated = true
                description = "Description"
                `in` = "query"
            },
        )
    }
}
