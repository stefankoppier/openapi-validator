package io.github.stefankoppier.openapi.validator.core.rules.openapi.collections

import io.github.stefankoppier.openapi.validator.core.assertThatResult
import io.swagger.v3.oas.models.callbacks.Callback
import kotlin.test.Test

class CallbacksRuleTest {

    @Test
    fun `all succeeds`() {
        val rule = CallbacksRule().all {
            ref { exactly("#/components/callbacks/ref") }
        }

        assertThatResult(rule.validate(fixture)).isSuccess()
    }

    @Test
    fun `all fails`() {
        val rule = CallbacksRule().all {
            ref { exactly("fail") }
        }

        assertThatResult(rule.validate(fixture)).isFailure()
    }

    @Test
    fun `callback succeeds`() {
        val rule = CallbacksRule().callback(named = "callback") {
            ref { exactly("#/components/callbacks/ref") }
        }

        assertThatResult(rule.validate(fixture)).isSuccess()
    }

    @Test
    fun `callback fails`() {
        val rule = CallbacksRule().callback(named = "callback") {
            ref { exactly("fail") }
        }

        assertThatResult(rule.validate(fixture)).isFailure()
    }

    @Test
    fun `callback not found`() {
        val rule = CallbacksRule().callback(named = "nothing") {
            ref { exactly("nothing") }
        }

        assertThatResult(rule.validate(fixture)).isFailure()
    }

    companion object {
        private val fixture = mapOf(
            "callback" to Callback().apply { `$ref` = "ref" },
        ).toList()
    }
}
