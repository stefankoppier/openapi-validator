package io.github.stefankoppier.openapi.validator.core.rules.openapi.collections

import io.github.stefankoppier.openapi.validator.core.assertThatResult
import io.swagger.v3.oas.models.SpecVersion
import io.swagger.v3.oas.models.media.Schema
import kotlin.test.Test

class SchemasRuleTest {

    @Test
    fun `all succeeds`() {
        val rule = SchemasRule().all {
            name { exactly("pet") }
        }

        assertThatResult(rule.validate(fixture)).isSuccess()
    }

    @Test
    fun `all fails`() {
        val rule = SchemasRule().all {
            name { exactly("dog") }
        }

        assertThatResult(rule.validate(fixture)).isFailure()
    }

    @Test
    fun `schema succeeds`() {
        val rule = SchemasRule().schema(named = "pet") {
            required()
        }

        assertThatResult(rule.validate(fixture)).isSuccess()
    }

    @Test
    fun `schema fails`() {
        val rule = SchemasRule().schema(named = "dog") {
            required()
        }

        assertThatResult(rule.validate(fixture)).isFailure()
    }

    @Test
    fun `schema not found`() {
        val rule = SchemasRule().schema(named = "not found") {
            required()
        }

        assertThatResult(rule.validate(fixture)).isFailure()
    }

    companion object {
        private val fixture = mapOf(
            "pet" to Schema<String>(SpecVersion.V31).apply {
                name = "pet"
            },
        ).toList()
    }
}
