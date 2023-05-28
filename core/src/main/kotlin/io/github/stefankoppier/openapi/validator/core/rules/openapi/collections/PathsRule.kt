package io.github.stefankoppier.openapi.validator.core.rules.openapi.collections

import io.github.stefankoppier.openapi.validator.core.rules.RuleGroup
import io.github.stefankoppier.openapi.validator.core.rules.RuleGroupCategory
import io.github.stefankoppier.openapi.validator.core.rules.openapi.OpenAPIRule
import io.github.stefankoppier.openapi.validator.core.rules.primitives.IterableValidationRule
import io.swagger.v3.oas.models.PathItem

class PathsRule(group: RuleGroup) : IterableValidationRule<Pair<String, PathItem>>(group) {

}

fun OpenAPIRule.paths(description: String = "", rule: PathsRule.() -> PathsRule): PathsRule {
    return rule(PathsRule(RuleGroup.named("paths", description, RuleGroupCategory.OBJECT, group)))
}