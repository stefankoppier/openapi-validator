package io.github.stefankoppier.openapi.validator.core.rules.openapi.collections

import io.github.stefankoppier.openapi.validator.core.assertThatResult
import kotlin.test.Test

class SecurityRequirementRuleTest {

    @Test
    fun `all succeeds`() {
        val rule = SecurityRequirementRule().all {
            each { it == "requirement" }
        }

        assertThatResult(rule.validate(fixture)).isSuccess()
    }

    @Test
    fun `all fails`() {
        val rule = SecurityRequirementRule().all {
            each { it == "fail" }
        }

        assertThatResult(rule.validate(fixture)).isFailure()
    }

    @Test
    fun `securityRequirement succeeds`() {
        val rule = SecurityRequirementRule().securityRequirement(named = "name") {
            required()
        }

        assertThatResult(rule.validate(fixture)).isSuccess()
    }

    @Test
    fun `securityRequirement fails`() {
        val rule = SecurityRequirementRule().securityRequirement(named = "name") {
            each { it == "fail" }
        }

        assertThatResult(rule.validate(fixture)).isFailure()
    }

    @Test
    fun `securityRequirement not found`() {
        val rule = SecurityRequirementRule().securityRequirement(named = "unknown") {
            required()
        }

        assertThatResult(rule.validate(fixture)).isFailure()
    }

    companion object {
        private val fixture = mapOf(
            "name" to listOf("requirement"),
        ).toList()
    }
}
