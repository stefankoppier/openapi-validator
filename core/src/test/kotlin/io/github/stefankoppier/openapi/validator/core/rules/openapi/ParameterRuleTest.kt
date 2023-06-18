package io.github.stefankoppier.openapi.validator.core.rules.openapi

import io.github.stefankoppier.openapi.validator.core.ValidationFailure
import io.github.stefankoppier.openapi.validator.core.assertThat
import io.github.stefankoppier.openapi.validator.core.rules.RuleGroup
import io.swagger.v3.oas.models.PathItem
import io.swagger.v3.oas.models.parameters.Parameter
import io.swagger.v3.oas.models.servers.Server
import kotlin.test.Test

class ParameterRuleTest {

    @Test
    fun `name and in are required`() {
        val rule = ParameterRule()
        assertThat(rule.validate(Parameter())).isFailure(
            ValidationFailure(RuleGroup.named("name", "", RuleGroup.Category.FIELD, RuleGroup.unknown()), "Was required but is not given"),
            ValidationFailure(RuleGroup.named("in", "", RuleGroup.Category.FIELD, RuleGroup.unknown()), "Was required but is not given")
        )
    }

    @Test
    fun `when in 'path' then required must be true`() {
        val rule = ParameterRule()
        val fixture = Parameter().apply {
            name = "Parameter"
            `in` = "path"
            required = false
        }
        assertThat(rule.validate(fixture)).isFailure(
            ValidationFailure(RuleGroup.named("required", "", RuleGroup.Category.FIELD, RuleGroup.unknown()), "Was supposed to be 'true' but is 'false'"),
        )
    }

    @Test
    fun `name succeeds`() {
        val rule = ParameterRule()
            .name { exactly("Parameter") }

        assertThat(rule.validate(fixture)).isSuccess()
    }

    @Test
    fun `name fails`() {
        val rule = ParameterRule()
            .name { exactly("Fail") }

        assertThat(rule.validate(fixture)).isFailure(
            ValidationFailure(
                RuleGroup.named("name", "", RuleGroup.Category.FIELD, RuleGroup.unknown()),
                "Was supposed to be 'Fail' but is 'Parameter'")
        )
    }

    companion object {
        val fixture = Parameter().apply {
            name = "Parameter"
            `in` = "path"
        }
    }
}