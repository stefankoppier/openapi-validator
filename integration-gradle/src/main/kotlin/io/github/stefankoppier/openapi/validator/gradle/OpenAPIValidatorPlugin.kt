package io.github.stefankoppier.openapi.validator.gradle

import org.gradle.api.Plugin
import org.gradle.api.Project

class OpenAPIValidatorPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        val extension = target.extensions
            .create(EXTENSION_NAME, OpenAPIValidatorExtension::class.java)

        target.tasks.register(OpenAPIValidateTask.TASK_NAME, OpenAPIValidateTask::class.java) {
            it.group = OpenAPIValidateTask.TASK_GROUP
            it.document.set(extension.document)
            it.rules.set(extension.rules)
        }
    }

    companion object {
        const val EXTENSION_NAME = "openAPIValidate"
    }
}
