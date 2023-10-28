package io.github.stefankoppier.openapi.validator.core.rules.openapi

import io.github.stefankoppier.openapi.validator.core.rules.RuleGroup
import io.github.stefankoppier.openapi.validator.core.rules.ValidationRule
import io.github.stefankoppier.openapi.validator.core.rules.openapi.collections.ContentRule
import io.github.stefankoppier.openapi.validator.core.rules.primitives.BooleanRule
import io.github.stefankoppier.openapi.validator.core.rules.primitives.StringRule
import io.swagger.v3.oas.models.parameters.RequestBody

class RequestBodyRule internal constructor(group: RuleGroup = RuleGroup.unknown()) : ValidationRule<RequestBody>(group) {

    init {
        given({ it != null }) {
            content { required() }
        }
    }

    fun description(description: String = "", rule: StringRule.() -> StringRule) =
        apply {
            add {
                rule(StringRule(RuleGroup.named("description", RuleGroup.Category.FIELD, description, group)))
                    .validate(it?.description)
            }
        }

    fun content(description: String = "", rule: ContentRule.() -> ContentRule) =
        apply {
            add {
                rule(ContentRule(RuleGroup.named("content", RuleGroup.Category.GROUP, description, group)))
                    .validate(it?.content?.toList())
            }
        }

    fun required(description: String = "", rule: BooleanRule.() -> BooleanRule) =
        apply {
            add {
                rule(BooleanRule(RuleGroup.named("required", RuleGroup.Category.FIELD, description, group)))
                    .validate(it?.required)
            }
        }
}
