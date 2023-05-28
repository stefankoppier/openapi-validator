plugins {
    alias(libs.plugins.kotlin.jvm)
}

dependencies {
    api(libs.swagger.models)
    api(libs.kotlinpoet)

    implementation(libs.swagger.parser)

    testImplementation(project(":interface-junit"))
}