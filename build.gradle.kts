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
        apply(plugin = "jvm-test-suite")

        kotlin {
            jvmToolchain {
                languageVersion.set(JavaLanguageVersion.of(11))
            }
        }

        dependencies {
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
    }
}