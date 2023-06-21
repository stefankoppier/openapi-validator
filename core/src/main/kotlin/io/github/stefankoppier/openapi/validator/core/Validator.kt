package io.github.stefankoppier.openapi.validator.core

import io.github.stefankoppier.openapi.validator.core.rules.ValidationRule
import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.parser.OpenAPIV3Parser
import io.swagger.v3.parser.core.models.ParseOptions
import java.net.URI

class Validator(private val rule: ValidationRule<OpenAPI>, private val options: Options = Options()) {

    fun validate(yaml: String) =
        parse(yaml)
            .map { rule.validate(it) }
            .getOrElse { ValidationResult(it) }

    fun validate(path: URI) =
        parse(path)
            .map { rule.validate(it) }
            .getOrElse { ValidationResult(it) }

    private fun parse(path: URI): Result<OpenAPI> {
        val document = OpenAPIV3Parser().read(path.toString(), emptyList(), options.asParseOptions())
        return if (document != null) {
            Result.success(document)
        } else {
            Result.failure(IllegalArgumentException("Failed to parse contents from '$path'"))
        }
    }

    fun parse(yaml: String): Result<OpenAPI> {
        val document = OpenAPIV3Parser()
            .readContents(yaml, emptyList(), options.asParseOptions())
            ?.openAPI
        return if (document != null) {
            Result.success(document)
        } else {
            Result.failure(IllegalArgumentException("Failed to parse yaml"))
        }
    }

    data class Options(val resolve: Boolean = true) {

        internal fun asParseOptions() =
            ParseOptions().apply {
                isResolve = resolve
            }
    }
}
