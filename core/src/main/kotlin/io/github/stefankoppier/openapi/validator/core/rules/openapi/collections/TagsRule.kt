package io.github.stefankoppier.openapi.validator.core.rules.openapi.collections

import io.github.stefankoppier.openapi.validator.core.rules.RuleGroup
import io.github.stefankoppier.openapi.validator.core.rules.openapi.TagRule
import io.github.stefankoppier.openapi.validator.core.rules.primitives.IterableValidationRule
import io.swagger.v3.oas.models.tags.Tag

class TagsRule internal constructor(group: RuleGroup = RuleGroup.unknown()) : IterableValidationRule<Tag>(group) {

    fun all(description: String = "", rule: TagRule.() -> TagRule) =
        all { tag ->
            rule(TagRule(RuleGroup.named("tag '${tag.name}'", description, io.github.stefankoppier.openapi.validator.core.rules.RuleGroup.Category.OBJECT, group)))
                .validate(tag)
        }

    fun tag(description: String = "", named: String, rule: TagRule.() -> TagRule) =
        apply {
            add { tags ->
                val tag = tags?.find { it.name == named }
                rule(TagRule(RuleGroup.named("tag '$named'", description, RuleGroup.Category.OBJECT, group)))
                    .validate(tag)
            }
        }
}
