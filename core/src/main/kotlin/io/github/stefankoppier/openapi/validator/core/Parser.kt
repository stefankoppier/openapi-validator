package io.github.stefankoppier.openapi.validator.core

import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.parser.OpenAPIV3Parser
import java.net.URI

class Parser {

    fun parse(path: URI): Result<OpenAPI> {
        val openAPI: OpenAPI? = OpenAPIV3Parser().read(path.toString())
        return if (openAPI != null)  {
            Result.success(openAPI)
        } else {
            Result.failure(IllegalArgumentException("Failed to parse from '$path'"))
        }
    }

    fun parse(yaml: String): Result<OpenAPI> {
        val openAPI: OpenAPI? = OpenAPIV3Parser().readContents(yaml)?.openAPI
        return if (openAPI != null)  {
            Result.success(openAPI)
        } else {
            Result.failure(IllegalArgumentException("Failed to parse contents"))
        }
    }
}