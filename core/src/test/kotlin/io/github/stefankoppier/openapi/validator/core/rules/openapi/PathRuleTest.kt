package io.github.stefankoppier.openapi.validator.core.rules.openapi

import io.github.stefankoppier.openapi.validator.core.ValidationFailure
import io.github.stefankoppier.openapi.validator.core.assertThatResult
import io.github.stefankoppier.openapi.validator.core.rules.RuleGroup
import io.swagger.v3.oas.models.Operation
import io.swagger.v3.oas.models.PathItem
import io.swagger.v3.oas.models.parameters.Parameter
import io.swagger.v3.oas.models.servers.Server
import org.assertj.core.api.Assertions.assertThat
import kotlin.test.Test

class PathRuleTest {

    @Test
    fun `summary succeeds`() {
        val rule = PathRule()
            .summary { exactly("summary") }

        assertThatResult(rule.validate(fixture)).isSuccess()
    }

    @Test
    fun `summary fails`() {
        val rule = PathRule()
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
        val rule = PathRule()
            .description { exactly("description") }

        assertThatResult(rule.validate(fixture)).isSuccess()
    }

    @Test
    fun `description fails`() {
        val rule = PathRule()
            .description { exactly("fail") }

        assertThatResult(rule.validate(fixture)).isFailure(
            ValidationFailure(
                RuleGroup.named("description", RuleGroup.Category.FIELD, "", RuleGroup.unknown()),
                "Was supposed to be 'fail' but is 'description'",
            ),
        )
    }

    @Test
    fun `operations all are optional`() {
        val rule = PathRule()
            .operations {
                required()
                operationId {
                    required()
                    exactly("operation")
                }
            }

        assertThatResult(rule.validate("url" to PathItem())).isSuccess()
    }

    @Test
    fun `operations succeeds for all`() {
        val rule = PathRule()
            .operations {
                required()
                operationId { required() }
            }

        assertThatResult(rule.validate(fixture)).isSuccess()
    }

    @Test
    fun `operations fails for all`() {
        val rule = PathRule()
            .operations { operationId { exactly("fail") } }

        assertThat(rule.validate(fixture).failures).hasSize(8)
    }

    @Test
    fun `get succeeds`() {
        val rule = PathRule()
            .get { exactly(Operation().apply { operationId = "get" }) }

        assertThatResult(rule.validate(fixture)).isSuccess()
    }

    @Test
    fun `get fails`() {
        val rule = PathRule()
            .get { exactly(Operation()) }

        assertThatResult(rule.validate(fixture)).isFailure(
            RuleGroup.named("get", RuleGroup.Category.GROUP, "", RuleGroup.unknown()),
        )
    }

    @Test
    fun `put succeeds`() {
        val rule = PathRule()
            .put { exactly(Operation().apply { operationId = "put" }) }

        assertThatResult(rule.validate(fixture)).isSuccess()
    }

    @Test
    fun `put fails`() {
        val rule = PathRule()
            .put { exactly(Operation()) }

        assertThatResult(rule.validate(fixture)).isFailure(
            RuleGroup.named("put", RuleGroup.Category.GROUP, "", RuleGroup.unknown()),
        )
    }

    @Test
    fun `post succeeds`() {
        val rule = PathRule()
            .post { exactly(Operation().apply { operationId = "post" }) }

        assertThatResult(rule.validate(fixture)).isSuccess()
    }

    @Test
    fun `post fails`() {
        val rule = PathRule()
            .post { exactly(Operation()) }

        assertThatResult(rule.validate(fixture)).isFailure(
            RuleGroup.named("post", RuleGroup.Category.GROUP, "", RuleGroup.unknown()),
        )
    }

    @Test
    fun `delete succeeds`() {
        val rule = PathRule()
            .delete { exactly(Operation().apply { operationId = "delete" }) }

        assertThatResult(rule.validate(fixture)).isSuccess()
    }

