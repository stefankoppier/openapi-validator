package io.github.stefankoppier.openapi.validator.rules.openapi

import io.github.stefankoppier.openapi.validator.rules.RuleGroup
import io.github.stefankoppier.openapi.validator.rules.ValidationRule
import io.github.stefankoppier.openapi.validator.rules.primitives.OptionalStringRule
import io.swagger.v3.oas.models.PathItem

class PathItemRule(group: RuleGroup) : ValidationRule<PathItem>(group) {

    fun summary(rule: OptionalStringRule.() -> OptionalStringRule): PathItemRule {
        add {
            rule(OptionalStringRule(RuleGroup.named("summary", group))).validate(it.summary)
        }
        return this
    }

    fun description(rule: OptionalStringRule.() -> OptionalStringRule): PathItemRule {
        add {
            rule(OptionalStringRule(RuleGroup.named("description", group))).validate(it.description)
        }
        return this
    }
}

fun PathsRule.path(rule: PathItemRule.() -> PathItemRule): PathItemRule {
    return rule(PathItemRule(RuleGroup.named("path", group)))
}