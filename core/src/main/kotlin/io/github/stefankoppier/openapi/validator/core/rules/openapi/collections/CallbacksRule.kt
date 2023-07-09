package io.github.stefankoppier.openapi.validator.core.rules.openapi.collections

import io.github.stefankoppier.openapi.validator.core.rules.RuleGroup
import io.github.stefankoppier.openapi.validator.core.rules.openapi.CallbackRule
import io.github.stefankoppier.openapi.validator.core.rules.primitives.IterableValidationRule
import io.swagger.v3.oas.models.callbacks.Callback

class CallbacksRule internal constructor(group: RuleGroup = RuleGroup.unknown()) : IterableValidationRule<Pair<String, Callback>>(group) {

    fun all(description: String = "", rule: CallbackRule.() -> CallbackRule) =
        all { callback ->
            rule(CallbackRule(RuleGroup.named("callback '${callback.first}'", RuleGroup.Category.GROUP, description, group)))
                .validate(callback.second)
        }

    fun callback(description: String = "", named: String, rule: CallbackRule.() -> CallbackRule) =
        apply {
            add { callbacks ->
                val callback = callbacks?.find { it.first == named }
                rule(CallbackRule(RuleGroup.named("callback '$named'", RuleGroup.Category.GROUP, description, group)))
                    .validate(callback?.second)
            }
        }
}
