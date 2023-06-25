package io.github.stefankoppier.openapi.validator.core.rules.openapi

import io.github.stefankoppier.openapi.validator.core.ValidationFailure
import io.github.stefankoppier.openapi.validator.core.assertThatResult
import io.github.stefankoppier.openapi.validator.core.rules.RuleGroup
import io.swagger.v3.oas.models.ExternalDocumentation
import io.swagger.v3.oas.models.media.Discriminator
import io.swagger.v3.oas.models.media.Schema
import io.swagger.v3.oas.models.media.XML
import org.junit.jupiter.api.Disabled
import java.math.BigDecimal
import kotlin.test.Test

class SchemaRuleTest {

    @Test
    fun `name succeeds`() {
        val rule = SchemaRule()
            .name { exactly("name") }

        assertThatResult(rule.validate(fixture)).isSuccess()
    }

    @Test
    @Disabled("Property 'name' is not included in equals of Schema.")
    fun `name fails`() {
        val rule = SchemaRule()
            .name { exactly("fail") }

        assertThatResult(rule.validate(fixture)).isFailure(
            ValidationFailure(
                RuleGroup.named("name", RuleGroup.Category.FIELD, "", RuleGroup.unknown()),
                "Was supposed to be 'fail' but is 'name'",
            ),
        )
    }

    @Test
    fun `title succeeds`() {
        val rule = SchemaRule()
            .title { exactly("title") }

        assertThatResult(rule.validate(fixture)).isSuccess()
    }

    @Test
    fun `title fails`() {
        val rule = SchemaRule()
            .title { exactly("fail") }

        assertThatResult(rule.validate(fixture)).isFailure(
            ValidationFailure(
                RuleGroup.named("title", RuleGroup.Category.FIELD, "", RuleGroup.unknown()),
                "Was supposed to be 'fail' but is 'title'",
            ),
        )
    }

    @Test
    fun `multipleOf succeeds`() {
        val rule = SchemaRule()
            .multipleOf { exactly(BigDecimal.ONE) }

        assertThatResult(rule.validate(fixture)).isSuccess()
    }

    @Test
    fun `multipleOf fails`() {
        val rule = SchemaRule()
            .multipleOf { exactly(BigDecimal.TEN) }

        assertThatResult(rule.validate(fixture)).isFailure(
            ValidationFailure(
                RuleGroup.named("multipleOf", RuleGroup.Category.FIELD, "", RuleGroup.unknown()),
                "Was supposed to be '10' but is '1'",
            ),
        )
    }

    @Test
    fun `maximum succeeds`() {
        val rule = SchemaRule()
            .maximum { exactly(BigDecimal.ONE) }

        assertThatResult(rule.validate(fixture)).isSuccess()
    }

    @Test
    fun `maximum fails`() {
        val rule = SchemaRule()
            .maximum { exactly(BigDecimal.TEN) }

        assertThatResult(rule.validate(fixture)).isFailure(
            ValidationFailure(
                RuleGroup.named("maximum", RuleGroup.Category.FIELD, "", RuleGroup.unknown()),
                "Was supposed to be '10' but is '1'",
            ),
        )
    }

    @Test
    fun `exclusiveMaximum succeeds`() {
        val rule = SchemaRule()
            .exclusiveMaximum { isTrue() }

        assertThatResult(rule.validate(fixture)).isSuccess()
    }

    @Test
    fun `exclusiveMaximum fails`() {
        val rule = SchemaRule()
            .exclusiveMaximum { isFalse() }

        assertThatResult(rule.validate(fixture)).isFailure(
            ValidationFailure(
                RuleGroup.named("exclusiveMaximum", RuleGroup.Category.FIELD, "", RuleGroup.unknown()),
                "Was supposed to be 'false' but is 'true'",
            ),
        )
    }

    @Test
    fun `minimum succeeds`() {
        val rule = SchemaRule()
            .minimum { exactly(BigDecimal.ONE) }

        assertThatResult(rule.validate(fixture)).isSuccess()
    }

    @Test
    fun `minimum fails`() {
        val rule = SchemaRule()
            .minimum { exactly(BigDecimal.TEN) }

        assertThatResult(rule.validate(fixture)).isFailure(
            ValidationFailure(
                RuleGroup.named("minimum", RuleGroup.Category.FIELD, "", RuleGroup.unknown()),
                "Was supposed to be '10' but is '1'",
            ),
        )
    }

