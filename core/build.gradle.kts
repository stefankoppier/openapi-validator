plugins {
    id("java-test-fixtures")
    alias(libs.plugins.kotlin.jvm)
}

description = "Core module of the OpenAPI validator project."

dependencies {
    api(libs.swagger.models)

    implementation(libs.swagger.parser)

    testFixturesImplementation(libs.assertj.core)

    integrationTestImplementation(libs.guava)
}