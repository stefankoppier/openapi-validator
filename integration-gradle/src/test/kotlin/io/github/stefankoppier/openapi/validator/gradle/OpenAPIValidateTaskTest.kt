package io.github.stefankoppier.openapi.validator.gradle

import io.github.stefankoppier.openapi.validator.core.rules.openapi.openAPI
import org.gradle.api.GradleException
import org.gradle.api.Project
import org.gradle.testfixtures.ProjectBuilder
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.nio.file.Paths
import kotlin.test.assertNotNull

class OpenAPIValidateTaskTest {

    private lateinit var project: Project

    @BeforeEach
    fun before() {
        project = ProjectBuilder.builder().build()
        project.plugins.apply("io.github.stefankoppier.openapi.validator")
    }

    @Test
    fun `execute succeeds`() {
        val task = project.tasks.findByName("openAPIValidate") as? OpenAPIValidateTask
        val extension = project.extensions.findByName("openAPIValidate") as? OpenAPIValidatorExtension
        assertNotNull(extension)
        assertNotNull(task)

        extension.document.set(Paths.get("").toAbsolutePath().resolve("src/test/resources/petstore.yaml").toUri())
        extension.rules.set(
            openAPI {
                required()
            },
        )

        task.execute()
    }

    @Test
    fun `execute fails`() {
        val task = project.tasks.findByName("openAPIValidate") as? OpenAPIValidateTask
        val extension = project.extensions.findByName("openAPIValidate") as? OpenAPIValidatorExtension
        assertNotNull(extension)
        assertNotNull(task)

        extension.document.set(Paths.get("").toAbsolutePath().resolve("src/test/resources/petstore.yaml").toUri())
        extension.rules.set(
            openAPI {
                info {
                    title { exactly("Not OpenAPI Petstore") }
                }
            },
        )

        assertThrows<GradleException> { task.execute() }
    }
}
