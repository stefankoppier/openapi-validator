package io.github.stefankoppier.openapi.validator.core.rules.openapi.collections

import io.github.stefankoppier.openapi.validator.core.rules.RuleGroup
import io.github.stefankoppier.openapi.validator.core.rules.openapi.MediaTypeRule
import io.github.stefankoppier.openapi.validator.core.rules.primitives.IterableRule
import io.swagger.v3.oas.models.media.MediaType

class ContentRule internal constructor(group: RuleGroup = RuleGroup.unknown()) : IterableRule<Pair<String, MediaType>>(group) {

    fun all(description: String = "", rule: MediaTypeRule.() -> MediaTypeRule) =
        addForEach { mediaType ->
            rule(
                MediaTypeRule(
                    RuleGroup.named(
                        "mediaType '${mediaType.first}'",
                        RuleGroup.Category.GROUP,
                        description,
                        group,
                    ),
                ),
            )
                .validate(mediaType.second)
        }

    fun mediaType(description: String = "", named: String, rule: MediaTypeRule.() -> MediaTypeRule) =
        add { encodings ->
            val encoding = encodings?.find { it.first == named }
            rule(MediaTypeRule(RuleGroup.named("mediaType '$named'", RuleGroup.Category.GROUP, description, group)))
                .validate(encoding?.second)
        }
}
