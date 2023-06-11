package io.github.stefankoppier.openapi.validator.core.rules.openapi.collections

import io.github.stefankoppier.openapi.validator.core.rules.RuleGroup
import io.github.stefankoppier.openapi.validator.core.rules.RuleGroupCategory
import io.github.stefankoppier.openapi.validator.core.rules.openapi.OpenAPIRule
import io.github.stefankoppier.openapi.validator.core.rules.openapi.PathRule
import io.github.stefankoppier.openapi.validator.core.rules.primitives.IterableValidationRule
import io.swagger.v3.oas.models.PathItem

class PathsRule(group: RuleGroup) : IterableValidationRule<Pair<String, PathItem>>(group) {

    fun path(description: String = "", named: String, rule: PathRule.() -> PathRule)  =
        apply {
            add { paths ->
                val path = paths?.find { it.first == named }
                rule(PathRule(RuleGroup.named("path '$named'", description, RuleGroupCategory.OBJECT, group)))
                    .validate(path?.second)
            }
        }
}