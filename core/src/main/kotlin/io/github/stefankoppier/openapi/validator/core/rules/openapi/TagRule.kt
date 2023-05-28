package io.github.stefankoppier.openapi.validator.core.rules.openapi

import io.github.stefankoppier.openapi.validator.core.rules.RuleGroup
import io.github.stefankoppier.openapi.validator.core.rules.RuleGroupCategory
import io.github.stefankoppier.openapi.validator.core.rules.ValidationRule
import io.github.stefankoppier.openapi.validator.core.rules.primitives.StringRule
import io.swagger.v3.oas.models.tags.Tag

class TagRule(group : RuleGroup) : ValidationRule<Tag>(group) {

    fun TagRule.name(description: String = "", rule: StringRule.() -> StringRule): TagRule {
        add {
            rule(StringRule(RuleGroup.named("name", description, RuleGroupCategory.FIELD, group))).validate(it.name)
        }
        return this
    }
}