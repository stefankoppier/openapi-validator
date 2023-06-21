package io.github.stefankoppier.openapi.validator.core.rules.openapi.collections

import io.github.stefankoppier.openapi.validator.core.rules.RuleGroup
import io.github.stefankoppier.openapi.validator.core.rules.openapi.MediaTypeRule
import io.github.stefankoppier.openapi.validator.core.rules.primitives.IterableValidationRule
import io.swagger.v3.oas.models.media.MediaType

class ContentRule internal constructor(group: RuleGroup = RuleGroup.unknown()) : IterableValidationRule<Pair<String, MediaType>>(group) {

    fun all(description: String = "", rule: MediaTypeRule.() -> MediaTypeRule) =
        all { mediaType ->
            rule(MediaTypeRule(RuleGroup.named("mediaType '${mediaType.first}'", description, RuleGroup.Category.OBJECT, group)))
                .validate(mediaType.second)
        }

    fun mediaType(description: String = "", named: String, rule: MediaTypeRule.() -> MediaTypeRule) =
        apply {
            add { encodings ->
                val encoding = encodings?.find { it.first == named }
                rule(MediaTypeRule(RuleGroup.named("mediaType '$named'", description, RuleGroup.Category.OBJECT, group)))
                    .validate(encoding?.second)
            }
        }
}
