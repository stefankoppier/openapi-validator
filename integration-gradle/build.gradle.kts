plugins {
    id("java-gradle-plugin")
    alias(libs.plugins.gradle.plugin.publish)
    alias(libs.plugins.kotlin.jvm)
}

description = "Gradle integration for the OpenAPI validator project."


dependencies {
    api(project(":core"))

    integrationTestImplementation(gradleTestKit())
}

gradlePlugin {
    website = "https://github.com/stefankoppier/openapi-validator"
    vcsUrl = "https://github.com/stefankoppier/openapi-validator.git"
    plugins {
        create("openapi-validator") {
            id = "io.github.stefankoppier.openapi.validator"
            displayName = "openapi-validator"
            description = project.description
            version = project.version
            tags = listOf("OpenAPI", "api", "specification", "validation")
            implementationClass = "io.github.stefankoppier.openapi.validator.gradle.OpenAPIValidatorPlugin"
        }
    }
}