import com.google.common.io.Resources
import io.github.stefankoppier.openapi.validator.core.Parser
import io.github.stefankoppier.openapi.validator.core.Validator
import io.github.stefankoppier.openapi.validator.core.rules.openapi.openAPI
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.nio.charset.StandardCharsets

class OpenAPIValidation {

    @Test
    fun `validate openAPI Petstore`() {
        val rule = openAPI {
            info {
                title { exactly("OpenAPI Petstore") }
                description { exactly("This is a sample server Petstore server. For this sample, you can use the api key `special-key` to test the authorization filters.") }
                version { exactly("1.0.0") }
                contact {
                    name { exactly("Swagger" ) }
                    url { exactly("http://petstore.swagger.io/") }
                }
                licence {
                    name { exactly("Apache-2.0" ) }
                    url { exactly("https://www.apache.org/licenses/LICENSE-2.0.html") }
                }
            }
            tags {
                contains {
                    name { exactly("pet") }
                    description { exactly("Everything about your Pets") }
                }
                contains {
                    name { exactly("store") }
                    description { exactly("Access to Petstore orders") }
                }
                contains {
                    name { exactly("user") }
                    description { exactly("Operations about user") }
                }
            }
        }

        val openAPI = Parser()
            .parse(Resources.toString(Resources.getResource("petstore.yaml"), StandardCharsets.UTF_8))
            .getOrThrow()
        val result = Validator(rule).validate(openAPI)

        assertThat(result.failures).isEmpty()
    }
}