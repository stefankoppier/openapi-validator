package io.github.stefankoppier.openapi.validator.generator

import io.github.stefankoppier.openapi.validator.generator.visitors.builtin.ExactOpenAPIVisitor

class ExactOpenAPIRuleGenerator : OpenAPIRuleGenerator(
    ExactOpenAPIVisitor()
)