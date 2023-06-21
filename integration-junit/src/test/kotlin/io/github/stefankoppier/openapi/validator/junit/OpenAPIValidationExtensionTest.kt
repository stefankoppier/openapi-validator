package io.github.stefankoppier.openapi.validator.junit

import io.github.stefankoppier.openapi.validator.core.rules.openapi.openAPI
import org.junit.jupiter.api.extension.ExtendWith
import kotlin.test.Test

@ExtendWith(OpenAPIValidationExtension::class)
@OpenAPITest(relativeUrl = "src/test/resources/petstore.yaml")
class OpenAPIValidationExtensionTest {

    @Test
    fun `test extension`() {
        assertDocumentIsValidFor {
            openAPI("My specification") {
                info {
                    title { exactly("OpenAPI Petstore") }
                }
            }
        }
    }
}
