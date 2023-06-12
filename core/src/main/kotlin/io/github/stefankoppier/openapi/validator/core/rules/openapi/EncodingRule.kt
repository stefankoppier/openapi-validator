package io.github.stefankoppier.openapi.validator.core.rules.openapi

import io.github.stefankoppier.openapi.validator.core.rules.RuleGroup
import io.github.stefankoppier.openapi.validator.core.rules.ValidationRule
import io.github.stefankoppier.openapi.validator.core.rules.openapi.collections.HeadersRule
import io.github.stefankoppier.openapi.validator.core.rules.primitives.BooleanRule
import io.github.stefankoppier.openapi.validator.core.rules.primitives.EnumRule
import io.github.stefankoppier.openapi.validator.core.rules.primitives.StringRule
import io.swagger.v3.oas.models.media.Encoding

class EncodingRule(group: RuleGroup) : ValidationRule<Encoding>(group) {

    fun contentType(description: String = "", rule: StringRule.() -> StringRule) =
        apply {
            add {
                rule(StringRule(RuleGroup.named("contentType", description, RuleGroup.Category.FIELD, group)))
                    .validate(it?.contentType)

            }
        }

    fun headers(description: String = "", rule: HeadersRule.() -> HeadersRule) =
        apply {
            add {
                rule(HeadersRule(RuleGroup.named("headers", description, RuleGroup.Category.OBJECT, group)))
                    .validate(it?.headers?.toList())

            }
        }

    fun style(description: String = "", rule: EnumRule<Encoding.StyleEnum>.() -> EnumRule<Encoding.StyleEnum>) =
        apply {
            add {
                rule(EnumRule(RuleGroup.named("style", description, RuleGroup.Category.FIELD, group)))
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

    fun allowReserved(description: String = "", rule: BooleanRule.() -> BooleanRule) =
        apply {
            add {
                rule(BooleanRule(RuleGroup.named("allowReserved", description, RuleGroup.Category.FIELD, group)))
                    .validate(it?.allowReserved)

            }
        }
}