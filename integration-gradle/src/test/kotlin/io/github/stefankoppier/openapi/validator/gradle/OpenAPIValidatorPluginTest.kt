package io.github.stefankoppier.openapi.validator.gradle

import org.gradle.api.Project
import org.gradle.testfixtures.ProjectBuilder
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertIs
import kotlin.test.assertNotNull

class OpenAPIValidatorPluginTest {

    private lateinit var project: Project

    @BeforeEach
    fun before() {
        project = ProjectBuilder.builder().build()
        project.plugins.apply("io.github.stefankoppier.openapi.validator")
    }

    @Test
    fun `plugin registers openAPIValidate task`() {
        val task = project.tasks.findByName("openAPIValidate")
        assertNotNull(task)
        assertIs<OpenAPIValidateTask>(task)
        task.run {
            assertEquals("verification", group)
        }
    }
}
