package io.github.stefankoppier.openapi.validator.core.rules.openapi

import io.github.stefankoppier.openapi.validator.core.rules.RuleGroup
import io.github.stefankoppier.openapi.validator.core.rules.ValidationRule
import io.github.stefankoppier.openapi.validator.core.rules.openapi.collections.ServerVariablesRule
import io.github.stefankoppier.openapi.validator.core.rules.primitives.StringRule
import io.github.stefankoppier.openapi.validator.core.rules.primitives.URLRule
import io.swagger.v3.oas.models.servers.Server

class ServerRule(group: RuleGroup) : ValidationRule<Server>(group) {

    init {
        url { required() }
    }

    fun url(description: String = "", rule: URLRule.() -> URLRule) =
        apply {
            add {
                rule(URLRule(RuleGroup.named("url", description, RuleGroup.Category.FIELD, group)))
                    .validate(it?.url)
            }
        }

    fun description(description: String = "", rule: StringRule.() -> StringRule) =
        apply {
            add {
                rule(StringRule(RuleGroup.named("description", description, RuleGroup.Category.FIELD, group)))
                    .validate(it?.description)
            }
        }

    fun variables(description: String = "", rule: ServerVariablesRule.() -> ServerVariablesRule) =
        apply {
            add {
                rule(ServerVariablesRule(RuleGroup.named("variables", description, RuleGroup.Category.OBJECT, group)))
                    .validate(it?.variables?.toList())
            }
        }
}