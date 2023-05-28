package io.github.stefankoppier.openapi.validator.core.rules.openapi

import io.github.stefankoppier.openapi.validator.core.rules.RuleGroup
import io.github.stefankoppier.openapi.validator.core.rules.RuleGroupCategory
import io.github.stefankoppier.openapi.validator.core.rules.ValidationRule
import io.github.stefankoppier.openapi.validator.core.rules.openapi.collections.ComponentsRule
import io.github.stefankoppier.openapi.validator.core.rules.openapi.collections.PathsRule
import io.github.stefankoppier.openapi.validator.core.rules.openapi.collections.TagsRule
import io.github.stefankoppier.openapi.validator.core.rules.primitives.StringRule
import io.swagger.v3.oas.models.OpenAPI

class OpenAPIRule(group: RuleGroup =  RuleGroup.unknown()) : ValidationRule<OpenAPI>(group) {

    init {
        openapi { matches(Regex("[0-9]+\\.[0-9]+\\.[0-9]+")) }
    }

    fun openapi(description: String = "", rule: StringRule.() -> StringRule): OpenAPIRule {
        add {
            rule(StringRule(RuleGroup.named("info", description, RuleGroupCategory.FIELD, group))).validate(it?.openapi)
        }
        return this
    }

    fun info(description: String = "", rule: InfoRule.() -> InfoRule): OpenAPIRule {
        add {
            rule(InfoRule(RuleGroup.named("info", description, RuleGroupCategory.OBJECT, group))).validate(it?.info)
        }
        return this
    }

    // jsonSchemaDialect

    // servers

    fun paths(description: String = "", rule: PathsRule.() -> PathsRule): OpenAPIRule {
        add {
            rule(PathsRule(RuleGroup.named("paths", description, RuleGroupCategory.OBJECT, group))).validate(it?.paths?.toList())
        }
        return this
    }

    // webhooks

    fun components(description: String = "", rule: ComponentsRule.() -> ComponentsRule): OpenAPIRule {
        add {
            rule(ComponentsRule(RuleGroup.named("components", description, RuleGroupCategory.OBJECT, group))).validate(it?.components)
        }
        return this
    }

    // security

    fun tags(description: String = "", rule: TagsRule.() -> TagsRule): OpenAPIRule {
        add {
            rule(TagsRule(RuleGroup.named("tags", description, RuleGroupCategory.OBJECT, group))).validate(it?.tags)
        }
        return this
    }

    fun externalDocs(description: String = "", rule: ExternalDocumentationRule.() -> ExternalDocumentationRule): OpenAPIRule {
        add {
            rule(ExternalDocumentationRule(RuleGroup.named("externalDocs", description, RuleGroupCategory.OBJECT, group))).validate(it?.externalDocs)
        }
        return this
    }
}

fun openAPI(description: String = "", rule: OpenAPIRule.() -> OpenAPIRule): OpenAPIRule {
    return rule(OpenAPIRule(RuleGroup.named("document", description, RuleGroupCategory.OBJECT)))
}