package io.github.stefankoppier.openapi.validator.core.rules.openapi

import io.github.stefankoppier.openapi.validator.core.rules.RuleGroup
import io.github.stefankoppier.openapi.validator.core.rules.ValidationRule
import io.github.stefankoppier.openapi.validator.core.rules.primitives.BooleanRule
import io.github.stefankoppier.openapi.validator.core.rules.primitives.StringRule
import io.swagger.v3.oas.models.media.XML

class XMLRule(group: RuleGroup = RuleGroup.unknown()) : ValidationRule<XML>(group) {

    fun name(description: String = "", rule: StringRule.() -> StringRule) =
        add {
            rule(StringRule(RuleGroup.named("name", RuleGroup.Category.FIELD, description, group)))
                .validate(it?.name)
        }

    fun namespace(description: String = "", rule: StringRule.() -> StringRule) =
        add {
            rule(StringRule(RuleGroup.named("namespace", RuleGroup.Category.FIELD, description, group)))
                .validate(it?.namespace)
        }

    fun prefix(description: String = "", rule: StringRule.() -> StringRule) =
        add {
            rule(StringRule(RuleGroup.named("prefix", RuleGroup.Category.FIELD, description, group)))
                .validate(it?.prefix)
        }

    fun attribute(description: String = "", rule: BooleanRule.() -> BooleanRule) =
        add {
            rule(BooleanRule(RuleGroup.named("attribute", RuleGroup.Category.FIELD, description, group)))
                .validate(it?.attribute)
        }

    fun wrapped(description: String = "", rule: BooleanRule.() -> BooleanRule) =
        add {
            rule(BooleanRule(RuleGroup.named("wrapped", RuleGroup.Category.FIELD, description, group)))
                .validate(it?.wrapped)
        }
}
