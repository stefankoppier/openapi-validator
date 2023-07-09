package io.github.stefankoppier.openapi.validator.core.rules.openapi

import io.github.stefankoppier.openapi.validator.core.ValidationFailure
import io.github.stefankoppier.openapi.validator.core.assertThatResult
import io.github.stefankoppier.openapi.validator.core.rules.RuleGroup
import io.swagger.v3.oas.models.callbacks.Callback
import kotlin.test.Test

class CallbackRuleTest {

    @Test
    fun `ref succeeds`() {
        val rule = CallbackRule()
            .ref { exactly("#/components/callbacks/ref") }

        assertThatResult(rule.validate(fixture)).isSuccess()
    }

    @Test
    fun `ref fails`() {
        val rule = CallbackRule()
            .ref { exactly("fail") }

        assertThatResult(rule.validate(fixture)).isFailure(
            ValidationFailure(
                RuleGroup.named("ref", RuleGroup.Category.FIELD, "", RuleGroup.unknown()),
                "Was supposed to be 'fail' but is '#/components/callbacks/ref'",
            ),
        )
    }

    companion object {
        val fixture = Callback().apply {
            `$ref` = "ref"
        }
    }
}
