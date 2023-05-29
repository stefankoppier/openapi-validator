package io.github.stefankoppier.openapi.validator.core.rules.openapi

import io.github.stefankoppier.openapi.validator.core.rules.RuleGroup
import io.github.stefankoppier.openapi.validator.core.rules.RuleGroupCategory
import io.github.stefankoppier.openapi.validator.core.rules.ValidationRule
import io.github.stefankoppier.openapi.validator.core.rules.primitives.AnyRule
import io.github.stefankoppier.openapi.validator.core.rules.primitives.StringRule
import io.github.stefankoppier.openapi.validator.core.rules.primitives.URIRule
import io.swagger.v3.oas.models.examples.Example

class ExampleRule(group: RuleGroup) : ValidationRule<Example>(group) {

    fun summary(description: String = "", rule: StringRule.() -> StringRule) =
        apply {
            add {
                rule(StringRule(RuleGroup.named("summary", description, RuleGroupCategory.FIELD, group)))
                    .validate(it?.summary)
            }
        }

    fun description(description: String = "", rule: StringRule.() -> StringRule) = 
        apply {
            add {
                rule(StringRule(RuleGroup.named("description", description, RuleGroupCategory.FIELD, group)))
                    .validate(it?.description)
            }
        }

    fun value(description: String = "", rule: AnyRule.() -> AnyRule) =
        apply {
            add {
                rule(AnyRule(RuleGroup.named("value", description, RuleGroupCategory.FIELD, group)))
                    .validate(it?.value)
            }
        }

    fun externalValue(description: String = "", rule: URIRule.() -> URIRule) =
        apply {
            add {
                rule(URIRule(RuleGroup.named("externalValue", description, RuleGroupCategory.FIELD, group)))
                    .validate(it?.externalValue)
            }
        }
}