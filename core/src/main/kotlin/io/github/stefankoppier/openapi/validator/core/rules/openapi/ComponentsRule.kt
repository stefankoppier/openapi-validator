package io.github.stefankoppier.openapi.validator.core.rules.openapi

import io.github.stefankoppier.openapi.validator.core.rules.RuleGroup
import io.github.stefankoppier.openapi.validator.core.rules.ValidationRule
import io.github.stefankoppier.openapi.validator.core.rules.openapi.collections.ExamplesRule
import io.github.stefankoppier.openapi.validator.core.rules.openapi.collections.HeadersRule
import io.github.stefankoppier.openapi.validator.core.rules.openapi.collections.ParametersRule
import io.github.stefankoppier.openapi.validator.core.rules.openapi.collections.RequestBodiesRule
import io.github.stefankoppier.openapi.validator.core.rules.openapi.collections.ResponsesRule
import io.github.stefankoppier.openapi.validator.core.rules.openapi.collections.SchemasRule
import io.swagger.v3.oas.models.Components

class ComponentsRule internal constructor(group: RuleGroup = RuleGroup.unknown()) : ValidationRule<Components>(group) {

    fun schemas(description: String = "", rule: SchemasRule.() -> SchemasRule) =
        apply {
            add { components ->
                rule(SchemasRule(RuleGroup.named("schemas", RuleGroup.Category.GROUP, description, group)))
                    .validate(components?.schemas?.toList())
            }
        }

    fun responses(description: String = "", rule: ResponsesRule.() -> ResponsesRule) =
        apply {
            add { components ->
                rule(ResponsesRule(RuleGroup.named("responses", RuleGroup.Category.GROUP, description, group)))
                    .validate(components?.responses?.toList())
            }
        }

    fun parameters(description: String = "", rule: ParametersRule.() -> ParametersRule) =
        apply {
            add { components ->
                rule(ParametersRule(RuleGroup.named("parameters", RuleGroup.Category.GROUP, description, group)))
                    .validate(components?.parameters?.toList()?.map { it.second })
            }
        }

    fun examples(description: String = "", rule: ExamplesRule.() -> ExamplesRule) =
        apply {
            add { components ->
                rule(ExamplesRule(RuleGroup.named("examples", RuleGroup.Category.GROUP, description, group)))
                    .validate(components?.examples?.toList())
            }
        }

    fun requestBodies(description: String = "", rule: RequestBodiesRule.() -> RequestBodiesRule) =
        apply {
            add { components ->
                rule(RequestBodiesRule(RuleGroup.named("requestBodies", RuleGroup.Category.GROUP, description, group)))
                    .validate(components?.requestBodies?.toList())
            }
        }

    fun headers(description: String = "", rule: HeadersRule.() -> HeadersRule) =
        apply {
            add { components ->
                rule(HeadersRule(RuleGroup.named("headers", RuleGroup.Category.GROUP, description, group)))
                    .validate(components?.headers?.toList())
            }
        }

// TODO Security schemas
// TODO links
// TODO callbacks
// TODO extensions
}
