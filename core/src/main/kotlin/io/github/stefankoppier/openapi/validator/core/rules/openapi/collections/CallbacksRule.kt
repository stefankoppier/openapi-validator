package io.github.stefankoppier.openapi.validator.core.rules.openapi.collections

import io.github.stefankoppier.openapi.validator.core.rules.RuleGroup
import io.github.stefankoppier.openapi.validator.core.rules.openapi.CallbackRule
import io.github.stefankoppier.openapi.validator.core.rules.primitives.IterableRule
import io.swagger.v3.oas.models.callbacks.Callback

class CallbacksRule internal constructor(group: RuleGroup = RuleGroup.unknown()) : IterableRule<Pair<String, Callback>>(group) {

    fun all(description: String = "", rule: CallbackRule.() -> CallbackRule) =
        addForEach { callback ->
            rule(CallbackRule(RuleGroup.named("callback '${callback.first}'", RuleGroup.Category.GROUP, description, group)))
                .validate(callback.second)
        }

    fun callback(description: String = "", named: String, rule: CallbackRule.() -> CallbackRule) =
        add { callbacks ->
            val callback = callbacks?.find { it.first == named }
            rule(CallbackRule(RuleGroup.named("callback '$named'", RuleGroup.Category.GROUP, description, group)))
                .validate(callback?.second)
        }
}
