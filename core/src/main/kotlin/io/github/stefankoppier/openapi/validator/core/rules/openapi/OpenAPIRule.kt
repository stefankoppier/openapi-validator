package io.github.stefankoppier.openapi.validator.core.rules.openapi

import io.github.stefankoppier.openapi.validator.core.rules.RuleGroup
import io.github.stefankoppier.openapi.validator.core.rules.ValidationRule
import io.github.stefankoppier.openapi.validator.core.rules.openapi.collections.PathsRule
import io.github.stefankoppier.openapi.validator.core.rules.openapi.collections.SecurityRequirementsRule
import io.github.stefankoppier.openapi.validator.core.rules.openapi.collections.ServersRule
import io.github.stefankoppier.openapi.validator.core.rules.openapi.collections.TagsRule
import io.github.stefankoppier.openapi.validator.core.rules.primitives.StringRule
import io.swagger.v3.oas.models.OpenAPI

class OpenAPIRule internal constructor(group: RuleGroup = RuleGroup.unknown()) : ValidationRule<OpenAPI>(group) {

    init {
        openapi { matches(Regex("[0-9]+\\.[0-9]+\\.[0-9]+")) }
    }

    fun openapi(description: String = "", rule: StringRule.() -> StringRule) =
        add {
            rule(StringRule(RuleGroup.named("openapi", RuleGroup.Category.FIELD, description, group)))
                .validate(it?.openapi)
        }

    fun info(description: String = "", rule: InfoRule.() -> InfoRule) =
        add {
            rule(InfoRule(RuleGroup.named("info", RuleGroup.Category.GROUP, description, group)))
                .validate(it?.info)
        }

    fun jsonSchemaDialect(description: String = "", rule: StringRule.() -> StringRule) =
        add {
            rule(StringRule(RuleGroup.named("jsonSchemaDialect", RuleGroup.Category.FIELD, description, group)))
                .validate(it?.jsonSchemaDialect)
        }

    fun servers(description: String = "", rule: ServersRule.() -> ServersRule) =
        add {
            rule(ServersRule(RuleGroup.named("servers", RuleGroup.Category.GROUP, description, group)))
                .validate(it?.servers)
        }

    fun paths(description: String = "", rule: PathsRule.() -> PathsRule) =
        add {
            rule(PathsRule(RuleGroup.named("paths", RuleGroup.Category.GROUP, description, group)))
                .validate(it?.paths?.toList())
        }

    fun webhooks(description: String = "", rule: PathsRule.() -> PathsRule) =
        add {
            rule(PathsRule(RuleGroup.named("webhooks", RuleGroup.Category.GROUP, description, group)))
                .validate(it?.webhooks?.toList())
        }

    fun components(description: String = "", rule: ComponentsRule.() -> ComponentsRule) =
        add {
            rule(ComponentsRule(RuleGroup.named("components", RuleGroup.Category.GROUP, description, group)))
                .validate(it?.components)
        }

    fun security(description: String = "", rule: SecurityRequirementsRule.() -> SecurityRequirementsRule) =
        add {
            rule(SecurityRequirementsRule(RuleGroup.named("security", RuleGroup.Category.GROUP, description, group)))
                .validate(it?.security)
        }

    fun tags(description: String = "", rule: TagsRule.() -> TagsRule) =
        add {
            rule(TagsRule(RuleGroup.named("tags", RuleGroup.Category.GROUP, description, group)))
                .validate(it?.tags)
        }

    fun externalDocs(description: String = "", rule: ExternalDocumentationRule.() -> ExternalDocumentationRule) =
        add {
            rule(ExternalDocumentationRule(RuleGroup.named("externalDocs", RuleGroup.Category.GROUP, description, group)))
                .validate(it?.externalDocs)
        }
}

fun openAPI(description: String = "", rule: OpenAPIRule.() -> OpenAPIRule): OpenAPIRule {
    return rule(OpenAPIRule(RuleGroup.named("document", RuleGroup.Category.GROUP, description)))
}
