package io.github.stefankoppier.openapi.validator.core.rules.openapi

import io.github.stefankoppier.openapi.validator.core.rules.RuleGroup
import io.github.stefankoppier.openapi.validator.core.rules.ValidationRule
import io.github.stefankoppier.openapi.validator.core.rules.openapi.collections.ServerVariablesRule
import io.github.stefankoppier.openapi.validator.core.rules.primitives.StringRule
import io.github.stefankoppier.openapi.validator.core.rules.primitives.URLRule
import io.swagger.v3.oas.models.servers.Server

class ServerRule internal constructor(group: RuleGroup = RuleGroup.unknown()) : ValidationRule<Server>(group) {

    init {
        url { required() }
    }

    fun url(description: String = "", rule: URLRule.() -> URLRule) =
        apply {
            add {
                rule(URLRule(RuleGroup.named("url", RuleGroup.Category.FIELD, description, group)))
                    .validate(it?.url)
            }
        }

    fun description(description: String = "", rule: StringRule.() -> StringRule) =
        apply {
            add {
                rule(StringRule(RuleGroup.named("description", RuleGroup.Category.FIELD, description, group)))
                    .validate(it?.description)
            }
        }

    fun variables(description: String = "", rule: ServerVariablesRule.() -> ServerVariablesRule) =
        apply {
            add {
                rule(ServerVariablesRule(RuleGroup.named("variables", RuleGroup.Category.GROUP, description, group)))
                    .validate(it?.variables?.toList())
            }
        }
}
