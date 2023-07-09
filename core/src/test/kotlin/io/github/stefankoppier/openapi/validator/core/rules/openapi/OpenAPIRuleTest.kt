package io.github.stefankoppier.openapi.validator.core.rules.openapi

import io.github.stefankoppier.openapi.validator.core.ValidationFailure
import io.github.stefankoppier.openapi.validator.core.assertThatResult
import io.github.stefankoppier.openapi.validator.core.rules.RuleGroup
import io.swagger.v3.oas.models.Components
import io.swagger.v3.oas.models.ExternalDocumentation
import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.PathItem
import io.swagger.v3.oas.models.Paths
import io.swagger.v3.oas.models.info.Info
import io.swagger.v3.oas.models.links.Link
import io.swagger.v3.oas.models.security.SecurityRequirement
import io.swagger.v3.oas.models.servers.Server
import io.swagger.v3.oas.models.tags.Tag
import kotlin.test.Test

class OpenAPIRuleTest {

    @Test
    fun `openapi should be semver`() {
        val rule = OpenAPIRule()

        assertThatResult(rule.validate(OpenAPI().apply { openapi = "x" })).isFailure(
            ValidationFailure(
                RuleGroup.named("openapi", RuleGroup.Category.FIELD, "", RuleGroup.unknown()),
                "Was supposed to match '[0-9]+\\.[0-9]+\\.[0-9]+' but is 'x'",
            ),
        )
    }

    @Test
    fun `openapi succeeds`() {
        val rule = OpenAPIRule()
            .openapi { exactly("3.0.1") }

        assertThatResult(rule.validate(fixture)).isSuccess()
    }

    @Test
    fun `openapi fails`() {
        val rule = OpenAPIRule()
            .openapi { exactly("fail") }

        assertThatResult(rule.validate(fixture)).isFailure(
            ValidationFailure(
                RuleGroup.named("openapi", RuleGroup.Category.FIELD, "", RuleGroup.unknown()),
                "Was supposed to be 'fail' but is '3.0.1'",
            ),
        )
    }

    @Test
    fun `info succeeds`() {
        val rule = OpenAPIRule()
            .info { exactly(Info().apply { title = "title"; version = "1.0.0" }) }

        assertThatResult(rule.validate(fixture)).isSuccess()
    }

    @Test
    fun `info fails`() {
        val rule = OpenAPIRule()
            .info { exactly(Info()) }

        assertThatResult(rule.validate(fixture)).isFailure(
            RuleGroup.named("info", RuleGroup.Category.GROUP, "", RuleGroup.unknown()),
        )
    }

    @Test
    fun `jsonSchemaDialect succeeds`() {
        val rule = OpenAPIRule()
            .jsonSchemaDialect { exactly("jsonSchemaDialect") }

        assertThatResult(rule.validate(fixture)).isSuccess()
    }

    @Test
    fun `jsonSchemaDialect fails`() {
        val rule = OpenAPIRule()
            .jsonSchemaDialect { exactly("fail") }

        assertThatResult(rule.validate(fixture)).isFailure(
            ValidationFailure(
                RuleGroup.named("jsonSchemaDialect", RuleGroup.Category.FIELD, "", RuleGroup.unknown()),
                "Was supposed to be 'fail' but is 'jsonSchemaDialect'",
            ),
        )
    }

    @Test
    fun `servers succeeds`() {
        val rule = OpenAPIRule()
            .servers { exactly(listOf(Server())) }

        assertThatResult(rule.validate(fixture)).isSuccess()
    }

    @Test
    fun `servers fails`() {
        val rule = OpenAPIRule()
            .servers { exactly(listOf()) }

        assertThatResult(rule.validate(fixture)).isFailure(
            RuleGroup.named("servers", RuleGroup.Category.GROUP, "", RuleGroup.unknown()),
        )
    }

    @Test
    fun `paths succeeds`() {
        val rule = OpenAPIRule()
            .paths { exactly(Paths().addPathItem("path", PathItem()).toList()) }

        assertThatResult(rule.validate(fixture)).isSuccess()
    }

    @Test
    fun `paths fails`() {
        val rule = OpenAPIRule()
            .paths { exactly(listOf()) }

        assertThatResult(rule.validate(fixture)).isFailure(
            RuleGroup.named("paths", RuleGroup.Category.GROUP, "", RuleGroup.unknown()),
        )
    }

    @Test
    fun `webhooks succeeds`() {
        val rule = OpenAPIRule()
            .webhooks { exactly(Paths().addPathItem("webhook", PathItem()).toList()) }

        assertThatResult(rule.validate(fixture)).isSuccess()
    }

    @Test
    fun `webhooks fails`() {
        val rule = OpenAPIRule()
            .webhooks { exactly(listOf()) }

        assertThatResult(rule.validate(fixture)).isFailure(
            RuleGroup.named("webhooks", RuleGroup.Category.GROUP, "", RuleGroup.unknown()),
        )
    }

    @Test
    fun `components succeeds`() {
        val rule = OpenAPIRule()
            .components { exactly(Components()) }

        assertThatResult(rule.validate(fixture)).isSuccess()
    }

    @Test
    fun `components fails`() {
        val rule = OpenAPIRule()
            .components { exactly(Components().addLinks("fail", Link())) }

        assertThatResult(rule.validate(fixture)).isFailure(
            RuleGroup.named("components", RuleGroup.Category.GROUP, "", RuleGroup.unknown()),
        )
    }

    @Test
    fun `security succeeds`() {
        val rule = OpenAPIRule()
            .security { exactly(listOf(SecurityRequirement())) }

        assertThatResult(rule.validate(fixture)).isSuccess()
    }

    @Test
    fun `security fails`() {
        val rule = OpenAPIRule()
            .security { exactly(listOf()) }

        assertThatResult(rule.validate(fixture)).isFailure(
            RuleGroup.named("security", RuleGroup.Category.GROUP, "", RuleGroup.unknown()),
        )
    }

    @Test
    fun `tags succeeds`() {
        val rule = OpenAPIRule()
            .tags { exactly(listOf(Tag())) }

        assertThatResult(rule.validate(fixture)).isSuccess()
    }

    @Test
    fun `tags fails`() {
        val rule = OpenAPIRule()
            .tags { exactly(listOf()) }

        assertThatResult(rule.validate(fixture)).isFailure(
            RuleGroup.named("tags", RuleGroup.Category.GROUP, "", RuleGroup.unknown()),
        )
    }

    @Test
    fun `externalDocs succeeds`() {
        val rule = OpenAPIRule()
            .externalDocs { exactly(ExternalDocumentation().apply { url = "http://localhost" }) }

        assertThatResult(rule.validate(fixture)).isSuccess()
    }

    @Test
    fun `externalDocs fails`() {
        val rule = OpenAPIRule()
            .externalDocs { exactly(ExternalDocumentation().apply { url = "https://localhost" }) }

        assertThatResult(rule.validate(fixture)).isFailure(
            RuleGroup.named("externalDocs", RuleGroup.Category.GROUP, "", RuleGroup.unknown()),
        )
    }

    companion object {
        val fixture = OpenAPI().apply {
            openapi = "3.0.1"
            info = Info().apply { title = "title"; version = "1.0.0" }
            jsonSchemaDialect = "jsonSchemaDialect"
            servers = listOf(Server())
            paths = Paths().addPathItem("path", PathItem())
            webhooks = Paths().addPathItem("webhook", PathItem())
            components = Components()
            security = listOf(SecurityRequirement())
            tags = listOf(Tag())
            externalDocs = ExternalDocumentation().apply { url = "http://localhost" }
        }
    }
}
