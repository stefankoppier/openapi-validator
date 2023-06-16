import org.jetbrains.dokka.gradle.DokkaTask

plugins {
    id("jacoco-report-aggregation")
    alias(libs.plugins.kotlin.jvm)
    alias(libs.plugins.dokka)
}

group = "io.github.stefankoppier"
version = "1.0-SNAPSHOT"

val javaVersion = 11

dependencies {
    implementation(project(":core"))
    implementation(project(":integration-junit"))
}

allprojects {
    repositories {
        mavenCentral()
    }

    plugins.withId("org.jetbrains.kotlin.jvm") {
        apply(plugin = "org.jetbrains.dokka")
        apply(plugin = "jvm-test-suite")
        apply(plugin = "jacoco")

        kotlin {
            jvmToolchain {
                languageVersion.set(JavaLanguageVersion.of(javaVersion))
            }
        }

        dependencies {
            dokkaHtmlPlugin(libs.dokka.versioning.plugin)

            implementation(libs.kotlin.stdlib)
        }

        testing {
            suites {
                withType(JvmTestSuite::class).configureEach {
                    useJUnitJupiter()

                    dependencies {
                        implementation.bundle(libs.bundles.testing)
                    }
                }

                val test by getting(JvmTestSuite::class)

                register<JvmTestSuite>("integrationTest") {
                    dependencies {
                        implementation(project())
                    }

                    targets {
                        all {
                            testTask.configure { shouldRunAfter(test) }
                        }
                    }
                }
            }
        }

        tasks.check {
            dependsOn(testing.suites.named("integrationTest"))
        }

        tasks.withType<DokkaTask>().configureEach {
            dokkaSourceSets {
                configureEach {
                    jdkVersion.set(javaVersion)
                    includes.from(
                        "$rootDir/Dokka.md"
                    )
                }
            }
        }
    }
}

tasks.check {
    dependsOn(tasks.testCodeCoverageReport)
}