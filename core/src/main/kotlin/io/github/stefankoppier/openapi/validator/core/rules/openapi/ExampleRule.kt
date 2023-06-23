package io.github.stefankoppier.openapi.validator.core.rules.openapi

import io.github.stefankoppier.openapi.validator.core.rules.RuleGroup
import io.github.stefankoppier.openapi.validator.core.rules.ValidationRule
import io.github.stefankoppier.openapi.validator.core.rules.primitives.AnyRule
import io.github.stefankoppier.openapi.validator.core.rules.primitives.StringRule
import io.github.stefankoppier.openapi.validator.core.rules.primitives.URIRule
import io.swagger.v3.oas.models.examples.Example

class ExampleRule internal constructor(group: RuleGroup = RuleGroup.unknown()) : ValidationRule<Example>(group) {

    fun summary(description: String = "", rule: StringRule.() -> StringRule) =
        apply {
            add {
                rule(StringRule(RuleGroup.named("summary", RuleGroup.Category.FIELD, description, group)))
                    .validate(it?.summary)
            }
        }

    fun description(description: String = "", rule: StringRule.() -> StringRule) =
        apply {
            add {
                rule(StringRule(RuleGroup.named("description", RuleGroup.Category.FIELD, description, group)))
                    .validate(it?.description)
            }
        }

    fun value(description: String = "", rule: AnyRule.() -> AnyRule) =
        apply {
            add {
                rule(AnyRule(RuleGroup.named("value", RuleGroup.Category.FIELD, description, group)))
                    .validate(it?.value)
            }
        }

    fun externalValue(description: String = "", rule: URIRule.() -> URIRule) =
        apply {
            add {
                rule(URIRule(RuleGroup.named("externalValue", RuleGroup.Category.FIELD, description, group)))
                    .validate(it?.externalValue)
            }
        }
}
