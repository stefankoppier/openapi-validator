package io.github.stefankoppier.openapi.validator.core.rules.openapi

import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.tags.Tag
import org.assertj.core.api.Assertions.assertThat
import kotlin.test.Test
import kotlin.test.assertTrue

class OpenAPIRuleTest {

    @Test
    fun `openAPI valid openapi`() {
        val result = OpenAPIRule()
            .validate(OpenAPI().openapi("3.0.0"))
        assertTrue { result.isSuccess }
    }

    @Test
    fun `openAPI invalid openapi`() {
        val result = OpenAPIRule()
            .validate(OpenAPI().openapi("3"))
        assertTrue { result.isFailure }
        assertThat(result.failures).hasSize(1)
    }

    @Test
    fun `openAPI tags`() {
        val result = OpenAPIRule()
            .tags { all(predicate = { it.description != null }) }
            .tags { any(predicate = { it.description == "Description" }) }
            .validate(OpenAPI().tags(listOf(
                Tag().name(null).description(null),
                Tag().name("Name").description("Description"),
            )))
        assertTrue { result.isFailure }
        assertThat(result.failures).hasSize(1)
    }
}