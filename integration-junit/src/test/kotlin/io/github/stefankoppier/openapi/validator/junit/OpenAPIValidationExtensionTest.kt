package io.github.stefankoppier.openapi.validator.junit

import io.github.stefankoppier.openapi.validator.core.rules.openapi.openAPI
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.extension.ExtendWith
import org.opentest4j.AssertionFailedError
import kotlin.test.Test

@ExtendWith(OpenAPIValidationExtension::class)
@OpenAPITest(relativeUrl = "src/test/resources/petstore.yaml")
class OpenAPIValidationExtensionTest {

    @Test
    fun `test assertDocumentIsValidFor success`() {
        assertDoesNotThrow {
            assertDocumentIsValidFor {
                openAPI("My specification") {
                    info {
                        title { exactly("OpenAPI Petstore") }
                    }
                }
            }
        }
    }

    @Test
    @OpenAPITest(relativeUrl = "src/test/resources/petstore2.yaml")
    fun `test second annotation`() {
        assertDoesNotThrow {
            assertDocumentIsValidFor {
                openAPI("My specification") {
                    info {
                        title { exactly("OpenAPI Petstore 2") }
                    }
                }
            }
        }
    }

    @Test
    fun `test assertDocumentIsValidFor one error`() {
        assertThatThrownBy {
            assertDocumentIsValidFor {
                openAPI("My specification") {
                    info {
                        title { exactly("OpenAPI Peetstore") }
                    }
                }
            }
        }
            .isInstanceOf(AssertionFailedError::class.java)
            .hasMessage(
                """
                |Validation for document failed
                |There is 1 problem.
                |
                |For document (My specification):
                |    For info:
                |        Field 'title' does not comply:
                |            - Was supposed to be 'OpenAPI Peetstore' but is 'OpenAPI Petstore'
                |
                """.trimMargin(),
            )
    }

    @Test
    fun `test assertDocumentIsValidFor two errors`() {
        assertThatThrownBy {
            assertDocumentIsValidFor {
                openAPI("My specification") {
                    info {
                        title { exactly("OpenAPI Peetstore") }
                        version { exactly("2.0.0") }
                    }
                }
            }
        }
            .isInstanceOf(AssertionFailedError::class.java)
            .hasMessage(
                """
                |Validation for document failed
                |There are 2 problems.
                |
                |For document (My specification):
                |    For info:
                |        Field 'title' does not comply:
                |            - Was supposed to be 'OpenAPI Peetstore' but is 'OpenAPI Petstore'
                |        Field 'version' does not comply:
                |            - Was supposed to be '2.0.0' but is '1.0.0'
                |
                """.trimMargin(),
            )
    }
}
