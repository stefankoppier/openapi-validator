package io.github.stefankoppier.openapi.validator.core.rules.openapi

import io.github.stefankoppier.openapi.validator.core.rules.RuleGroup
import io.github.stefankoppier.openapi.validator.core.rules.RuleGroupCategory
import io.github.stefankoppier.openapi.validator.core.rules.ValidationRule
import io.swagger.v3.oas.models.Paths

class PathsRule(group: RuleGroup) : ValidationRule<Paths>(group) {

}

fun OpenAPIRule.paths(rule: PathsRule.() -> PathsRule): PathsRule {
    return rule(PathsRule(RuleGroup.named("paths", RuleGroupCategory.OBJECT, group)))
}