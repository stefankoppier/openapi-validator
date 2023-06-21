package io.github.stefankoppier.openapi.validator.core.rules.openapi.collections

import io.github.stefankoppier.openapi.validator.core.rules.RuleGroup
import io.github.stefankoppier.openapi.validator.core.rules.ValidationRule
import io.swagger.v3.oas.models.Components

class ComponentsRule internal constructor(group: RuleGroup = RuleGroup.unknown()) : ValidationRule<Components>(group) {

    fun schemas(description: String = "", rule: SchemasRule.() -> SchemasRule) =
        apply {
            add { components ->
                rule(SchemasRule(RuleGroup.named("schemas", description, RuleGroup.Category.OBJECT, group)))
                    .validate(components?.schemas?.toList())
            }
        }

    fun responses(description: String = "", rule: ResponsesRule.() -> ResponsesRule) =
        apply {
            add { components ->
                rule(ResponsesRule(RuleGroup.named("responses", description, RuleGroup.Category.OBJECT, group)))
                    .validate(components?.responses?.toList())
            }
        }

    fun parameters(description: String = "", rule: ParametersRule.() -> ParametersRule) =
        apply {
            add { components ->
                rule(ParametersRule(RuleGroup.named("parameters", description, RuleGroup.Category.OBJECT, group)))
                    .validate(components?.parameters?.toList()?.map { it.second })
            }
        }

    fun examples(description: String = "", rule: ExamplesRule.() -> ExamplesRule) =
        apply {
            add { components ->
                rule(ExamplesRule(RuleGroup.named("examples", description, RuleGroup.Category.OBJECT, group)))
                    .validate(components?.examples?.toList())
            }
        }

    // Request bodies

    fun headers(description: String = "", rule: HeadersRule.() -> HeadersRule) =
        apply {
            add { components ->
                rule(HeadersRule(RuleGroup.named("headers", description, RuleGroup.Category.OBJECT, group)))
                    .validate(components?.headers?.toList())
            }
        }

    // Security schemas

    // links

    // callbacks

    // extensions
}
