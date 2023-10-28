package io.github.stefankoppier.openapi.validator.core.rules.openapi

import io.github.stefankoppier.openapi.validator.core.rules.RuleGroup
import io.github.stefankoppier.openapi.validator.core.rules.ValidationRule
import io.github.stefankoppier.openapi.validator.core.rules.openapi.collections.ContentRule
import io.github.stefankoppier.openapi.validator.core.rules.openapi.collections.ExamplesRule
import io.github.stefankoppier.openapi.validator.core.rules.primitives.AnyRule
import io.github.stefankoppier.openapi.validator.core.rules.primitives.BooleanRule
import io.github.stefankoppier.openapi.validator.core.rules.primitives.EnumRule
import io.github.stefankoppier.openapi.validator.core.rules.primitives.StringRule
import io.swagger.v3.oas.models.headers.Header

class HeaderRule(group: RuleGroup = RuleGroup.unknown()) : ValidationRule<Header>(group) {

    fun description(description: String = "", rule: StringRule.() -> StringRule) =
        add {
            rule(StringRule(RuleGroup.named("description", RuleGroup.Category.FIELD, description, group)))
                .validate(it?.description)
        }

    fun required(description: String = "", rule: BooleanRule.() -> BooleanRule) =
        add {
            rule(BooleanRule(RuleGroup.named("required", RuleGroup.Category.FIELD, description, group)))
                .validate(it?.required)
        }

    fun deprecated(description: String = "", rule: BooleanRule.() -> BooleanRule) =
        add {
            rule(BooleanRule(RuleGroup.named("deprecated", RuleGroup.Category.FIELD, description, group)))
                .validate(it?.deprecated)
        }

    fun style(description: String = "", rule: EnumRule<Header.StyleEnum>.() -> EnumRule<Header.StyleEnum>) =
        add {
            rule(EnumRule(RuleGroup.named("style", RuleGroup.Category.FIELD, description)))
                .validate(it?.style)
        }

    fun explode(description: String = "", rule: BooleanRule.() -> BooleanRule) =
        add {
            rule(BooleanRule(RuleGroup.named("explode", RuleGroup.Category.FIELD, description, group)))
                .validate(it?.explode)
        }

    fun schema(description: String = "", rule: SchemaRule.() -> SchemaRule) =
        add {
            rule(SchemaRule(RuleGroup.named("schema", RuleGroup.Category.GROUP, description, group)))
                .validate(it?.schema)
        }

    fun example(description: String = "", rule: AnyRule.() -> AnyRule) =
        add {
            rule(AnyRule(RuleGroup.named("example", RuleGroup.Category.FIELD, description, group)))
                .validate(it?.example)
        }

    fun examples(description: String = "", rule: ExamplesRule.() -> ExamplesRule) =
        add {
            rule(ExamplesRule(RuleGroup.named("examples", RuleGroup.Category.GROUP, description, group)))
                .validate(it?.examples?.toList())
        }

    fun content(description: String = "", rule: ContentRule.() -> ContentRule) =
        add {
            rule(ContentRule(RuleGroup.named("content", RuleGroup.Category.GROUP, description, group)))
                .validate(it?.content?.toList())
        }
}
