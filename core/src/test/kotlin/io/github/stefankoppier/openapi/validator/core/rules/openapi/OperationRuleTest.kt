package io.github.stefankoppier.openapi.validator.core.rules.openapi

import io.github.stefankoppier.openapi.validator.core.ValidationFailure
import io.github.stefankoppier.openapi.validator.core.assertThatResult
import io.github.stefankoppier.openapi.validator.core.rules.RuleGroup
import io.swagger.v3.oas.models.ExternalDocumentation
import io.swagger.v3.oas.models.Operation
import io.swagger.v3.oas.models.media.Content
import io.swagger.v3.oas.models.parameters.Parameter
import io.swagger.v3.oas.models.parameters.RequestBody
import io.swagger.v3.oas.models.responses.ApiResponse
import io.swagger.v3.oas.models.responses.ApiResponses
import io.swagger.v3.oas.models.security.SecurityRequirement
import io.swagger.v3.oas.models.servers.Server
import kotlin.test.Test

class OperationRuleTest {

    @Test
    fun `tags succeeds`() {
        val rule = OperationRule()
            .tags { exactly(listOf("tag")) }

        assertThatResult(rule.validate(fixture)).isSuccess()
    }

    @Test
    fun `tags fails`() {
        val rule = OperationRule()
            .tags { exactly(listOf("fail")) }

        assertThatResult(rule.validate(fixture)).isFailure(
            ValidationFailure(
                RuleGroup.named("tags", RuleGroup.Category.FIELD, "", RuleGroup.unknown()),
                "Was supposed to be '[fail]' but is '[tag]'",
            ),
        )
    }

    @Test
    fun `summary succeeds`() {
        val rule = OperationRule()
            .summary { exactly("summary") }

        assertThatResult(rule.validate(fixture)).isSuccess()
    }

    @Test
    fun `summary fails`() {
        val rule = OperationRule()
            .summary { exactly("fail") }

        assertThatResult(rule.validate(fixture)).isFailure(
            ValidationFailure(
                RuleGroup.named("summary", RuleGroup.Category.FIELD, "", RuleGroup.unknown()),
                "Was supposed to be 'fail' but is 'summary'",
            ),
        )
    }

    @Test
    fun `description succeeds`() {
        val rule = OperationRule()
            .description { exactly("description") }

        assertThatResult(rule.validate(fixture)).isSuccess()
    }

    @Test
    fun `description fails`() {
        val rule = OperationRule()
            .description { exactly("fail") }

        assertThatResult(rule.validate(fixture)).isFailure(
            ValidationFailure(
                RuleGroup.named("description", RuleGroup.Category.FIELD, "", RuleGroup.unknown()),
                "Was supposed to be 'fail' but is 'description'",
            ),
        )
    }

    @Test
    fun `externalDocs succeeds`() {
        val rule = OperationRule()
            .externalDocs { exactly(ExternalDocumentation().apply { url = "http://localhost" }) }

        assertThatResult(rule.validate(fixture)).isSuccess()
    }

    @Test
    fun `externalDocs fails`() {
        val rule = OperationRule()
            .externalDocs { exactly(ExternalDocumentation().apply { url = "http://localhost"; description = "fail" }) }

        assertThatResult(rule.validate(fixture)).isFailure(
            RuleGroup.named("externalDocs", RuleGroup.Category.GROUP, "", RuleGroup.unknown()),
        )
    }

    @Test
    fun `operationId succeeds`() {
        val rule = OperationRule()
            .operationId { exactly("operationId") }

        assertThatResult(rule.validate(fixture)).isSuccess()
    }

    @Test
    fun `operationId fails`() {
        val rule = OperationRule()
            .operationId { exactly("fail") }

        assertThatResult(rule.validate(fixture)).isFailure(
            ValidationFailure(
                RuleGroup.named("operationId", RuleGroup.Category.FIELD, "", RuleGroup.unknown()),
                "Was supposed to be 'fail' but is 'operationId'",
            ),
        )
    }

