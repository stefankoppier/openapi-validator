package io.github.stefankoppier.openapi.validator.core.rules.openapi.collections

import io.github.stefankoppier.openapi.validator.core.rules.RuleGroup
import io.github.stefankoppier.openapi.validator.core.rules.openapi.RequestBodyRule
import io.github.stefankoppier.openapi.validator.core.rules.primitives.IterableRule
import io.swagger.v3.oas.models.parameters.RequestBody

class RequestBodiesRule(group: RuleGroup = RuleGroup.unknown()) : IterableRule<Pair<String, RequestBody>>(group) {

    fun all(description: String = "", rule: RequestBodyRule.() -> RequestBodyRule) =
        all { requestBody ->
            rule(RequestBodyRule(RuleGroup.named("requestBody '${requestBody.first}'", RuleGroup.Category.GROUP, description, group)))
                .validate(requestBody.second)
        }

    fun requestBody(description: String = "", named: String, rule: RequestBodyRule.() -> RequestBodyRule) =
        add { paths ->
            val requestBodies = paths?.find { it.first == named }
            rule(RequestBodyRule(RuleGroup.named("requestBody '$named'", RuleGroup.Category.GROUP, description, group)))
                .validate(requestBodies?.second)
        }
}