    @Test
    fun `exclusiveMinimum succeeds`() {
        val rule = SchemaRule()
            .exclusiveMinimum { isTrue() }

        assertThatResult(rule.validate(fixture)).isSuccess()
    }

    @Test
    fun `exclusiveMinimum fails`() {
        val rule = SchemaRule()
            .exclusiveMinimum { isFalse() }

        assertThatResult(rule.validate(fixture)).isFailure(
            ValidationFailure(
                RuleGroup.named("exclusiveMinimum", RuleGroup.Category.FIELD, "", RuleGroup.unknown()),
                "Was supposed to be 'false' but is 'true'",
            ),
        )
    }

    @Test
    fun `maxLength succeeds`() {
        val rule = SchemaRule()
            .maxLength { exactly(1) }

        assertThatResult(rule.validate(fixture)).isSuccess()
    }

    @Test
    fun `maxLength fails`() {
        val rule = SchemaRule()
            .maxLength { exactly(2) }

        assertThatResult(rule.validate(fixture)).isFailure(
            ValidationFailure(
                RuleGroup.named("maxLength", RuleGroup.Category.FIELD, "", RuleGroup.unknown()),
                "Was supposed to be '2' but is '1'",
            ),
        )
    }

    @Test
    fun `minLength succeeds`() {
        val rule = SchemaRule()
            .minLength { exactly(1) }

        assertThatResult(rule.validate(fixture)).isSuccess()
    }

    @Test
    fun `minLength fails`() {
        val rule = SchemaRule()
            .minLength { exactly(2) }

        assertThatResult(rule.validate(fixture)).isFailure(
            ValidationFailure(
                RuleGroup.named("minLength", RuleGroup.Category.FIELD, "", RuleGroup.unknown()),
                "Was supposed to be '2' but is '1'",
            ),
        )
    }

    @Test
    fun `pattern succeeds`() {
        val rule = SchemaRule()
            .pattern { exactly("pattern") }

        assertThatResult(rule.validate(fixture)).isSuccess()
    }

    @Test
    fun `pattern fails`() {
        val rule = SchemaRule()
            .pattern { exactly("fail") }

        assertThatResult(rule.validate(fixture)).isFailure(
            ValidationFailure(
                RuleGroup.named("pattern", RuleGroup.Category.FIELD, "", RuleGroup.unknown()),
                "Was supposed to be 'fail' but is 'pattern'",
            ),
        )
    }

    @Test
    fun `maxItems succeeds`() {
        val rule = SchemaRule()
            .minLength { exactly(1) }

        assertThatResult(rule.validate(fixture)).isSuccess()
    }

    @Test
    fun `maxItems fails`() {
        val rule = SchemaRule()
            .maxItems { exactly(2) }

        assertThatResult(rule.validate(fixture)).isFailure(
            ValidationFailure(
                RuleGroup.named("maxItems", RuleGroup.Category.FIELD, "", RuleGroup.unknown()),
                "Was supposed to be '2' but is '1'",
            ),
        )
    }

    @Test
    fun `minItems succeeds`() {
        val rule = SchemaRule()
            .minItems { exactly(1) }

        assertThatResult(rule.validate(fixture)).isSuccess()
    }

    @Test
    fun `minItems fails`() {
        val rule = SchemaRule()
            .minItems { exactly(2) }

        assertThatResult(rule.validate(fixture)).isFailure(
            ValidationFailure(
                RuleGroup.named("minItems", RuleGroup.Category.FIELD, "", RuleGroup.unknown()),
                "Was supposed to be '2' but is '1'",
            ),
        )
    }

    @Test
    fun `uniqueItems succeeds`() {
        val rule = SchemaRule()
            .uniqueItems { isTrue() }

        assertThatResult(rule.validate(fixture)).isSuccess()
    }

    @Test
    fun `uniqueItems fails`() {
        val rule = SchemaRule()
            .uniqueItems { isFalse() }

        assertThatResult(rule.validate(fixture)).isFailure(
            ValidationFailure(
                RuleGroup.named("uniqueItems", RuleGroup.Category.FIELD, "", RuleGroup.unknown()),
                "Was supposed to be 'false' but is 'true'",
            ),
        )
    }