    @Test
    fun `delete fails`() {
        val rule = PathRule()
            .delete { exactly(Operation()) }

        assertThatResult(rule.validate(fixture)).isFailure(
            RuleGroup.named("delete", RuleGroup.Category.GROUP, "", RuleGroup.unknown()),
        )
    }

    @Test
    fun `options succeeds`() {
        val rule = PathRule()
            .options { exactly(Operation().apply { operationId = "options" }) }

        assertThatResult(rule.validate(fixture)).isSuccess()
    }

    @Test
    fun `options fails`() {
        val rule = PathRule()
            .options { exactly(Operation()) }

        assertThatResult(rule.validate(fixture)).isFailure(
            RuleGroup.named("options", RuleGroup.Category.GROUP, "", RuleGroup.unknown()),
        )
    }

    @Test
    fun `head succeeds`() {
        val rule = PathRule()
            .head { exactly(Operation().apply { operationId = "head" }) }

        assertThatResult(rule.validate(fixture)).isSuccess()
    }

    @Test
    fun `head fails`() {
        val rule = PathRule()
            .head { exactly(Operation()) }

        assertThatResult(rule.validate(fixture)).isFailure(
            RuleGroup.named("head", RuleGroup.Category.GROUP, "", RuleGroup.unknown()),
        )
    }

    @Test
    fun `patch succeeds`() {
        val rule = PathRule()
            .patch { exactly(Operation().apply { operationId = "patch" }) }

        assertThatResult(rule.validate(fixture)).isSuccess()
    }

    @Test
    fun `patch fails`() {
        val rule = PathRule()
            .patch { exactly(Operation()) }

        assertThatResult(rule.validate(fixture)).isFailure(
            RuleGroup.named("patch", RuleGroup.Category.GROUP, "", RuleGroup.unknown()),
        )
    }

    @Test
    fun `trace succeeds`() {
        val rule = PathRule()
            .trace { exactly(Operation().apply { operationId = "trace" }) }

        assertThatResult(rule.validate(fixture)).isSuccess()
    }

    @Test
    fun `trace fails`() {
        val rule = PathRule()
            .trace { exactly(Operation()) }

        assertThatResult(rule.validate(fixture)).isFailure(
            RuleGroup.named("trace", RuleGroup.Category.GROUP, "", RuleGroup.unknown()),
        )
    }

    @Test
    fun `servers succeeds`() {
        val rule = PathRule()
            .servers { exactly(listOf(Server())) }

        assertThatResult(rule.validate(fixture)).isSuccess()
    }

    @Test
    fun `servers fails`() {
        val rule = PathRule()
            .servers { exactly(listOf(Server().apply { url = "http://localhost" })) }

        assertThatResult(rule.validate(fixture))
            .isFailure(
                RuleGroup.named("servers", RuleGroup.Category.GROUP, "", RuleGroup.unknown()),
            )
    }

    @Test
    fun `parameters succeeds`() {
        val rule = PathRule()
            .parameters { exactly(listOf(Parameter())) }

        assertThatResult(rule.validate(fixture)).isSuccess()
    }

    @Test
    fun `parameters fails`() {
        val rule = PathRule()
            .parameters { exactly(listOf(Parameter().apply { name = "parameter" })) }

        assertThatResult(rule.validate(fixture))
            .isFailure(RuleGroup.named("parameters", RuleGroup.Category.GROUP, "", RuleGroup.unknown()))
    }

    companion object {
        val fixture = "url" to PathItem().apply {
            summary = "summary"
            description = "description"
            get = Operation().apply { operationId = "get" }
            put = Operation().apply { operationId = "put" }
            post = Operation().apply { operationId = "post" }
            delete = Operation().apply { operationId = "delete" }
            options = Operation().apply { operationId = "options" }
            head = Operation().apply { operationId = "head" }
            patch = Operation().apply { operationId = "patch" }
            trace = Operation().apply { operationId = "trace" }
            servers = listOf(Server())
            parameters = listOf(Parameter())
            `$ref` = "ref"
        }
    }
}
