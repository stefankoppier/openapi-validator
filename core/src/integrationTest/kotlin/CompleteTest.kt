import com.google.common.io.Resources
import io.github.stefankoppier.openapi.validator.core.Validator
import io.github.stefankoppier.openapi.validator.core.rules.openapi.collections.PathsRule
import io.github.stefankoppier.openapi.validator.core.rules.openapi.openAPI
import io.swagger.v3.oas.models.parameters.Parameter
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.nio.charset.StandardCharsets

class CompleteTest {

    @Test
    fun `validate openAPI Petstore`() {
        val rule = openAPI {
            info {
                title { exactly("OpenAPI Petstore") }
                description { exactly("This is a sample server Petstore server. For this sample, you can use the api key `special-key` to test the authorization filters.") }
                version { exactly("1.0.0") }
                contact {
                    name { exactly("Swagger") }
                    url { exactly("http://petstore.swagger.io/") }
                }
                licence {
                    name { exactly("Apache-2.0") }
                    url { exactly("https://www.apache.org/licenses/LICENSE-2.0.html") }
                }
            }
            tags {
                tag(named = "pet") {
                    description { exactly("Everything about your Pets") }
                }
                tag(named = "store") {
                    description { exactly("Access to Petstore orders") }
                }
                tag(named = "user") {
                    required()
                }
            }
            paths {
                `path pet`()
                `path user login`()
                `path pet findByStatus`()
            }
        }

        val result = Validator(rule)
            .validate(Resources.toString(Resources.getResource("petstore.yaml"), StandardCharsets.UTF_8))

        assertThat(result.failures)
            .withFailMessage { result.summarize() }
            .isEmpty()
    }

    private fun PathsRule.`path pet`() =
        path(named = "/pet") {
            post {
//              tags { any { it == "Add a new pet to the store" } }
                summary { exactly("Add a new pet to the store") }
                description { required(); exactly("") }
                operationId { exactly("addPet") }
                responses {
                    response(named = "200") {
                        description { exactly("successful operation") }
                        content {
                            mediaType(named = "application/json") {
                                schema {
                                    ref { exactly("#/components/schemas/Pet") }
                                }
                            }
                        }
                    }
                    response(named = "405") {
                        description { exactly("Invalid input") }
                    }
                }
            }

            put {
//              tags { any { it == "pet" } }
                summary { exactly("Update an existing pet") }
                description { exactly("") }
                operationId { exactly("updatePet") }
                externalDocs {
                    url { exactly("http://petstore.swagger.io/v2/doc/updatePet") }
                    description { exactly("API documentation for the updatePet operation") }
                }
                responses {
                    response(named = "200") {
                        description { exactly("successful operation") }
                        content {
                            mediaType(named = "application/xml") {
                                schema {
                                    ref { exactly("#/components/schemas/Pet") }
                                }
                            }
                        }
                    }
                }
            }
        }

    private fun PathsRule.`path pet findByStatus`() =
        path(named = "/pet/findByStatus") {
            get {
                parameters {
                    parameter(named = "status") {
                        `in` { exactly("query") }
                        style { exactly(Parameter.StyleEnum.FORM) }
                        explode { exactly(false) }
                        deprecated { exactly(true) }
                        schema {
                            type { exactly("array") }
                            items {
                                type { exactly("string") }
                            }
                        }
                    }
                }
            }
        }

    private fun PathsRule.`path user login`() =
        path(named = "/user/login") {
            get {
                parameters {
                    parameter(named = "username") {
                        `in` { exactly("query") }
                        description { exactly("The user name for login") }
                        required { exactly(true) }
                        schema {
                            type { exactly("string") }
                            pattern { exactly("^[a-zA-Z0-9]+[a-zA-Z0-9\\.\\-_]*[a-zA-Z0-9]+\$") }
                        }
                    }
                }
            }
        }
}
