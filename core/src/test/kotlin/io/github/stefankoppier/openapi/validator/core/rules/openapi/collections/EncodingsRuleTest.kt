package io.github.stefankoppier.openapi.validator.core.rules.openapi.collections

import io.swagger.v3.oas.models.media.Encoding
import org.assertj.core.api.Assertions.assertThat
import kotlin.test.Test

class EncodingsRuleTest {

    @Test
    fun `all succeeds`() {
        val rule = EncodingsRule().all {
            contentType { exactly("application/json") }
        }

        val result = rule.validate(fixture)
        assertThat(result.isSuccess).isTrue()
    }

    @Test
    fun `all fails`() {
        val rule = EncodingsRule().all {
            contentType { exactly("application/xml") }
        }

        val result = rule.validate(fixture)
        assertThat(result.isFailure).isTrue()
    }

    @Test
    fun `path succeeds`() {
        val rule = EncodingsRule().encoding(named = "json") {
            contentType { exactly("application/json") }
        }

        val result = rule.validate(fixture)
        assertThat(result.isSuccess).isTrue()
    }

    @Test
    fun `path fails`() {
        val rule = EncodingsRule().encoding(named = "json") {
             contentType { exactly("application/xml") }
        }

        val result = rule.validate(fixture)
        assertThat(result.isFailure).isTrue()
    }

    companion object {
        private val fixture = mapOf(
            "json" to Encoding().apply {
                contentType = "application/json"
            }
        ).toList()
    }
}