package io.github.stefankoppier.openapi.validator.core.rules.openapi

import io.github.stefankoppier.openapi.validator.core.rules.RuleGroup
import io.github.stefankoppier.openapi.validator.core.rules.ValidationRule
import io.github.stefankoppier.openapi.validator.core.rules.openapi.collections.ExamplesRule
import io.github.stefankoppier.openapi.validator.core.rules.primitives.AnyRule
import io.github.stefankoppier.openapi.validator.core.rules.primitives.BooleanRule
import io.github.stefankoppier.openapi.validator.core.rules.primitives.EnumRule
import io.github.stefankoppier.openapi.validator.core.rules.primitives.StringRule
import io.swagger.v3.oas.models.headers.Header

class HeaderRule(group: RuleGroup) : ValidationRule<Header>(group) {

    fun description(description: String = "", rule: StringRule.() -> StringRule) =
        apply {
            add {
                rule(StringRule(RuleGroup.named("description", description, RuleGroup.Category.FIELD, group)))
                    .validate(it?.description)
            }
        }

    fun required(description: String = "", rule: BooleanRule.() -> BooleanRule) =
        apply {
            add {
                rule(BooleanRule(RuleGroup.named("required", description, RuleGroup.Category.FIELD, group)))
                    .validate(it?.required)
            }
        }

    fun deprecated(description: String = "", rule: BooleanRule.() -> BooleanRule) =
        apply {
            add {
                rule(BooleanRule(RuleGroup.named("deprecated", description, RuleGroup.Category.FIELD, group)))
                    .validate(it?.deprecated)
            }
        }

    fun style(description: String = "", rule: EnumRule<Header.StyleEnum>.() -> EnumRule<Header.StyleEnum>) =
        apply {
            add {
                rule(EnumRule(RuleGroup.named("style", description, RuleGroup.Category.FIELD)))
                    .validate(it?.style)
            }
        }

    fun explode(description: String = "", rule: BooleanRule.() -> BooleanRule) =
        apply {
            add {
                rule(BooleanRule(RuleGroup.named("explode", description, RuleGroup.Category.FIELD, group)))
                    .validate(it?.explode)
            }
        }

    fun schema(description: String = "", rule: SchemaRule.() -> SchemaRule) =
        apply {
            add {
                rule(SchemaRule(RuleGroup.named("schema", description, RuleGroup.Category.OBJECT, group)))
                    .validate(it?.schema)
            }
        }

    fun example(description: String = "", rule: AnyRule.() -> AnyRule) =
        apply {
            add {
                rule(AnyRule(RuleGroup.named("example", description, RuleGroup.Category.FIELD, group)))
                    .validate(it?.example)
            }
        }

    fun examples(description: String = "", rule: ExamplesRule.() -> ExamplesRule) =
        apply {
            add {
                rule(ExamplesRule(RuleGroup.named("examples", description, RuleGroup.Category.FIELD, group)))
                    .validate(it?.examples?.toList())
            }
        }

    fun content(description: String = "", rule: ContentRule.() -> ContentRule) =
        apply {
            add {
                rule(ContentRule(RuleGroup.named("content", description, RuleGroup.Category.OBJECT, group)))
                    .validate(it?.content?.toList())
            }
        }
}
