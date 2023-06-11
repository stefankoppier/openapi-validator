package io.github.stefankoppier.openapi.validator.core.rules.openapi.collections

import io.github.stefankoppier.openapi.validator.core.rules.RuleGroup
import io.github.stefankoppier.openapi.validator.core.rules.openapi.TagRule
import io.github.stefankoppier.openapi.validator.core.rules.primitives.IterableValidationRule
import io.swagger.v3.oas.models.tags.Tag

class TagsRule(group: RuleGroup) : IterableValidationRule<Tag>(group) {

    fun tag(description: String = "", named: String, rule: TagRule.() -> TagRule) =
        apply {
            add { tags ->
                val tag = tags?.find { it.name == named }
                rule(TagRule(RuleGroup.named("tag '$named'", description, RuleGroup.Category.OBJECT, group)))
                    .validate(tag)
            }
        }
}