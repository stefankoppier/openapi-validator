rootProject.name = "openapi-validator"

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

include(":core")
//include(":generator")
include(":integration-junit")
