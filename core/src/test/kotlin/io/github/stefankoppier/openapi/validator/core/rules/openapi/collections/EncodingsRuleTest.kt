package io.github.stefankoppier.openapi.validator.core.rules.openapi.collections

import io.github.stefankoppier.openapi.validator.core.assertThat
import io.swagger.v3.oas.models.media.Encoding
import kotlin.test.Test

class EncodingsRuleTest {

    @Test
    fun `all succeeds`() {
        val rule = EncodingsRule().all {
            contentType { exactly("application/json") }
        }

        assertThat(rule.validate(fixture)).isSuccess()
    }

    @Test
    fun `all fails`() {
        val rule = EncodingsRule().all {
            contentType { exactly("application/xml") }
        }

        assertThat(rule.validate(fixture)).isFailure()
    }

    @Test
    fun `path succeeds`() {
        val rule = EncodingsRule().encoding(named = "json") {
            contentType { exactly("application/json") }
        }

        assertThat(rule.validate(fixture)).isSuccess()
    }

    @Test
    fun `path fails`() {
        val rule = EncodingsRule().encoding(named = "json") {
             contentType { exactly("application/xml") }
        }

        assertThat(rule.validate(fixture)).isFailure()
    }

    companion object {
        private val fixture = mapOf(
            "json" to Encoding().apply {
                contentType = "application/json"
            }
        ).toList()
    }
}