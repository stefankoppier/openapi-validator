import io.github.stefankoppier.openapi.validator.core.rules.openapi.*

plugins {
    id("java")
    alias(libs.plugins.kotlin.jvm)
    id("io.github.stefankoppier.openapi.validator") version "0.0.1"
}

openAPIValidate {
    document.set(uri("petstore.yaml"))
    rules.set(
        openAPI {
            info {
                title { exactly("OpenAPI Petstore") }
            }
        }
    )
}