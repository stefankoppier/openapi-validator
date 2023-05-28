package io.github.stefankoppier.openapi.validator.core.rules.openapi

import io.github.stefankoppier.openapi.validator.core.rules.RuleGroup
import io.github.stefankoppier.openapi.validator.core.rules.RuleGroupCategory
import io.github.stefankoppier.openapi.validator.core.rules.ValidationRule
import io.github.stefankoppier.openapi.validator.core.rules.primitives.StringRule
import io.github.stefankoppier.openapi.validator.core.rules.primitives.URLRule
import io.swagger.v3.oas.models.ExternalDocumentation

class ExternalDocumentationRule(group: RuleGroup) : ValidationRule<ExternalDocumentation>(group) {

    init {
        url { required() }
    }

    fun description(description: String = "", rule: StringRule.() -> StringRule): ExternalDocumentationRule {
        add {
            rule(StringRule(RuleGroup.named("description", description, RuleGroupCategory.FIELD, group))).validate(it?.description)
        }
        return this
    }

    fun url(description: String = "", rule: URLRule.() -> URLRule): ExternalDocumentationRule {
        add {
            rule(URLRule(RuleGroup.named("url", description, RuleGroupCategory.FIELD, group))).validate(it?.url)
        }
        return this
    }
}
