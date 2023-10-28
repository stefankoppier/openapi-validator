package io.github.stefankoppier.openapi.validator.core.rules.openapi

import io.github.stefankoppier.openapi.validator.core.rules.RuleGroup
import io.github.stefankoppier.openapi.validator.core.rules.ValidationRule
import io.github.stefankoppier.openapi.validator.core.rules.openapi.collections.HeadersRule
import io.github.stefankoppier.openapi.validator.core.rules.primitives.BooleanRule
import io.github.stefankoppier.openapi.validator.core.rules.primitives.EnumRule
import io.github.stefankoppier.openapi.validator.core.rules.primitives.StringRule
import io.swagger.v3.oas.models.media.Encoding

class EncodingRule(group: RuleGroup = RuleGroup.unknown()) : ValidationRule<Encoding>(group) {

    fun contentType(description: String = "", rule: StringRule.() -> StringRule) =
        add {
            rule(StringRule(RuleGroup.named("contentType", RuleGroup.Category.FIELD, description, group)))
                .validate(it?.contentType)
        }

    fun headers(description: String = "", rule: HeadersRule.() -> HeadersRule) =
        add {
            rule(HeadersRule(RuleGroup.named("headers", RuleGroup.Category.GROUP, description, group)))
                .validate(it?.headers?.toList())
        }

    fun style(description: String = "", rule: EnumRule<Encoding.StyleEnum>.() -> EnumRule<Encoding.StyleEnum>) =
        add {
            rule(EnumRule(RuleGroup.named("style", RuleGroup.Category.FIELD, description, group)))
                .validate(it?.style)
        }

    fun explode(description: String = "", rule: BooleanRule.() -> BooleanRule) =
        add {
            rule(BooleanRule(RuleGroup.named("explode", RuleGroup.Category.FIELD, description, group)))
                .validate(it?.explode)
        }

    fun allowReserved(description: String = "", rule: BooleanRule.() -> BooleanRule) =
        add {
            rule(BooleanRule(RuleGroup.named("allowReserved", RuleGroup.Category.FIELD, description, group)))
                .validate(it?.allowReserved)
        }
}
