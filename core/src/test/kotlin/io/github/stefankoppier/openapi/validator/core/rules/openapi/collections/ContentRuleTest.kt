package io.github.stefankoppier.openapi.validator.core.rules.openapi.collections

import io.swagger.v3.oas.models.media.MediaType
import org.assertj.core.api.Assertions.assertThat
import kotlin.test.Test

class ContentRuleTest {

    @Test
    fun `all succeeds`() {
        val rule = ContentRule().all {
            example { exactly("Example") }
        }

        val result = rule.validate(encodings)
        assertThat(result.isSuccess).isTrue()
    }

    @Test
    fun `all fails`() {
        val rule = ContentRule().all {
            example { exactly("Fail") }
        }

        val result = rule.validate(encodings)
        assertThat(result.isFailure).isTrue()
    }

    @Test
    fun `path succeeds`() {
        val rule = ContentRule().mediaType(named = "application/json") {
            example { exactly("Example") }
        }

        val result = rule.validate(encodings)
        assertThat(result.isSuccess).isTrue()
    }

    @Test
    fun `path fails`() {
        val rule = ContentRule().mediaType(named = "application/json") {
            example { exactly("Fail") }
        }

        val result = rule.validate(encodings)
        assertThat(result.isFailure).isTrue()
    }

    companion object {
        private val encodings = mapOf(
            "application/json" to MediaType().apply {
                example = "Example"
            }
        ).toList()
    }
}