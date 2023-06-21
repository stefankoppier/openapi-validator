package io.github.stefankoppier.openapi.validator.junit

import io.github.stefankoppier.openapi.validator.core.rules.openapi.openAPI
import org.junit.jupiter.api.extension.ExtendWith
import java.time.LocalDate
import kotlin.test.Test

@ExtendWith(OpenAPIValidationExtension::class)
@OpenAPITest(relativeUrl = "src/test/resources/petstore.yaml")
class OpenAPIValidationExtensionTest {

    @Test
    fun `test extension`() {
        assertDocumentIsValidFor {
            openAPI("My specification") {
                since(LocalDate.MAX) {
                    info {
                        title { exactly("OpenAPI Peatstore") }
                    }
                }
            }
        }
    }
}
