package io.github.stefankoppier.openapi.validator.core.rules.openapi.collections

import io.github.stefankoppier.openapi.validator.core.assertThatResult
import io.swagger.v3.oas.models.servers.Server
import kotlin.test.Test

class ServersRuleTest {

    @Test
    fun `all succeeds`() {
        val rule = ServersRule().all {
            description { exactly("Description") }
        }

        assertThatResult(rule.validate(fixture)).isSuccess()
    }

    @Test
    fun `all fails`() {
        val rule = ServersRule().all {
            description { exactly("Invalid") }
        }

        assertThatResult(rule.validate(fixture)).isFailure()
    }

    @Test
    fun `server succeeds`() {
        val rule = ServersRule().server(url = URL) {
            description { exactly("Description") }
        }

        assertThatResult(rule.validate(fixture)).isSuccess()
    }

    @Test
    fun `server fails`() {
        val rule = ServersRule().server(url = URL) {
            description { exactly("Invalid") }
        }

        assertThatResult(rule.validate(fixture)).isFailure()
    }

    @Test
    fun `server not found`() {
        val rule = ServersRule().server(url = "http://notfound.com") {
            description { exactly("Invalid") }
        }

        assertThatResult(rule.validate(fixture)).isFailure()
    }

    companion object {
        private const val URL = "http://localhost"

        private val fixture = listOf(
            Server().apply {
                url = URL
                description = "Description"
            },
        )
    }
}
