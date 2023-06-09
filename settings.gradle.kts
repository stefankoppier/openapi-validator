rootProject.name = "openapi-validator"

dependencyResolutionManagement {
    repositories {
        mavenLocal()
        mavenCentral()
    }
}

pluginManagement {
    repositories {
        mavenLocal()
        gradlePluginPortal()
    }
}

include(":core")
//include(":generator")
include(":integration-junit")
include(":integration-gradle")
