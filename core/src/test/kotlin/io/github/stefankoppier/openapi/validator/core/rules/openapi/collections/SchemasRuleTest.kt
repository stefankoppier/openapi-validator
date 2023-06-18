package io.github.stefankoppier.openapi.validator.core.rules.openapi.collections

import io.swagger.v3.oas.models.SpecVersion
import io.swagger.v3.oas.models.media.Schema
import io.github.stefankoppier.openapi.validator.core.assertThat
import kotlin.test.Test


class SchemasRuleTest {

    @Test
    fun `all succeeds`() {
        val rule = SchemasRule().all {
            name { exactly("pet") }
        }

        assertThat(rule.validate(fixture)).isSuccess()
    }

    @Test
    fun `all fails`() {
        val rule = SchemasRule().all {
            name { exactly("dog") }
        }

        assertThat(rule.validate(fixture)).isFailure()
    }

    @Test
    fun `schema succeeds`() {
        val rule = SchemasRule().schema(named = "pet") {
            required()
        }

        assertThat(rule.validate(fixture)).isSuccess()
    }

    @Test
    fun `schema fails`() {
        val rule = SchemasRule().schema(named = "dog") {
            required()
        }

        assertThat(rule.validate(fixture)).isFailure()
    }

    @Test
    fun `schema not found`() {
        val rule = SchemasRule().schema(named = "not found") {
            required()
        }

        assertThat(rule.validate(fixture)).isFailure()
    }

    companion object {
        private val fixture = mapOf(
            "pet" to Schema<String>(SpecVersion.V31).apply {
                name = "pet"
            }
        ).toList()
    }
}