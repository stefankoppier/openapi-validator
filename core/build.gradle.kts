plugins {
    alias(libs.plugins.kotlin.jvm)
}

dependencies {
    api(libs.swagger.models)

    implementation(libs.swagger.parser)

    integrationTestImplementation(libs.guava)
}