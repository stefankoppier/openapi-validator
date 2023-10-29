package io.github.stefankoppier.openapi.validator.core.rules.openapi.collections

import io.github.stefankoppier.openapi.validator.core.rules.RuleGroup
import io.github.stefankoppier.openapi.validator.core.rules.primitives.IterableRule
import io.github.stefankoppier.openapi.validator.core.rules.primitives.IterableStringRule

class SecurityRequirementRule(group: RuleGroup = RuleGroup.unknown()) : IterableRule<Pair<String, List<String>>>(group) {

    fun all(description: String = "", rule: IterableStringRule.() -> IterableStringRule) =
        addForEach { requirement ->
            rule(IterableStringRule(RuleGroup.named("securityRequirement '${requirement.first}'", RuleGroup.Category.GROUP, description, group)))
                .validate(requirement.second)
        }

    fun securityRequirement(description: String = "", named: String, rule: IterableStringRule.() -> IterableStringRule) =
        add { requirements ->
            val requirement = requirements?.find { it.first == named }
            rule(IterableStringRule(RuleGroup.named("securityRequirement '$named'", RuleGroup.Category.GROUP, description, group)))
                .validate(requirement?.second)
        }
}
