package io.github.stefankoppier.openapi.validator.core.rules.openapi.collections

import io.github.stefankoppier.openapi.validator.core.rules.RuleGroup
import io.github.stefankoppier.openapi.validator.core.rules.openapi.ServerVariableRule
import io.github.stefankoppier.openapi.validator.core.rules.primitives.IterableValidationRule
import io.swagger.v3.oas.models.servers.ServerVariable

class ServerVariablesRule internal constructor(group: RuleGroup = RuleGroup.unknown()) : IterableValidationRule<Pair<String, ServerVariable>>(group) {

    fun all(description: String = "", rule: ServerVariableRule.() -> ServerVariableRule) =
        all { serverVariables ->
            rule(ServerVariableRule(RuleGroup.named("serverVariable '${serverVariables.first}'", description, RuleGroup.Category.OBJECT, group)))
                .validate(serverVariables.second)
        }

    fun serverVariable(description: String = "", named: String, rule: ServerVariableRule.() -> ServerVariableRule) =
        apply {
            add { serverVariables ->
                val serverVariable = serverVariables?.find { it.first == named }
                rule(ServerVariableRule(RuleGroup.named("serverVariable '$named'", description, RuleGroup.Category.OBJECT, group)))
                    .validate(serverVariable?.second)
            }
        }
}
