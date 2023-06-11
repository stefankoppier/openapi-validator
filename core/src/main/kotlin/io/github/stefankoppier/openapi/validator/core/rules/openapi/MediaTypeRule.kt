package io.github.stefankoppier.openapi.validator.core.rules.openapi

import io.github.stefankoppier.openapi.validator.core.rules.RuleGroup
import io.github.stefankoppier.openapi.validator.core.rules.RuleGroupCategory
import io.github.stefankoppier.openapi.validator.core.rules.ValidationRule
import io.github.stefankoppier.openapi.validator.core.rules.openapi.collections.EncodingRule
import io.github.stefankoppier.openapi.validator.core.rules.openapi.collections.ExamplesRule
import io.github.stefankoppier.openapi.validator.core.rules.primitives.AnyRule
import io.swagger.v3.oas.models.media.MediaType

class MediaTypeRule(group: RuleGroup) : ValidationRule<MediaType>(group) {

    fun schema(description: String = "", rule: SchemaRule.() -> SchemaRule) =
        apply {
            add {
                rule(SchemaRule(RuleGroup.named("schema", description, RuleGroupCategory.OBJECT, group)))
                    .validate(it?.schema)
            }
        }

    fun example(description: String = "", rule: AnyRule.() -> AnyRule) =
        apply {
            add {
                rule(AnyRule(RuleGroup.named("example", description, RuleGroupCategory.FIELD, group)))
                    .validate(it?.example)
            }
        }

    fun examples(description: String = "", rule: ExamplesRule.() -> ExamplesRule) =
        apply {
            add {
                rule(ExamplesRule(RuleGroup.named("examples", description, RuleGroupCategory.OBJECT, group)))
                    .validate(it?.examples?.toList())
            }
        }

    fun encoding(description: String = "", rule: EncodingRule.() -> EncodingRule) =
        apply {
            add {
                rule(EncodingRule(RuleGroup.named("encoding", description, RuleGroupCategory.OBJECT, group)))
                    .validate(it?.encoding?.toList())
            }
        }
}