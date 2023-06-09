import com.google.common.io.Resources
import io.github.stefankoppier.openapi.validator.core.Validator
import io.github.stefankoppier.openapi.validator.core.rules.openapi.openAPI
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.nio.charset.StandardCharsets

class OutputFormattingTest {

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

        val result = Validator(rule)
            .validate(Resources.toString(Resources.getResource("petstore.yaml"), StandardCharsets.UTF_8))

        assertThat(result.failures).isNotEmpty()
        assertThat(result.summarize()).isEqualTo(
            """
            |For document:
            |    For info:
            |        Field 'title' does not comply:
            |            - Was supposed to be 'Different title' but is 'OpenAPI Petstore'
            |        For contact:
            |            Field 'name' does not comply:
            |                - Was supposed to be 'Different name' but is 'Swagger'
            |
            """.trimMargin(),
        )
    }
}