    @Test
    fun `parameters succeeds`() {
        val rule = OperationRule()
            .parameters { exactly(listOf(Parameter())) }

        assertThatResult(rule.validate(fixture)).isSuccess()
    }

    @Test
    fun `parameters fails`() {
        val rule = OperationRule()
            .parameters { exactly(listOf(Parameter().apply { name = "fail" })) }

        assertThatResult(rule.validate(fixture)).isFailure(
            RuleGroup.named("parameters", RuleGroup.Category.GROUP, "", RuleGroup.unknown()),
        )
    }

    @Test
    fun `requestBody succeeds`() {
        val rule = OperationRule()
            .requestBody { exactly(RequestBody().apply { content = Content() }) }

        assertThatResult(rule.validate(fixture)).isSuccess()
    }

    @Test
    fun `requestBody fails`() {
        val rule = OperationRule()
            .requestBody { exactly(RequestBody().apply { description = "fail" }) }

        assertThatResult(rule.validate(fixture)).isFailure(
            RuleGroup.named("requestBody", RuleGroup.Category.GROUP, "", RuleGroup.unknown()),
        )
    }

    @Test
    fun `responses succeeds`() {
        val rule = OperationRule()
            .responses { exactly(emptyList()) }

        assertThatResult(rule.validate(fixture)).isSuccess()
    }

    @Test
    fun `responses fails`() {
        val rule = OperationRule()
            .responses { exactly(listOf("fail" to ApiResponse())) }

        assertThatResult(rule.validate(fixture)).isFailure(
            RuleGroup.named("responses", RuleGroup.Category.GROUP, "", RuleGroup.unknown()),
        )
    }

    @Test
    fun `deprecated succeeds`() {
        val rule = OperationRule()
            .deprecated { isTrue() }

        assertThatResult(rule.validate(fixture)).isSuccess()
    }

    @Test
    fun `deprecated fails`() {
        val rule = OperationRule()
            .deprecated { isFalse() }

        assertThatResult(rule.validate(fixture)).isFailure(
            ValidationFailure(
                RuleGroup.named("deprecated", RuleGroup.Category.FIELD, "", RuleGroup.unknown()),
                "Was supposed to be 'false' but is 'true'",
            ),
        )
    }

    @Test
    fun `security succeeds`() {
        val rule = OperationRule()
            .security { exactly(listOf(SecurityRequirement())) }

        assertThatResult(rule.validate(fixture)).isSuccess()
    }

    @Test
    fun `security fails`() {
        val rule = OperationRule()
            .security { exactly(listOf(SecurityRequirement().addList("fail"))) }

        assertThatResult(rule.validate(fixture)).isFailure(
            RuleGroup.named("security", RuleGroup.Category.GROUP, "", RuleGroup.unknown()),
        )
    }

    @Test
    fun `servers succeeds`() {
        val rule = OperationRule()
            .servers { exactly(listOf(Server())) }

        assertThatResult(rule.validate(fixture)).isSuccess()
    }

    @Test
    fun `servers fails`() {
        val rule = OperationRule()
            .servers { exactly(listOf(Server().apply { description = "fail" })) }

        assertThatResult(rule.validate(fixture)).isFailure(
            RuleGroup.named("servers", RuleGroup.Category.GROUP, "", RuleGroup.unknown()),
        )
    }

    companion object {
        val fixture = Operation().apply {
            tags = listOf("tag")
            summary = "summary"
            description = "description"
            externalDocs = ExternalDocumentation().apply { url = "http://localhost" }
            operationId = "operationId"
            parameters = listOf(Parameter())
            requestBody = RequestBody().apply { content = Content() }
            responses = ApiResponses()
            deprecated = true
            security = listOf(SecurityRequirement())
            servers = listOf(Server())
        }
    }
}
