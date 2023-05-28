package io.github.stefankoppier.openapi.validator.junit

@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.CLASS)
annotation class OpenAPIValidationDocument(val relativeUrl: String) {
}