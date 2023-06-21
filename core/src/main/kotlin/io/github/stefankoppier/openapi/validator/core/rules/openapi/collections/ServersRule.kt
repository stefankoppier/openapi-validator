package io.github.stefankoppier.openapi.validator.core.rules.openapi.collections

import io.github.stefankoppier.openapi.validator.core.rules.RuleGroup
import io.github.stefankoppier.openapi.validator.core.rules.openapi.ServerRule
import io.github.stefankoppier.openapi.validator.core.rules.primitives.IterableValidationRule
import io.swagger.v3.oas.models.servers.Server

class ServersRule internal constructor(group: RuleGroup = RuleGroup.unknown()) : IterableValidationRule<Server>(group) {

    fun all(description: String = "", rule: ServerRule.() -> ServerRule) =
        all { server ->
            rule(ServerRule(RuleGroup.named("server '${server.url}'", description, RuleGroup.Category.OBJECT, group)))
                .validate(server)
        }

    fun server(description: String = "", url: String, rule: ServerRule.() -> ServerRule) =
        apply {
            add { servers ->
                val server = servers?.find { it.url == url }
                rule(ServerRule(RuleGroup.named("server '$url'", description, RuleGroup.Category.OBJECT, group)))
                    .validate(server)
            }
        }
}
