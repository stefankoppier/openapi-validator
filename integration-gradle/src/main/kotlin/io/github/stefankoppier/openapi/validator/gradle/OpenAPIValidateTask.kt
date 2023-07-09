package io.github.stefankoppier.openapi.validator.gradle

import io.github.stefankoppier.openapi.validator.core.Validator
import io.github.stefankoppier.openapi.validator.core.rules.openapi.OpenAPIRule
import org.gradle.api.DefaultTask
import org.gradle.api.GradleException
import org.gradle.api.provider.Property
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.TaskAction
import java.net.URI

abstract class OpenAPIValidateTask : DefaultTask() {

    @get:Input abstract val document: Property<URI>

    @get:Input abstract val rules: Property<OpenAPIRule>

    @TaskAction
    fun execute() {
        val validator = Validator(rules.get())
            .validate(document.get())

        if (validator.isFailure) {
            throw GradleException(validator.summarize())
        } else {
            logger.info("No errors found")
        }
    }

    companion object {
        const val TASK_NAME = "openAPIValidate"
        const val TASK_GROUP = "verification"
    }
}
