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

val javaVersion = 11

dependencies {
    implementation(project(":core"))
    implementation(project(":integration-junit"))
}

allprojects {
    plugins.withId("org.jetbrains.kotlin.jvm") {
        apply(plugin = "org.jetbrains.dokka")
        apply(plugin = "jvm-test-suite")
        apply(plugin = "jacoco")
        apply(plugin = "com.diffplug.spotless")
        apply(plugin = "maven-publish")
        apply(plugin = "signing")

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

        if (project != rootProject) {
            afterEvaluate {
                val dokkaHtml by tasks.getting(DokkaTask::class)
                val javadocJar by tasks.registering(Jar::class) {
                    dependsOn(dokkaHtml)
                    archiveClassifier.set("javadoc")
                    from(dokkaHtml.outputDirectory)
                }

                publishing {
                    repositories {
                        maven {
                            name = "OSSRH"
                            url = uri("https://s01.oss.sonatype.org/service/local/staging/deploy/maven2/")

                            credentials {
                                username = properties["ossrhUsername"] as String
                                password = properties["ossrhPassword"] as String
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
                                name.set("$group:${rootProject.name}-${project.name}")
                                description.set(project.description)
                                url.set("https://github.com/stefankoppier/openapi-validator")
                                developers {
                                    developer {
                                        id.set("stefankoppier")
                                        name.set("Stefan Koppier")
                                        email.set("stefan.koppier@outlook.com")
                                        url.set("https://github.com/stefankoppier")
                                    }
                                }

                                scm {
                                    connection.set("scm:git:git@github.com/stefankoppier/openapi-validator.git")
                                    developerConnection.set("scm:git:git@github.com/stefankoppier/openapi-validator.git")
                                    url.set("https://github.com/stefankoppier/openapi-validator/tree/main")
                                }

                                licenses {
                                    license {
                                        name.set("The Apache License, Version 2.0")
                                        url.set("http://www.apache.org/licenses/LICENSE-2.0.txt")
                                    }
                                }

                                issueManagement {
                                    system.set("GitHub")
                                    url.set("https://github.com/stefankoppier/openapi-validator/issues")
                                }
                            }
                        }
                    }
                }
            }

            signing {
                val key = findProperty("signing.key") as String
                val password = findProperty("signing.password") as String
                useInMemoryPgpKeys(key, password)
                sign(publishing.publications)
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
