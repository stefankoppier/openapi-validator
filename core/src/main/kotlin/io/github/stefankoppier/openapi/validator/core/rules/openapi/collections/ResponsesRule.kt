package io.github.stefankoppier.openapi.validator.core.rules.openapi.collections

import io.github.stefankoppier.openapi.validator.core.rules.RuleGroup
import io.github.stefankoppier.openapi.validator.core.rules.openapi.ResponseRule
import io.github.stefankoppier.openapi.validator.core.rules.primitives.IterableRule
import io.swagger.v3.oas.models.responses.ApiResponse

class ResponsesRule internal constructor(group: RuleGroup = RuleGroup.unknown()) : IterableRule<Pair<String, ApiResponse>>(group) {

    fun all(description: String = "", rule: ResponseRule.() -> ResponseRule) =
        all { response ->
            rule(
                ResponseRule(
                    RuleGroup.named(
                        "response '${response.first}'",
                        RuleGroup.Category.GROUP,
                        description,
                        group,
                    ),
                ),
            )
                .validate(response.second)
        }

    fun response(description: String = "", named: String, rule: ResponseRule.() -> ResponseRule) =
        add { responses ->
            val response = responses?.find { it.first == named }
            rule(ResponseRule(RuleGroup.named("response '$named'", RuleGroup.Category.GROUP, description, group)))
                .validate(response?.second)
        }
}
