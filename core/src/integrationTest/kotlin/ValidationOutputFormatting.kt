import com.google.common.io.Resources
import io.github.stefankoppier.openapi.validator.Parser
import io.github.stefankoppier.openapi.validator.Validator
import io.github.stefankoppier.openapi.validator.rules.openapi.openAPI
import org.assertj.core.api.Assertions.assertThat
import java.nio.charset.StandardCharsets
import kotlin.test.Test

class ValidationOutputFormatting {

    @Test
    fun `output looks good`() {
        val rule = openAPI {
            info {
                title { exactly("Different title") }
                contact {
                    name { exactly("Different name") }
                }
            }
        }

        val openAPI = Parser()
            .parse(Resources.toString(Resources.getResource("petstore.yaml"), StandardCharsets.UTF_8))
            .getOrThrow()
        val result = Validator(rule).validate(openAPI)

        assertThat(result.failures).isNotEmpty()
        assertThat(result.summarize()).isEqualTo("""
            |For object document:
            |    For object info:
            |        Field title does not comply:
            |            - Was supposed to be 'Different title' but is 'OpenAPI Petstore'
            |        For object contact:
            |            Field name does not comply:
            |                - Was supposed to be 'Different name' but is 'Swagger'
            |""".trimMargin())
    }
}