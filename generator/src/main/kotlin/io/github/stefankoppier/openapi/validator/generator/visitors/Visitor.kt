package io.github.stefankoppier.openapi.validator.generator.visitors

import com.squareup.kotlinpoet.CodeBlock

interface Visitor<T> {

    fun visit(fixture: T): CodeBlock
}