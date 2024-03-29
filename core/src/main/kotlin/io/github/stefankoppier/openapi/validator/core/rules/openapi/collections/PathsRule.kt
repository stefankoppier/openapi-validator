package io.github.stefankoppier.openapi.validator.core.rules.openapi.collections

import io.github.stefankoppier.openapi.validator.core.rules.RuleGroup
import io.github.stefankoppier.openapi.validator.core.rules.openapi.PathRule
import io.github.stefankoppier.openapi.validator.core.rules.primitives.IterableRule
import io.swagger.v3.oas.models.PathItem

class PathsRule internal constructor(group: RuleGroup = RuleGroup.unknown()) : IterableRule<Pair<String, PathItem>>(group) {

    fun all(description: String = "", rule: PathRule.() -> PathRule) =
        addForEach { path ->
            rule(PathRule(RuleGroup.named("path '${path.first}'", RuleGroup.Category.GROUP, description, group)))
                .validate(path)
        }

    fun path(description: String = "", named: String, rule: PathRule.() -> PathRule) =
        add { paths ->
            val path = paths?.find { it.first == named }
            rule(PathRule(RuleGroup.named("path '$named'", RuleGroup.Category.GROUP, description, group)))
                .validate(path)
        }
}
