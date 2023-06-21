package io.github.stefankoppier.openapi.validator.junit

import io.github.stefankoppier.openapi.validator.core.rules.openapi.openAPI
import io.swagger.v3.oas.models.OpenAPI
import org.junit.jupiter.api.extension.ExtendWith
import java.time.LocalDate
import kotlin.test.Test

@ExtendWith(OpenAPIValidationExtension::class)
@OpenAPITest(relativeUrl = "src/test/resources/petstore.yaml")
class OpenAPIValidationExtensionTest(private val document: OpenAPI) {

    @Test
    fun `test extension`() {
        assertDocumentIsValidFor(
            document,
            openAPI("My specification") {
                since(LocalDate.MAX) {
                    info {
                        title { exactly("OpenAPI Peatstore") }
                    }
                }
            },
        )
    }
}
