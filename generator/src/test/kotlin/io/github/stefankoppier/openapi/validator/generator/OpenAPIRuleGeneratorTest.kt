package io.github.stefankoppier.openapi.validator.generator

import io.github.stefankoppier.openapi.validator.junit.OpenAPIValidationDocument
import io.github.stefankoppier.openapi.validator.junit.OpenAPIValidationExtension
import io.swagger.v3.oas.models.OpenAPI
import org.junit.jupiter.api.extension.ExtendWith
import kotlin.test.Test

@ExtendWith(OpenAPIValidationExtension::class)
@OpenAPIValidationDocument(relativeUrl = "src/test/resources/petstore.yaml")
class OpenAPIRuleGeneratorTest(private val document: OpenAPI) {

    @Test
    fun `generate exact specification`() {
        ExactOpenAPIRuleGenerator().generate(document).writeTo(System.out)
    }
}