    @Test
    fun `maxProperties succeeds`() {
        val rule = SchemaRule()
            .maxProperties { exactly(1) }

        assertThatResult(rule.validate(fixture)).isSuccess()
    }

    @Test
    fun `maxProperties fails`() {
        val rule = SchemaRule()
            .maxProperties { exactly(2) }

        assertThatResult(rule.validate(fixture)).isFailure(
            ValidationFailure(
                RuleGroup.named("maxProperties", RuleGroup.Category.FIELD, "", RuleGroup.unknown()),
                "Was supposed to be '2' but is '1'",
            ),
        )
    }

    @Test
    fun `minProperties succeeds`() {
        val rule = SchemaRule()
            .minProperties { exactly(1) }

        assertThatResult(rule.validate(fixture)).isSuccess()
    }

    @Test
    fun `minProperties fails`() {
        val rule = SchemaRule()
            .minProperties { exactly(2) }

        assertThatResult(rule.validate(fixture)).isFailure(
            ValidationFailure(
                RuleGroup.named("minProperties", RuleGroup.Category.FIELD, "", RuleGroup.unknown()),
                "Was supposed to be '2' but is '1'",
            ),
        )
    }

    @Test
    fun `required succeeds`() {
        val rule = SchemaRule()
            .required { exactly(listOf("property")) }

        assertThatResult(rule.validate(fixture)).isSuccess()
    }

    @Test
    fun `required fails`() {
        val rule = SchemaRule()
            .required { exactly(listOf("fail")) }

        assertThatResult(rule.validate(fixture)).isFailure(
            ValidationFailure(
                RuleGroup.named("required", RuleGroup.Category.FIELD, "", RuleGroup.unknown()),
                "Was supposed to be '[fail]' but is '[property]'",
            ),
        )
    }

    @Test
    fun `type succeeds`() {
        val rule = SchemaRule()
            .type { exactly("type") }

        assertThatResult(rule.validate(fixture)).isSuccess()
    }

    @Test
    fun `type fails`() {
        val rule = SchemaRule()
            .type { exactly("fail") }

        assertThatResult(rule.validate(fixture)).isFailure(
            ValidationFailure(
                RuleGroup.named("type", RuleGroup.Category.FIELD, "", RuleGroup.unknown()),
                "Was supposed to be 'fail' but is 'type'",
            ),
        )
    }

    @Test
    fun `not succeeds`() {
        val rule = SchemaRule()
            .not { exactly(Schema<String>()) }

        assertThatResult(rule.validate(fixture)).isSuccess()
    }

    @Test
    fun `not fails`() {
        val rule = SchemaRule()
            .not { exactly(Schema<String>().apply { description = "fail" }) }

        assertThatResult(rule.validate(fixture)).isFailure(
            RuleGroup.named("not", RuleGroup.Category.GROUP, "", RuleGroup.unknown()),
        )
    }

    @Test
    fun `properties succeeds`() {
        val rule = SchemaRule()
            .properties { exactly(listOf("schema" to Schema<String>())) }

        assertThatResult(rule.validate(fixture)).isSuccess()
    }

    @Test
    fun `properties fails`() {
        val rule = SchemaRule()
            .properties { exactly(listOf("fail" to Schema<String>().apply { description = "fail" })) }

        assertThatResult(rule.validate(fixture)).isFailure(
            RuleGroup.named("properties", RuleGroup.Category.GROUP, "", RuleGroup.unknown()),
        )
    }

    @Test
    fun `description succeeds`() {
        val rule = SchemaRule()
            .description { exactly("description") }

        assertThatResult(rule.validate(fixture)).isSuccess()
    }

    @Test
    fun `description fails`() {
        val rule = SchemaRule()
            .description { exactly("fail") }

        assertThatResult(rule.validate(fixture)).isFailure(
            ValidationFailure(
                RuleGroup.named("description", RuleGroup.Category.FIELD, "", RuleGroup.unknown()),
                "Was supposed to be 'fail' but is 'description'",
            ),
        )
    }

    @Test
    fun `format succeeds`() {
        val rule = SchemaRule()
            .format { exactly("format") }

        assertThatResult(rule.validate(fixture)).isSuccess()
    }

