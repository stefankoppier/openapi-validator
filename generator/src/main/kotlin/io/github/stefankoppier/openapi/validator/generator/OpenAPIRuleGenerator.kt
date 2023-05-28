package io.github.stefankoppier.openapi.validator.generator

import com.squareup.kotlinpoet.ClassName
import com.squareup.kotlinpoet.FileSpec
import com.squareup.kotlinpoet.FunSpec
import io.github.stefankoppier.openapi.validator.generator.visitors.Visitor
import io.swagger.v3.oas.models.OpenAPI

abstract class OpenAPIRuleGenerator(
    private val openAPIVisitor: Visitor<OpenAPI>
) {

    fun generate(openAPI: OpenAPI): FileSpec {
        return FileSpec.builder(ClassName.bestGuess("Specification"))
            .addFunction(FunSpec.builder("rules")
                .addCode(openAPIVisitor.visit(openAPI))
                .build()
            ).build()
    }
}