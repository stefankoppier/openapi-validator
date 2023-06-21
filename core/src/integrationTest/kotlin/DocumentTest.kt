import com.google.common.io.Resources
import io.github.stefankoppier.openapi.validator.core.Validator
import io.github.stefankoppier.openapi.validator.core.rules.openapi.openAPI
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.nio.charset.StandardCharsets

class DocumentTest {

    @Test
    fun `all tags have a description`() {
        val rule = openAPI {
            tags {
                all {
                    description {
                        required()
                    }
                }
            }
        }

        val result = Validator(rule)
            .validate(Resources.toString(Resources.getResource("petstore.yaml"), StandardCharsets.UTF_8))

        assertThat(result.failures)
            .withFailMessage { result.summarize() }
            .hasSize(1)
    }
}
