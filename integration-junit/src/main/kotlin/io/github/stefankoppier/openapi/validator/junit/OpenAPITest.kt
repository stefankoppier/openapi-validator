package io.github.stefankoppier.openapi.validator.junit

@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.CLASS, AnnotationTarget.FUNCTION)
annotation class OpenAPITest(val relativeUrl: String)
