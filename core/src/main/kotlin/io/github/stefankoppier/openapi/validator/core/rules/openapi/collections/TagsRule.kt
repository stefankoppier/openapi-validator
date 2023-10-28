package io.github.stefankoppier.openapi.validator.core.rules.openapi.collections

import io.github.stefankoppier.openapi.validator.core.rules.RuleGroup
import io.github.stefankoppier.openapi.validator.core.rules.openapi.TagRule
import io.github.stefankoppier.openapi.validator.core.rules.primitives.IterableRule
import io.swagger.v3.oas.models.tags.Tag

class TagsRule internal constructor(group: RuleGroup = RuleGroup.unknown()) : IterableRule<Tag>(group) {

    fun all(description: String = "", rule: TagRule.() -> TagRule) =
        addForEach { tag ->
            rule(TagRule(RuleGroup.named("tag '${tag.name}'", RuleGroup.Category.GROUP, description, group)))
                .validate(tag)
        }

    fun tag(description: String = "", named: String, rule: TagRule.() -> TagRule) =
        add { tags ->
            val tag = tags?.find { it.name == named }
            rule(TagRule(RuleGroup.named("tag '$named'", RuleGroup.Category.GROUP, description, group)))
                .validate(tag)
        }
}
