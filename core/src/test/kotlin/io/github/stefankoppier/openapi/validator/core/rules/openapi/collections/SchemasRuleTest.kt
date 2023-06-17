package io.github.stefankoppier.openapi.validator.core.rules.openapi.collections

import io.swagger.v3.oas.models.SpecVersion
import io.swagger.v3.oas.models.media.Schema
import org.assertj.core.api.Assertions.assertThat
import kotlin.test.Test


class SchemasRuleTest {

    @Test
    fun `all succeeds`() {
        val rule = SchemasRule().all {
            name { exactly("pet") }
        }

        val result = rule.validate(schemas.toList())
        assertThat(result.isSuccess).isTrue()
    }

    @Test
    fun `all fails`() {
        val rule = SchemasRule().all {
            name { exactly("dog") }
        }

        val result = rule.validate(schemas.toList())
        assertThat(result.isFailure).isTrue()
    }

    @Test
    fun `schema succeeds`() {
        val rule = SchemasRule().schema(named = "pet") {
            required()
        }

        val result = rule.validate(schemas.toList())
        assertThat(result.isSuccess).isTrue()
    }

    @Test
    fun `schema fails`() {
        val rule = SchemasRule().schema(named = "dog") {
            required()
        }

        val result = rule.validate(schemas.toList())
        assertThat(result.isFailure).isTrue()
    }

    companion object {
        private val schemas = mapOf(
            "pet" to Schema<String>(SpecVersion.V31).apply {
                name = "pet"
            }
        )
    }
}