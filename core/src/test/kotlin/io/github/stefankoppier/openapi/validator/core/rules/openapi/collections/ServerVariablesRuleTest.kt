package io.github.stefankoppier.openapi.validator.core.rules.openapi.collections

import io.github.stefankoppier.openapi.validator.core.assertThat
import io.swagger.v3.oas.models.servers.ServerVariable
import kotlin.test.Test

class ServerVariablesRuleTest {

    @Test
    fun `all succeeds`() {
        val rule = ServerVariablesRule().all {
            description { exactly("Description") }
        }

        assertThat(rule.validate(fixture)).isSuccess()
    }

    @Test
    fun `all fails`() {
        val rule = ServerVariablesRule().all {
            description { exactly("Invalid") }
        }

        assertThat(rule.validate(fixture)).isFailure()
    }

    @Test
    fun `serverVariable succeeds`() {
        val rule = ServerVariablesRule().serverVariable(named = "variable") {
            description { exactly("Description") }
        }

        assertThat(rule.validate(fixture)).isSuccess()
    }

    @Test
    fun `serverVariable fails`() {
        val rule = ServerVariablesRule().serverVariable(named = "variable") {
            description { exactly("Invalid") }
        }

        assertThat(rule.validate(fixture)).isFailure()
    }

    @Test
    fun `serverVariable not found`() {
        val rule = ServerVariablesRule().serverVariable(named = "not found") {
            description { exactly("Invalid") }
        }

        assertThat(rule.validate(fixture)).isFailure()
    }

    companion object {
        private val fixture = mapOf(
            "variable" to ServerVariable().apply {
                description = "Description"
                default = "Default"
            },
        ).toList()
    }
}
