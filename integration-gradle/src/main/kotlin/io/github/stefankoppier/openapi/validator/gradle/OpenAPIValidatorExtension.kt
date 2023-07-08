package io.github.stefankoppier.openapi.validator.gradle

import io.github.stefankoppier.openapi.validator.core.rules.openapi.OpenAPIRule
import org.gradle.api.provider.Property
import java.net.URI

abstract class OpenAPIValidatorExtension {

    abstract val document: Property<URI>

    abstract val rules: Property<OpenAPIRule>
}
