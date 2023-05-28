package io.github.stefankoppier.openapi.validator.core.rules.openapi

import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.tags.Tag
import org.assertj.core.api.Assertions
import kotlin.test.Test
import kotlin.test.assertTrue

class OpenAPIRuleTest {

    @Test
    fun `openAPI tags`() {
        val result = OpenAPIRule()
            .tags { all { it.name != null } }
            .tags { any { it.description == "Description" } }
            .validate(OpenAPI().tags(listOf(
                Tag().name(null).description(null),
                Tag().name("Name").description("Description"),
            )))
        assertTrue { result.isFailure }
        Assertions.assertThat(result.failures).hasSize(1)
    }
}