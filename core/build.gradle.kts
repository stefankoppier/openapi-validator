plugins {
    id("java-test-fixtures")
    id("org.jetbrains.kotlin.jvm") version "1.8.22"
}

description = "Core module of the OpenAPI validator project."

dependencies {
    api(libs.swagger.models)

    implementation(libs.swagger.parser)

    testFixturesImplementation(libs.assertj.core)

    integrationTestImplementation(libs.guava)
}