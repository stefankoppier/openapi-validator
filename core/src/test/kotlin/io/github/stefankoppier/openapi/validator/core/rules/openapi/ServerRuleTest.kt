package io.github.stefankoppier.openapi.validator.core.rules.openapi

import io.github.stefankoppier.openapi.validator.core.ValidationFailure
import io.github.stefankoppier.openapi.validator.core.assertThatResult
import io.github.stefankoppier.openapi.validator.core.rules.RuleGroup
import io.swagger.v3.oas.models.servers.Server
import io.swagger.v3.oas.models.servers.ServerVariable
import io.swagger.v3.oas.models.servers.ServerVariables
import kotlin.test.Test

class ServerRuleTest {

    @Test
    fun `url succeeds`() {
        val rule = ServerRule()
            .url { exactly("http://localhost") }

        assertThatResult(rule.validate(fixture)).isSuccess()
    }

    @Test
    fun `url fails`() {
        val rule = ServerRule()
            .url { exactly("https://localhost") }

        assertThatResult(rule.validate(fixture)).isFailure(
            ValidationFailure(
                RuleGroup.named("url", RuleGroup.Category.FIELD, "", RuleGroup.unknown()),
                "Was supposed to be 'https://localhost' but is 'http://localhost'",
            ),
        )
    }

    @Test
    fun `description succeeds`() {
        val rule = ServerRule()
            .description { exactly("description") }

        assertThatResult(rule.validate(fixture)).isSuccess()
    }

    @Test
    fun `description fails`() {
        val rule = ServerRule()
            .description { exactly("fail") }

        assertThatResult(rule.validate(fixture)).isFailure(
            ValidationFailure(
                RuleGroup.named("description", RuleGroup.Category.FIELD, "", RuleGroup.unknown()),
                "Was supposed to be 'fail' but is 'description'",
            ),
        )
    }

    @Test
    fun `variables succeeds`() {
        val rule = ServerRule()
            .variables { exactly(ServerVariables().toList()) }

        assertThatResult(rule.validate(fixture)).isSuccess()
    }

    @Test
    fun `variables fails`() {
        val rule = ServerRule()
            .variables { exactly(ServerVariables().apply { addServerVariable("fail", ServerVariable()) }.toList()) }

        assertThatResult(rule.validate(fixture)).isFailure(
            RuleGroup.named("variables", RuleGroup.Category.GROUP, "", RuleGroup.unknown()),
        )
    }

    companion object {
        val fixture = Server().apply {
            url = "http://localhost"
            description = "description"
            variables = ServerVariables()
        }
    }
}
