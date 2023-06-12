package io.github.stefankoppier.openapi.validator.core.rules.openapi.collections

import io.github.stefankoppier.openapi.validator.core.ValidationResult
import io.github.stefankoppier.openapi.validator.core.rules.RuleGroup
import io.github.stefankoppier.openapi.validator.core.rules.openapi.ResponseRule
import io.github.stefankoppier.openapi.validator.core.rules.primitives.IterableValidationRule
import io.swagger.v3.oas.models.responses.ApiResponse

class ResponsesRule internal constructor(group: RuleGroup = RuleGroup.unknown()) : IterableValidationRule<Pair<String, ApiResponse>>(group) {

    fun all(description: String = "", rule: ResponseRule.() -> ResponseRule) =
        apply {
            add { responses ->
                responses
                    ?.map { response ->
                        rule(ResponseRule(RuleGroup.named("response '${response.first}'", description, RuleGroup.Category.OBJECT, group)))
                            .validate(response.second)
                    }
                    ?.reduce { left, right -> left.merge(right) }
                    ?: ValidationResult.success()
            }
        }

    fun response(description: String = "", named: String, rule: ResponseRule.() -> ResponseRule)  =
        apply {
            add { responses ->
                val response = responses?.find { it.first == named }
                rule(ResponseRule(RuleGroup.named("response '$named'", description, RuleGroup.Category.OBJECT, group)))
                    .validate(response?.second)
            }
        }
}
