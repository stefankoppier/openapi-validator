import com.google.common.io.Resources
import io.github.stefankoppier.openapi.validator.core.Parser
import io.github.stefankoppier.openapi.validator.core.Validator
import io.github.stefankoppier.openapi.validator.core.rules.openapi.openAPI
import org.apache.commons.io.Charsets
import org.assertj.core.api.Assertions.assertThat
import kotlin.test.Test

class OpenAPIValidation {

    @Test
    fun `validate openAPI Petstore`() {
        val rule = openAPI {
            info {
                title { exactly("OpenAPI Petstore") }
                version { exactly("1.0.0") }
                contact {
                    url { required() }
                }
            }
        }

        val openAPI = Parser()
            .parse(Resources.toString(Resources.getResource("petstore.yaml"), Charsets.UTF_8))
            .getOrThrow()
        val result = Validator(rule).validate(openAPI)

        assertThat(result.failures).isEmpty()
    }
}