    @Test
    fun `format fails`() {
        val rule = SchemaRule()
            .format { exactly("fail") }

        assertThatResult(rule.validate(fixture)).isFailure(
            ValidationFailure(
                RuleGroup.named("format", RuleGroup.Category.FIELD, "", RuleGroup.unknown()),
                "Was supposed to be 'fail' but is 'format'",
            ),
        )
    }

    @Test
    fun `ref succeeds`() {
        val rule = SchemaRule()
            .ref { exactly("#/components/schemas/ref") }

        assertThatResult(rule.validate(fixture)).isSuccess()
    }

    @Test
    fun `ref fails`() {
        val rule = SchemaRule()
            .ref { exactly("fail") }

        assertThatResult(rule.validate(fixture)).isFailure(
            ValidationFailure(
                RuleGroup.named("ref", RuleGroup.Category.FIELD, "", RuleGroup.unknown()),
                "Was supposed to be 'fail' but is '#/components/schemas/ref'",
            ),
        )
    }

    @Test
    fun `nullable succeeds`() {
        val rule = SchemaRule()
            .nullable { isTrue() }

        assertThatResult(rule.validate(fixture)).isSuccess()
    }

    @Test
    fun `nullable fails`() {
        val rule = SchemaRule()
            .nullable { isFalse() }

        assertThatResult(rule.validate(fixture)).isFailure(
            ValidationFailure(
                RuleGroup.named("nullable", RuleGroup.Category.FIELD, "", RuleGroup.unknown()),
                "Was supposed to be 'false' but is 'true'",
            ),
        )
    }

    @Test
    fun `readOnly succeeds`() {
        val rule = SchemaRule()
            .readOnly { isTrue() }

        assertThatResult(rule.validate(fixture)).isSuccess()
    }

    @Test
    fun `readOnly fails`() {
        val rule = SchemaRule()
            .readOnly { isFalse() }

        assertThatResult(rule.validate(fixture)).isFailure(
            ValidationFailure(
                RuleGroup.named("readOnly", RuleGroup.Category.FIELD, "", RuleGroup.unknown()),
                "Was supposed to be 'false' but is 'true'",
            ),
        )
    }

    @Test
    fun `writeOnly succeeds`() {
        val rule = SchemaRule()
            .writeOnly { isTrue() }

        assertThatResult(rule.validate(fixture)).isSuccess()
    }

    @Test
    fun `writeOnly fails`() {
        val rule = SchemaRule()
            .writeOnly { isFalse() }

        assertThatResult(rule.validate(fixture)).isFailure(
            ValidationFailure(
                RuleGroup.named("writeOnly", RuleGroup.Category.FIELD, "", RuleGroup.unknown()),
                "Was supposed to be 'false' but is 'true'",
            ),
        )
    }

    @Test
    fun `externalDocs succeeds`() {
        val rule = SchemaRule()
            .externalDocs { exactly(ExternalDocumentation().apply { url = "http://localhost" }) }

        assertThatResult(rule.validate(fixture)).isSuccess()
    }

    @Test
    fun `externalDocs fails`() {
        val rule = SchemaRule()
            .externalDocs { exactly(ExternalDocumentation().apply { url = "https://localhost" }) }

        assertThatResult(rule.validate(fixture)).isFailure(
            RuleGroup.named("externalDocs", RuleGroup.Category.GROUP, "", RuleGroup.unknown()),
        )
    }

    @Test
    fun `deprecated succeeds`() {
        val rule = SchemaRule()
            .deprecated { isTrue() }

        assertThatResult(rule.validate(fixture)).isSuccess()
    }

    @Test
    fun `deprecated fails`() {
        val rule = SchemaRule()
            .deprecated { isFalse() }

        assertThatResult(rule.validate(fixture)).isFailure(
            ValidationFailure(
                RuleGroup.named("deprecated", RuleGroup.Category.FIELD, "", RuleGroup.unknown()),
                "Was supposed to be 'false' but is 'true'",
            ),
        )
    }

    @Test
    fun `items succeeds`() {
        val rule = SchemaRule()
            .items { exactly(Schema<String>()) }

        assertThatResult(rule.validate(fixture)).isSuccess()
    }

