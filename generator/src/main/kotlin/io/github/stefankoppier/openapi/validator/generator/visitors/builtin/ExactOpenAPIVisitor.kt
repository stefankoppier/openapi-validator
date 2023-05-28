package io.github.stefankoppier.openapi.validator.generator.visitors.builtin

import com.squareup.kotlinpoet.CodeBlock
import io.github.stefankoppier.openapi.validator.generator.visitors.Visitor
import io.swagger.v3.oas.models.OpenAPI

class ExactOpenAPIVisitor : Visitor<OpenAPI> {

    override fun visit(fixture: OpenAPI): CodeBlock {
        return CodeBlock.builder()
            .add("""
                |openAPI {
                |    openapi { exactly(%S) }
                |    info { }
                |    jsonSchemaDialect { exactly(%S) }
                |}
                """.trimMargin(),
                fixture.openapi,
                fixture.jsonSchemaDialect)
            .build()
    }
}