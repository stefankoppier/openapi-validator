package io.github.stefankoppier.openapi.validator.core.rules.openapi

import io.github.stefankoppier.openapi.validator.core.ValidationFailure
import io.github.stefankoppier.openapi.validator.core.assertThatResult
import io.github.stefankoppier.openapi.validator.core.rules.RuleGroup
import io.swagger.v3.oas.models.links.Link
import io.swagger.v3.oas.models.servers.Server
import kotlin.test.Test

class LinkRuleTest {

    @Test
    fun `operationRef succeeds`() {
        val rule = LinkRule()
            .operationRef { exactly("operationRef") }

        assertThatResult(rule.validate(fixture)).isSuccess()
    }

    @Test
    fun `operationRef fails`() {
        val rule = LinkRule()
            .operationRef { exactly("fail") }

        assertThatResult(rule.validate(fixture)).isFailure(
            ValidationFailure(
                RuleGroup.named("operationRef", RuleGroup.Category.FIELD, "", RuleGroup.unknown()),
                "Was supposed to be 'fail' but is 'operationRef'",
            ),
        )
    }

    @Test
    fun `operationId succeeds`() {
        val rule = LinkRule()
            .operationId { exactly("operationId") }

        assertThatResult(rule.validate(fixture)).isSuccess()
    }

    @Test
    fun `operationId fails`() {
        val rule = LinkRule()
            .operationId { exactly("fail") }

        assertThatResult(rule.validate(fixture)).isFailure(
            ValidationFailure(
                RuleGroup.named("operationId", RuleGroup.Category.FIELD, "", RuleGroup.unknown()),
                "Was supposed to be 'fail' but is 'operationId'",
            ),
        )
    }

//    @Test
//    fun `parameters succeeds`() {
//        val rule = LinkRule()
//            .parameters {
//                keys { exactly("parameter") }
//                values { exactly("value") }
//            }
//
//        assertThatResult(rule.validate(fixture)).isSuccess()
//    }
//
//    @Test
//    fun `parameters fails`() {
//        val rule = LinkRule()
//            .parameters {
//                keys { exactly("parameter") }
//                values { exactly("fail") }
//            }
//
//        assertThatResult(rule.validate(fixture)).isFailure(
//            RuleGroup.named("keys", RuleGroup.Category.FIELD, "", RuleGroup.unknown()),
//        )
//    }

    @Test
    fun `requestBody succeeds`() {
        val rule = LinkRule()
            .requestBody { instanceof(String::class) }

        assertThatResult(rule.validate(fixture)).isSuccess()
    }

    @Test
    fun `requestBody fails`() {
        val rule = LinkRule()
            .requestBody { instanceof(Int::class) }

        assertThatResult(rule.validate(fixture)).isFailure(
            ValidationFailure(
                RuleGroup.named("requestBody", RuleGroup.Category.FIELD, "", RuleGroup.unknown()),
                "Was supposed to be of type 'Int' but is of type 'String'",
            ),
        )
    }

    @Test
    fun `server succeeds`() {
        val rule = LinkRule()
            .server { exactly(Server().apply { url = "http:localhost" }) }

        assertThatResult(rule.validate(fixture)).isSuccess()
    }

    @Test
    fun `server fails`() {
        val rule = LinkRule()
            .server { exactly(Server().apply { url = "https:localhost" }) }

        assertThatResult(rule.validate(fixture)).isFailure(
            RuleGroup.named("server", RuleGroup.Category.GROUP, "", RuleGroup.unknown()),
        )
    }

    companion object {
        val fixture = Link().apply {
            operationRef = "operationRef"
            operationId = "operationId"
            parameters = mapOf("parameter" to "value")
            requestBody = "requestBody"
            description = "description"
            server = Server().apply { url = "http:localhost" }
        }
    }
}
