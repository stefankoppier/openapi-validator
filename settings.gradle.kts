rootProject.name = "openapi-validator"

include(":core")
//include(":generator")
include(":integration-junit")

dependencyResolutionManagement {
    repositories {
        mavenLocal()
        mavenCentral()
    }
}

pluginManagement {
    repositories {
        gradlePluginPortal()
    }
}