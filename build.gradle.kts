plugins {
    alias(libs.plugins.kotlin.jvm)
}

group = "io.github.stefankoppier"
version = "1.0-SNAPSHOT"

allprojects {
    repositories {
        mavenCentral()
    }

    plugins.withId("org.jetbrains.kotlin.jvm") {
        kotlin {
            jvmToolchain {
                languageVersion.set(JavaLanguageVersion.of(17))
            }
        }

        tasks.test {
            useJUnitPlatform()
        }

        dependencies {
            implementation(libs.kotlin.stdlib.jdk8)

            testImplementation(kotlin("test"))
            testImplementation(libs.assertj.core)
        }
    }
}