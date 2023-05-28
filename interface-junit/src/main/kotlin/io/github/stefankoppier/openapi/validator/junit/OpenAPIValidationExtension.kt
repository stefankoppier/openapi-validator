package io.github.stefankoppier.openapi.validator.junit

import io.github.stefankoppier.openapi.validator.core.Parser
import io.swagger.v3.oas.models.OpenAPI
import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.api.extension.ParameterContext
import org.junit.jupiter.api.extension.ParameterResolutionException
import org.junit.jupiter.api.extension.ParameterResolver
import java.net.URI
import java.nio.file.Paths
import kotlin.jvm.optionals.getOrNull

class OpenAPIValidationExtension : ParameterResolver {

    override fun supportsParameter(parameterContext: ParameterContext, extensionContext: ExtensionContext): Boolean {
        return parameterContext.parameter.type == OpenAPI::class.java
    }

    override fun resolveParameter(parameterContext: ParameterContext, extensionContext: ExtensionContext): Any {
        val element = extensionContext.element
            .getOrNull() as? Class<*>
            ?: throw ParameterResolutionException("Could not find element")

        val annotation = element
            .getAnnotation(OpenAPIValidationDocument::class.java)
            ?: throw ParameterResolutionException("Please annotate class '${element.name}' with '@${OpenAPIValidationDocument::class.simpleName}")

        val uri = runCatching { Paths.get(annotation.relativeUrl).toUri() }
            .getOrElse { throw ParameterResolutionException("Could not parse uri '${annotation.relativeUrl}'", it) }

        return Parser().parse(uri).getOrThrow()
    }
}