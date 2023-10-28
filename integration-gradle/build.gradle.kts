plugins {
    id("java-gradle-plugin")
    alias(libs.plugins.kotlin.jvm)
}

description = "Gradle integration for the OpenAPI validator project."


dependencies {
    api(project(":core"))

    integrationTestImplementation(gradleTestKit())
}

gradlePlugin {
    plugins {
        create("openapi-validator") {
            id = "io.github.stefankoppier.openapi.validator"
            displayName = "openapi-validator"
            version = project.version
            tags = listOf("OpenAPI", "api", "specification", "validation")
            implementationClass = "io.github.stefankoppier.openapi.validator.gradle.OpenAPIValidatorPlugin"
        }
    }
}