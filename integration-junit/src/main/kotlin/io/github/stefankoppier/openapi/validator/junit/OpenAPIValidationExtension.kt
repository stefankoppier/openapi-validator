package io.github.stefankoppier.openapi.validator.junit

import org.junit.jupiter.api.extension.AfterAllCallback
import org.junit.jupiter.api.extension.AfterEachCallback
import org.junit.jupiter.api.extension.BeforeAllCallback
import org.junit.jupiter.api.extension.BeforeEachCallback
import org.junit.jupiter.api.extension.Extension
import org.junit.jupiter.api.extension.ExtensionContext
import java.lang.IllegalStateException
import java.lang.reflect.Method
import java.net.URI
import kotlin.jvm.optionals.getOrNull

class OpenAPIValidationExtension : Extension, BeforeAllCallback, AfterAllCallback, BeforeEachCallback, AfterEachCallback {

    override fun beforeAll(context: ExtensionContext) {
        val element = context.element.getOrNull() as Class<*>?
        tests = element?.getAnnotation(OpenAPITest::class.java)
    }

    override fun beforeEach(context: ExtensionContext) {
        val element = context.element.getOrNull() as Method?
        test = element?.getAnnotation(OpenAPITest::class.java)
    }

    override fun afterAll(context: ExtensionContext?) {
        tests = null
    }

    override fun afterEach(context: ExtensionContext) {
        test = null
    }

    companion object {
        var tests: OpenAPITest? = null

        var test: OpenAPITest? = null

        val uri: URI
            get() {
                val uri = test?.relativeUrl ?: tests?.relativeUrl
                return runCatching { URI(uri) }.getOrElse {
                    throw IllegalStateException(
                        """|Failed to parse URI '$uri'.
                           |Make sure your class is annotated with @OpenAPITests or your test is annotated with @OpenAPITest")
                        """.trimMargin(),
                        it,
                    )
                }
            }
    }
}
