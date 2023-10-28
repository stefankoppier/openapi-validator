package io.github.stefankoppier.openapi.validator.core.rules.openapi

import io.github.stefankoppier.openapi.validator.core.rules.RuleGroup
import io.github.stefankoppier.openapi.validator.core.rules.ValidationRule
import io.github.stefankoppier.openapi.validator.core.rules.primitives.StringRule
import io.swagger.v3.oas.models.callbacks.Callback

class CallbackRule(group: RuleGroup = RuleGroup.unknown()) : ValidationRule<Callback>(group) {

    fun ref(description: String = "", rule: StringRule.() -> StringRule) =
        add {
            rule(StringRule(RuleGroup.named("ref", RuleGroup.Category.FIELD, description, group)))
                .validate(it?.`$ref`)
        }
}
