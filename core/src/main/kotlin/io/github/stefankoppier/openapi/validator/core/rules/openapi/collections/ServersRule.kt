package io.github.stefankoppier.openapi.validator.core.rules.openapi.collections

import io.github.stefankoppier.openapi.validator.core.rules.RuleGroup
import io.github.stefankoppier.openapi.validator.core.rules.openapi.ServerRule
import io.github.stefankoppier.openapi.validator.core.rules.primitives.IterableRule
import io.swagger.v3.oas.models.servers.Server

class ServersRule internal constructor(group: RuleGroup = RuleGroup.unknown()) : IterableRule<Server>(group) {

    fun all(description: String = "", rule: ServerRule.() -> ServerRule) =
        addForAll { server ->
            rule(ServerRule(RuleGroup.named("server '${server.url}'", RuleGroup.Category.GROUP, description, group)))
                .validate(server)
        }

    fun server(description: String = "", url: String, rule: ServerRule.() -> ServerRule) =
        add { servers ->
            val server = servers?.find { it.url == url }
            rule(ServerRule(RuleGroup.named("server '$url'", RuleGroup.Category.GROUP, description, group)))
                .validate(server)
        }
}
