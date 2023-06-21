import org.jetbrains.dokka.gradle.DokkaTaskPartial
import org.jetbrains.kotlin.js.inline.clean.removeUnusedImports
import java.net.URL

plugins {
    id("jacoco-report-aggregation")
    alias(libs.plugins.kotlin.jvm)
    alias(libs.plugins.dokka)
    alias(libs.plugins.spotless)
    alias(libs.plugins.sonarqube)
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
        apply(plugin = "com.diffplug.spotless")

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

        tasks.withType<DokkaTaskPartial>().configureEach {
            dokkaSourceSets {
                configureEach {
                    includes.from("$projectDir/Dokka.md")
                    jdkVersion.set(javaVersion)
                    sourceLink {
                        localDirectory.set(file("$projectDir/src/main/kotlin"))
                        remoteUrl.set(URL("https://github.com/stefankoppier/openapi-validator/tree/main/${project.name}/src/main/kotlin"))
                    }
                }
            }
        }

        spotless {
            kotlin {
                ktlint().setEditorConfigPath("$rootDir/.editorconfig")
            }
        }
    }
}

tasks.dokkaHtmlMultiModule.configure {
    includes.from("$projectDir/Dokka.md")
}

tasks.check {
    dependsOn(tasks.testCodeCoverageReport)
}

sonar {
    properties {
        property("sonar.projectKey", "stefankoppier_openapi-validator")
        property("sonar.organization", "stefankoppier")
        property("sonar.host.url", "https://sonarcloud.io")
        property("sonar.java.coveragePlugin", "jacoco")
        property("sonar.coverage.jacoco.xmlReportPaths", "$buildDir/reports/jacoco/testCodeCoverageReport/testCodeCoverageReport.xml")
    }
}
