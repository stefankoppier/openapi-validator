import org.jetbrains.dokka.gradle.DokkaTaskPartial
import java.net.URL

plugins {
    id("jacoco-report-aggregation")
    id("maven-publish")
    id("signing")
    id(libs.plugins.kotlin.jvm.get().pluginId)
    alias(libs.plugins.dokka)
    alias(libs.plugins.spotless)
    alias(libs.plugins.sonarqube)
}

val javaVersion = 11

dependencies {
    implementation(project(":core"))
    implementation(project(":integration-junit"))
}

allprojects {
    plugins.withId("org.jetbrains.kotlin.jvm") {
        apply(plugin = "jvm-test-suite")
        apply(plugin = "jacoco")
        apply(plugin = libs.plugins.dokka.get().pluginId)
        apply(plugin = libs.plugins.spotless.get().pluginId)

        kotlin {
            jvmToolchain {
                languageVersion.set(JavaLanguageVersion.of(javaVersion))
                vendor.set(JvmVendorSpec.matching("Eclipse Adoptium"))
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

//            val dokkaHtml by tasks.getting(DokkaTask::class)
//            val javadocJar by tasks.registering(Jar::class) {
//                dependsOn(dokkaHtml)
//                archiveClassifier.set("javadoc")
//                from(dokkaHtml.outputDirectory)
//            }
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