    @Test
    fun `items fails`() {
        val rule = SchemaRule()
            .items { exactly(Schema<String>().apply { title = "fail" }) }

        assertThatResult(rule.validate(fixture)).isFailure(
            RuleGroup.named("items", RuleGroup.Category.GROUP, "", RuleGroup.unknown()),
        )
    }

    @Test
    fun `xml succeeds`() {
        val rule = SchemaRule()
            .xml { exactly(XML()) }

        assertThatResult(rule.validate(fixture)).isSuccess()
    }

    @Test
    fun `xml fails`() {
        val rule = SchemaRule()
            .xml { exactly(XML().apply { name = "fail" }) }

        assertThatResult(rule.validate(fixture)).isFailure(
            RuleGroup.named("xml", RuleGroup.Category.GROUP, "", RuleGroup.unknown()),
        )
    }

    @Test
    fun `discriminator succeeds`() {
        val rule = SchemaRule()
            .discriminator { exactly(Discriminator()) }

        assertThatResult(rule.validate(fixture)).isSuccess()
    }

    @Test
    fun `discriminator fails`() {
        val rule = SchemaRule()
            .discriminator { exactly(Discriminator().apply { propertyName = "fail" }) }

        assertThatResult(rule.validate(fixture)).isFailure(
            RuleGroup.named("discriminator", RuleGroup.Category.GROUP, "", RuleGroup.unknown()),
        )
    }

    @Test
    fun `allOf succeeds`() {
        val rule = SchemaRule()
            .allOf { exactly(listOf("allOf" to Schema<String>())) }

        assertThatResult(rule.validate(fixture)).isSuccess()
    }

    @Test
    fun `allOf fails`() {
        val rule = SchemaRule()
            .allOf { exactly(listOf("fail" to Schema<String>())) }

        assertThatResult(rule.validate(fixture)).isFailure(
            RuleGroup.named("allOf", RuleGroup.Category.GROUP, "", RuleGroup.unknown()),
        )
    }

    @Test
    fun `oneOf succeeds`() {
        val rule = SchemaRule()
            .oneOf { exactly(listOf("oneOf" to Schema<String>())) }

        assertThatResult(rule.validate(fixture)).isSuccess()
    }

    @Test
    fun `oneOf fails`() {
        val rule = SchemaRule()
            .oneOf { exactly(listOf("fail" to Schema<String>())) }

        assertThatResult(rule.validate(fixture)).isFailure(
            RuleGroup.named("oneOf", RuleGroup.Category.GROUP, "", RuleGroup.unknown()),
        )
    }

    @Test
    fun `anyOf succeeds`() {
        val rule = SchemaRule()
            .anyOf { exactly(listOf("anyOf" to Schema<String>())) }

        assertThatResult(rule.validate(fixture)).isSuccess()
    }

    @Test
    fun `anyOf fails`() {
        val rule = SchemaRule()
            .anyOf { exactly(listOf("fail" to Schema<String>())) }

        assertThatResult(rule.validate(fixture)).isFailure(
            RuleGroup.named("anyOf", RuleGroup.Category.GROUP, "", RuleGroup.unknown()),
        )
    }

    companion object {
        val fixture = Schema<String>().apply {
            name = "name"
            title = "title"
            description = "description"
            multipleOf = BigDecimal.ONE
            maximum = BigDecimal.ONE
            exclusiveMaximum = true
            minimum = BigDecimal.ONE
            exclusiveMinimum = true
            maxLength = 1
            minLength = 1
            pattern = "pattern"
            maxItems = 1
            minItems = 1
            uniqueItems = true
            maxProperties = 1
            minProperties = 1
            required = listOf("property")
            type = "type"
            not = Schema<String>()
            properties = mapOf("schema" to Schema<String>())
            format = "format"
            `$ref` = "ref"
            nullable = true
            readOnly = true
            writeOnly = true
            externalDocs = ExternalDocumentation().apply { url = "http://localhost" }
            deprecated = true
            items = Schema<String>()
            xml = XML()
            discriminator = Discriminator()
            allOf = mutableListOf<Schema<*>>(Schema<String>().apply { name = "allOf" })
            oneOf = mutableListOf<Schema<*>>(Schema<String>().apply { name = "oneOf" })
            anyOf = mutableListOf<Schema<*>>(Schema<String>().apply { name = "anyOf" })
        }
    }
}
