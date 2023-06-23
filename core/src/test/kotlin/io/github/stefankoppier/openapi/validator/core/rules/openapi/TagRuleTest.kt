package io.github.stefankoppier.openapi.validator.core.rules.openapi

import io.github.stefankoppier.openapi.validator.core.ValidationFailure
import io.github.stefankoppier.openapi.validator.core.assertThatResult
import io.github.stefankoppier.openapi.validator.core.rules.RuleGroup
import io.swagger.v3.oas.models.ExternalDocumentation
import io.swagger.v3.oas.models.tags.Tag
import kotlin.test.Test

class TagRuleTest {

    @Test
    fun `name is required`() {
        val rule = TagRule()
        assertThatResult(rule.validate(Tag())).isFailure(
            ValidationFailure(RuleGroup.named("name", RuleGroup.Category.FIELD, "", RuleGroup.unknown()), "Was required but is not given"),
        )
    }

    @Test
    fun `name succeeds`() {
        val rule = TagRule()
            .name { exactly("Name") }

        assertThatResult(rule.validate(fixture)).isSuccess()
    }

    @Test
    fun `name fails`() {
        val rule = TagRule()
            .name { exactly("Fail") }

        assertThatResult(rule.validate(fixture)).isFailure(
            ValidationFailure(
                RuleGroup.named("name", RuleGroup.Category.FIELD, "", RuleGroup.unknown()),
                "Was supposed to be 'Fail' but is 'Name'",
            ),
        )
    }

    @Test
    fun `description succeeds`() {
        val rule = TagRule()
            .description { exactly("Description") }

        assertThatResult(rule.validate(fixture)).isSuccess()
    }

    @Test
    fun `description fails`() {
        val rule = TagRule()
            .description { exactly("Fail") }

        assertThatResult(rule.validate(fixture)).isFailure(
            ValidationFailure(
                RuleGroup.named("description", RuleGroup.Category.FIELD, "", RuleGroup.unknown()),
                "Was supposed to be 'Fail' but is 'Description'",
            ),
        )
    }

    @Test
    fun `externalDocs succeeds`() {
        val rule = TagRule()
            .externalDocs { exactly(ExternalDocumentation().apply { url = "http://localhost" }) }

        assertThatResult(rule.validate(fixture)).isSuccess()
    }

    @Test
    fun `externalDocs fails`() {
        val rule = TagRule()
            .externalDocs { exactly(ExternalDocumentation()) }

        assertThatResult(rule.validate(fixture)).isFailure(
            RuleGroup.named("externalDocs", RuleGroup.Category.OBJECT, "", RuleGroup.unknown()),
        )
    }
    companion object {
        val fixture = Tag().apply {
            name = "Name"
            description = "Description"
            externalDocs = ExternalDocumentation().apply {
                url = "http://localhost"
            }
        }
    }
}
