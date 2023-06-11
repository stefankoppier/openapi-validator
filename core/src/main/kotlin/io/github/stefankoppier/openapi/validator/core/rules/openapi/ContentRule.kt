package io.github.stefankoppier.openapi.validator.core.rules.openapi

import io.github.stefankoppier.openapi.validator.core.rules.RuleGroup
import io.github.stefankoppier.openapi.validator.core.rules.primitives.IterableValidationRule
import io.swagger.v3.oas.models.media.MediaType

class ContentRule(group: RuleGroup) : IterableValidationRule<Pair<String, MediaType>>(group) {

    fun mediaType(description: String = "", named: String, rule: MediaTypeRule.() -> MediaTypeRule) =
        apply {
            add { mediaTypes ->
                val mediaType = mediaTypes?.find { it.first == named }
                rule(MediaTypeRule(RuleGroup.named("mediaType '$named'", description, RuleGroup.Category.OBJECT, group)))
                    .validate(mediaType?.second)
            }
        }
}

