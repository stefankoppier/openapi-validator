plugins {
    id("maven-publish")
    id("signing")
    id("org.jetbrains.kotlin.jvm")
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
//            artifact(javadocJar)
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

signing {
    val key = findProperty("signing.key") as String
    val password = findProperty("signing.password") as String
    useInMemoryPgpKeys(key, password)
    sign(publishing.publications)
}