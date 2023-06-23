package io.github.stefankoppier.openapi.validator.core.rules.openapi

import io.github.stefankoppier.openapi.validator.core.rules.RuleGroup
import io.github.stefankoppier.openapi.validator.core.rules.ValidationRule
import io.github.stefankoppier.openapi.validator.core.rules.openapi.collections.EncodingsRule
import io.github.stefankoppier.openapi.validator.core.rules.openapi.collections.ExamplesRule
import io.github.stefankoppier.openapi.validator.core.rules.primitives.AnyRule
import io.swagger.v3.oas.models.media.MediaType

class MediaTypeRule internal constructor(group: RuleGroup = RuleGroup.unknown()) : ValidationRule<MediaType>(group) {

    fun schema(description: String = "", rule: SchemaRule.() -> SchemaRule) =
        apply {
            add {
                rule(SchemaRule(RuleGroup.named("schema", RuleGroup.Category.OBJECT, description, group)))
                    .validate(it?.schema)
            }
        }

    fun example(description: String = "", rule: AnyRule.() -> AnyRule) =
        apply {
            add {
                rule(AnyRule(RuleGroup.named("example", RuleGroup.Category.FIELD, description, group)))
                    .validate(it?.example)
            }
        }

    fun examples(description: String = "", rule: ExamplesRule.() -> ExamplesRule) =
        apply {
            add {
                rule(ExamplesRule(RuleGroup.named("examples", RuleGroup.Category.OBJECT, description, group)))
                    .validate(it?.examples?.toList())
            }
        }

    fun encoding(description: String = "", rule: EncodingsRule.() -> EncodingsRule) =
        apply {
            add {
                rule(EncodingsRule(RuleGroup.named("encoding", RuleGroup.Category.OBJECT, description, group)))
                    .validate(it?.encoding?.toList())
            }
        }
}
