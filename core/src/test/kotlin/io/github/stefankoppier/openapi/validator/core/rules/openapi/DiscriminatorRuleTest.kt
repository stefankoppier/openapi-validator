package io.github.stefankoppier.openapi.validator.core.rules.openapi

import io.github.stefankoppier.openapi.validator.core.ValidationFailure
import io.github.stefankoppier.openapi.validator.core.assertThatResult
import io.github.stefankoppier.openapi.validator.core.rules.RuleGroup
import io.swagger.v3.oas.models.media.Discriminator
import kotlin.test.Test

class DiscriminatorRuleTest {

    @Test
    fun `propertyName succeeds`() {
        val rule = DiscriminatorRule()
            .propertyName { exactly("property") }

        assertThatResult(rule.validate(fixture)).isSuccess()
    }

    @Test
    fun `propertyName fails`() {
        val rule = DiscriminatorRule()
            .propertyName { exactly("fail") }

        assertThatResult(rule.validate(fixture)).isFailure(
            ValidationFailure(
                RuleGroup.named("propertyName", RuleGroup.Category.FIELD, "", RuleGroup.unknown()),
                "Was supposed to be 'fail' but is 'property'",
            ),
        )
    }

    companion object {
        val fixture = Discriminator().apply {
            propertyName = "property"
        }
    }
}
