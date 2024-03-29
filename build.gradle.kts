import org.jetbrains.dokka.gradle.DokkaTask
import org.jetbrains.dokka.gradle.DokkaTaskPartial
import java.net.URL

plugins {
    id("jacoco-report-aggregation")
    id("maven-publish")
    id("signing")
    alias(libs.plugins.kotlin.jvm)
    alias(libs.plugins.dokka)
    alias(libs.plugins.spotless)
    alias(libs.plugins.sonarqube)
}

val javaVersion = 17

dependencies {
    implementation(project(":core"))
    implementation(project(":integration-junit"))
}

allprojects {
    repositories {
        mavenCentral()
    }

    plugins.withId("org.jetbrains.kotlin.jvm") {
        apply(plugin = "jvm-test-suite")
        apply(plugin = "jacoco")
        apply(plugin = "maven-publish")
        apply(plugin = "signing")
        apply(plugin = libs.plugins.dokka.get().pluginId)
        apply(plugin = libs.plugins.spotless.get().pluginId)

        kotlin {
            jvmToolchain {
                languageVersion = JavaLanguageVersion.of(javaVersion)
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
                    jdkVersion = javaVersion
                    sourceLink {
                        localDirectory = file("$projectDir/src/main/kotlin")
                        remoteUrl = URL("https://github.com/stefankoppier/openapi-validator/tree/main/${project.name}/src/main/kotlin")
                    }
                }
            }
        }

        spotless {
            kotlin {
                ktlint().setEditorConfigPath("$rootDir/.editorconfig")
            }
        }

        if (project != rootProject && project != project(":integration-gradle")) {
            val dokkaHtml by tasks.getting(DokkaTask::class)
            val javadocJar by tasks.registering(Jar::class) {
                dependsOn(dokkaHtml)
                archiveClassifier = "javadoc"
                from(dokkaHtml.outputDirectory)
            }

            publishing {
                repositories {
                    maven {
                        name = "OSSRH"
                        url = uri("https://s01.oss.sonatype.org/service/local/staging/deploy/maven2/")

                        credentials {
                            username = properties["ossrhUsername"] as? String
                            password = properties["ossrhPassword"] as? String
                        }
                    }
                }

                publications {
                    create<MavenPublication>(project.name) {
                        from(components["kotlin"])
                        artifact(tasks.named("kotlinSourcesJar").get())
                        artifact(javadocJar)
                        groupId = rootProject.group.toString()
                        artifactId = project.name
                        version = rootProject.version.toString()

                        pom {
                            name = "$group:${rootProject.name}-${project.name}"
                            description = project.description
                            url = "https://github.com/stefankoppier/openapi-validator"
                            developers {
                                developer {
                                    id = "stefankoppier"
                                    name = "Stefan Koppier"
                                    email = "stefan.koppier@outlook.com"
                                    url = "https://github.com/stefankoppier"
                                }
                            }

                            scm {
                                connection = "scm:git:git@github.com/stefankoppier/openapi-validator.git"
                                developerConnection = "scm:git:git@github.com/stefankoppier/openapi-validator.git"
                                url = "https://github.com/stefankoppier/openapi-validator/tree/main"
                            }

                            licenses {
                                license {
                                    name = "The Apache License, Version 2.0"
                                    url = "http://www.apache.org/licenses/LICENSE-2.0.txt"
                                }
                            }

                            issueManagement {
                                system = "GitHub"
                                url = "https://github.com/stefankoppier/openapi-validator/issues"
                            }
                        }
                    }
                }
            }
        }

        signing {
            val key = findProperty("signing.key") as? String
            val password = findProperty("signing.password") as? String
            useInMemoryPgpKeys(key, password)
            sign(publishing.publications)
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
        property("sonar.coverage.jacoco.xmlReportPaths", layout.buildDirectory.file("reports/jacoco/testCodeCoverageReport/testCodeCoverageReport.xml").get().toString())
    }
}
