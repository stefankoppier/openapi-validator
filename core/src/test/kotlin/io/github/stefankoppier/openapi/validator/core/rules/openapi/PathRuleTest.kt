package io.github.stefankoppier.openapi.validator.core.rules.openapi

import io.swagger.v3.oas.models.PathItem
import org.assertj.core.api.Assertions.assertThat
import kotlin.test.Test
import kotlin.test.assertTrue

class PathRuleTest {

    @Test
    fun `path with all properties failing`() {
        val result = PathRule()
            .summary { required() }
            .description { required() }
            .validate(PathItem())

        assertTrue { result.isFailure }
        assertThat(result.failures).hasSize(2)
    }

    @Test
    fun `summary required with a summary`() {
        val result = PathRule()
            .summary { required() }
            .validate(PathItem().summary("Summary"))

        assertTrue { result.isSuccess }
    }

    @Test
    fun `summary required without a summary`() {
        val result = PathRule()
            .summary { required() }
            .validate(PathItem())

        assertTrue { result.isFailure }
        assertThat(result.failures).hasSize(1)
    }

    @Test
    fun `summary required and lowercase with a summary`() {
        val result = PathRule()
            .summary { required(); lowercase() }
            .validate(PathItem().summary("Summary"))

        assertTrue { result.isFailure }
        assertThat(result.failures).hasSize(1)
    }
}