package io.github.stefankoppier.openapi.validator.core.rules.openapi.collections

import io.github.stefankoppier.openapi.validator.core.assertThatResult
import io.swagger.v3.oas.models.security.SecurityRequirement
import kotlin.test.Test

class SecurityRequirementsRuleTest {

    @Test
    fun `all succeeds`() {
        val rule = SecurityRequirementsRule().all {
            each { it == "name" to listOf("requirement") }
        }

        assertThatResult(rule.validate(fixture)).isSuccess()
    }

    @Test
    fun `all fails`() {
        val rule = SecurityRequirementsRule().all {
            each { it == "fail" to listOf("requirement") }
        }

        assertThatResult(rule.validate(fixture)).isFailure()
    }

    companion object {
        private val fixture = listOf(
            SecurityRequirement().apply {
                addList("name", listOf("requirement"))
            },
        ).toList()
    }
}
