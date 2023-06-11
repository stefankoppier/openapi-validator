package io.github.stefankoppier.openapi.validator.core.rules.openapi.collections

import io.github.stefankoppier.openapi.validator.core.ValidationFailure
import io.github.stefankoppier.openapi.validator.core.ValidationResult
import io.github.stefankoppier.openapi.validator.core.rules.RuleGroup
import io.github.stefankoppier.openapi.validator.core.rules.RuleGroupCategory
import io.github.stefankoppier.openapi.validator.core.rules.openapi.TagRule
import io.github.stefankoppier.openapi.validator.core.rules.primitives.IterableValidationRule
import io.swagger.v3.oas.models.tags.Tag

class TagsRule(group: RuleGroup) : IterableValidationRule<Tag>(group) {

    fun contains(description: String = "", rule: TagRule.() -> TagRule) =
        apply {
            add { tags ->
                require(tags != null)

                val innerGroup = RuleGroup.named("tag", description, RuleGroupCategory.OBJECT, group)

                val result = tags.map { rule(TagRule(innerGroup)).validate(it) }

                if (result.all { it.isFailure }) { // TODO: works but needs a usefull message
                    ValidationResult.failure(ValidationFailure(innerGroup, "Tags does not contain an matching element."))
                } else {
                    ValidationResult.success()
                }
            }
        }
}