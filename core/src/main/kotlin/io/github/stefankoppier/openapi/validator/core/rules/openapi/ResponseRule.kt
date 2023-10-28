package io.github.stefankoppier.openapi.validator.core.rules.openapi

import io.github.stefankoppier.openapi.validator.core.rules.RuleGroup
import io.github.stefankoppier.openapi.validator.core.rules.ValidationRule
import io.github.stefankoppier.openapi.validator.core.rules.openapi.collections.ContentRule
import io.github.stefankoppier.openapi.validator.core.rules.primitives.StringRule
import io.swagger.v3.oas.models.responses.ApiResponse

class ResponseRule internal constructor(group: RuleGroup = RuleGroup.unknown()) : ValidationRule<ApiResponse>(group) {

    fun description(description: String = "", rule: StringRule.() -> StringRule) =
        add {
            rule(StringRule(RuleGroup.named("description", RuleGroup.Category.FIELD, description, group)))
                .validate(it?.description)
        }

    fun content(description: String = "", rule: ContentRule.() -> ContentRule) =
        add {
            rule(ContentRule(RuleGroup.named("content", RuleGroup.Category.GROUP, description, group)))
                .validate(it?.content?.toList())
        }
}
