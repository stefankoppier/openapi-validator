package io.github.stefankoppier.openapi.validator.core.rules.openapi.collections

import io.github.stefankoppier.openapi.validator.core.rules.RuleGroup
import io.github.stefankoppier.openapi.validator.core.rules.openapi.EncodingRule
import io.github.stefankoppier.openapi.validator.core.rules.primitives.IterableRule
import io.swagger.v3.oas.models.media.Encoding

class EncodingsRule internal constructor(group: RuleGroup = RuleGroup.unknown()) : IterableRule<Pair<String, Encoding>>(group) {

    fun all(description: String = "", rule: EncodingRule.() -> EncodingRule) =
        all { encoding ->
            rule(
                EncodingRule(
                    RuleGroup.named(
                        "encoding '${encoding.first}'",
                        RuleGroup.Category.GROUP,
                        description,
                        group,
                    ),
                ),
            )
                .validate(encoding.second)
        }

    fun encoding(description: String = "", named: String, rule: EncodingRule.() -> EncodingRule) =
        apply {
            add { encodings ->
                val encoding = encodings?.find { it.first == named }
                rule(EncodingRule(RuleGroup.named("encoding '$named'", RuleGroup.Category.GROUP, description, group)))
                    .validate(encoding?.second)
            }
        }
}
