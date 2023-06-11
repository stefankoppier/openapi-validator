package io.github.stefankoppier.openapi.validator.core.rules.openapi

import io.github.stefankoppier.openapi.validator.core.rules.RuleGroup
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

    fun openapi(description: String = "", rule: StringRule.() -> StringRule) =
        apply { 
            add {
                rule(StringRule(RuleGroup.named("info", description, RuleGroup.Category.FIELD, group)))
                    .validate(it?.openapi)
            }
        }

    fun info(description: String = "", rule: InfoRule.() -> InfoRule) =
        apply { 
            add {
                rule(InfoRule(RuleGroup.named("info", description, RuleGroup.Category.OBJECT, group)))
                    .validate(it?.info)
            }
        }

    // jsonSchemaDialect

    // servers

    fun paths(description: String = "", rule: PathsRule.() -> PathsRule) =
        apply { 
            add {
                rule(PathsRule(RuleGroup.named("paths", description, RuleGroup.Category.OBJECT, group)))
                    .validate(it?.paths?.toList())
            }
        }

    // webhooks

    fun components(description: String = "", rule: ComponentsRule.() -> ComponentsRule) =
        apply { 
            add {
                rule(ComponentsRule(RuleGroup.named("components", description, RuleGroup.Category.OBJECT, group)))
                    .validate(it?.components)
            }
        }

    // security

    fun tags(description: String = "", rule: TagsRule.() -> TagsRule) =
        apply { 
            add {
                rule(TagsRule(RuleGroup.named("tags", description, RuleGroup.Category.OBJECT, group)))
                    .validate(it?.tags)
            }
        }

    fun externalDocs(description: String = "", rule: ExternalDocumentationRule.() -> ExternalDocumentationRule) =
        apply { 
            add {
                rule(ExternalDocumentationRule(RuleGroup.named("externalDocs", description, RuleGroup.Category.OBJECT, group)))
                    .validate(it?.externalDocs)
            }
        }
}

fun openAPI(description: String = "", rule: OpenAPIRule.() -> OpenAPIRule) : OpenAPIRule {
    return rule(OpenAPIRule(RuleGroup.named("document", description, RuleGroup.Category.OBJECT)))
}