package io.github.stefankoppier.openapi.validator.core.rules.openapi

import io.github.stefankoppier.openapi.validator.core.ValidationFailure
import io.github.stefankoppier.openapi.validator.core.assertThatResult
import io.github.stefankoppier.openapi.validator.core.rules.RuleGroup
import io.swagger.v3.oas.models.info.Contact
import io.swagger.v3.oas.models.info.Info
import io.swagger.v3.oas.models.info.License
import kotlin.test.Test

class InfoRuleTest {

    @Test
    fun `title and version is required`() {
        val rule = InfoRule()
        assertThatResult(rule.validate(Info())).isFailure(
            ValidationFailure(RuleGroup.named("title", RuleGroup.Category.FIELD, "", RuleGroup.unknown()), "Was required but is not given"),
            ValidationFailure(RuleGroup.named("version", RuleGroup.Category.FIELD, "", RuleGroup.unknown()), "Was required but is not given"),
        )
    }

    @Test
    fun `title succeeds`() {
        val rule = InfoRule()
            .title { exactly("title") }

        assertThatResult(rule.validate(fixture)).isSuccess()
    }

    @Test
    fun `title fails`() {
        val rule = InfoRule()
            .title { exactly("fail") }

        assertThatResult(rule.validate(fixture)).isFailure(
            ValidationFailure(
                RuleGroup.named("title", RuleGroup.Category.FIELD, "", RuleGroup.unknown()),
                "Was supposed to be 'fail' but is 'title'",
            ),
        )
    }

    @Test
    fun `summary succeeds`() {
        val rule = InfoRule()
            .summary { exactly("summary") }

        assertThatResult(rule.validate(fixture)).isSuccess()
    }

    @Test
    fun `summary fails`() {
        val rule = InfoRule()
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
        val rule = InfoRule()
            .description { exactly("description") }

        assertThatResult(rule.validate(fixture)).isSuccess()
    }

    @Test
    fun `description fails`() {
        val rule = InfoRule()
            .description { exactly("fail") }

        assertThatResult(rule.validate(fixture)).isFailure(
            ValidationFailure(
                RuleGroup.named("description", RuleGroup.Category.FIELD, "", RuleGroup.unknown()),
                "Was supposed to be 'fail' but is 'description'",
            ),
        )
    }

    @Test
    fun `termsOfService succeeds`() {
        val rule = InfoRule()
            .termsOfService { exactly("tos") }

        assertThatResult(rule.validate(fixture)).isSuccess()
    }

    @Test
    fun `termsOfService fails`() {
        val rule = InfoRule()
            .termsOfService { exactly("fail") }

        assertThatResult(rule.validate(fixture)).isFailure(
            ValidationFailure(
                RuleGroup.named("termsOfService", RuleGroup.Category.FIELD, "", RuleGroup.unknown()),
                "Was supposed to be 'fail' but is 'tos'",
            ),
        )
    }

    @Test
    fun `contact succeeds`() {
        val rule = InfoRule()
            .contact { exactly(Contact()) }

        assertThatResult(rule.validate(fixture)).isSuccess()
    }

    @Test
    fun `contact fails`() {
        val rule = InfoRule()
            .contact { exactly(Contact().apply { name = "fail" }) }

        assertThatResult(rule.validate(fixture)).isFailure(
            RuleGroup.named("contact", RuleGroup.Category.GROUP, "", RuleGroup.unknown()),
        )
    }

    @Test
    fun `licence succeeds`() {
        val rule = InfoRule()
            .licence { exactly(License().apply { name = "licence" }) }

        assertThatResult(rule.validate(fixture)).isSuccess()
    }

    @Test
    fun `licence fails`() {
        val rule = InfoRule()
            .licence { exactly(License().apply { name = "fail" }) }

        assertThatResult(rule.validate(fixture)).isFailure(
            RuleGroup.named("licence", RuleGroup.Category.GROUP, "", RuleGroup.unknown()),
        )
    }

    @Test
    fun `version succeeds`() {
        val rule = InfoRule()
            .version { exactly("1.0.0") }

        assertThatResult(rule.validate(fixture)).isSuccess()
    }

    @Test
    fun `version fails`() {
        val rule = InfoRule()
            .version { exactly("2.0.0") }

        assertThatResult(rule.validate(fixture)).isFailure(
            ValidationFailure(
                RuleGroup.named("version", RuleGroup.Category.FIELD, "", RuleGroup.unknown()),
                "Was supposed to be '2.0.0' but is '1.0.0'",
            ),
        )
    }

    companion object {
        val fixture = Info().apply {
            title = "title"
            summary = "summary"
            description = "description"
            termsOfService = "tos"
            contact = Contact()
            license = License().name("licence")
            version = "1.0.0"
        }
    }
}
