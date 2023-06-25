plugins {
    id("java-test-fixtures")
    id("publication-convention")
}

description = "Core module of the OpenAPI validator project."

dependencies {
    api(libs.swagger.models)

    implementation(libs.swagger.parser)

    testFixturesImplementation(libs.assertj.core)

    integrationTestImplementation(libs.guava)
}