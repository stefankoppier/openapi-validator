package io.github.stefankoppier.openapi.validator.core.rules.openapi

import io.github.stefankoppier.openapi.validator.core.rules.RuleGroup
import io.github.stefankoppier.openapi.validator.core.rules.ValidationRule
import io.github.stefankoppier.openapi.validator.core.rules.primitives.BooleanRule
import io.github.stefankoppier.openapi.validator.core.rules.primitives.StringRule
import io.swagger.v3.oas.models.media.XML

class XMLRule(group: RuleGroup) : ValidationRule<XML>(group) {

    fun name(description: String = "", rule: StringRule.() -> StringRule) =
        apply {
            add {
                rule(StringRule(RuleGroup.named("name", description, RuleGroup.Category.FIELD, group)))
                    .validate(it?.name)
            }
        }

    fun namespace(description: String = "", rule: StringRule.() -> StringRule) =
        apply {
            add {
                rule(StringRule(RuleGroup.named("namespace", description, RuleGroup.Category.FIELD, group)))
                    .validate(it?.namespace)
            }
        }

    fun prefix(description: String = "", rule: StringRule.() -> StringRule) =
        apply {
            add {
                rule(StringRule(RuleGroup.named("prefix", description, RuleGroup.Category.FIELD, group)))
                    .validate(it?.prefix)
            }
        }

    fun attribute(description: String = "", rule: BooleanRule.() -> BooleanRule) =
        apply {
            add {
                rule(BooleanRule(RuleGroup.named("attribute", description, RuleGroup.Category.FIELD, group)))
                    .validate(it?.attribute)
            }
        }

    fun wrapped(description: String = "", rule: BooleanRule.() -> BooleanRule) =
        apply {
            add {
                rule(BooleanRule(RuleGroup.named("wrapped", description, RuleGroup.Category.FIELD, group)))
                    .validate(it?.wrapped)
            }
        }
}
