package io.github.stefankoppier.openapi.validator.core.rules.openapi

import io.github.stefankoppier.openapi.validator.core.rules.RuleGroup
import io.github.stefankoppier.openapi.validator.core.rules.ValidationRule
import io.github.stefankoppier.openapi.validator.core.rules.primitives.StringRule
import io.swagger.v3.oas.models.media.Discriminator

class DiscriminatorRule(group: RuleGroup) : ValidationRule<Discriminator>(group) {

    fun propertyName(description: String = "", rule: StringRule.() -> StringRule) =
        apply {
            add {
                rule(StringRule(RuleGroup.named("propertyName", description, RuleGroup.Category.FIELD, group)))
                    .validate(it?.propertyName)
            }
        }

//    TODO
//    fun mapping(description: String = "", rule: StringRule.() -> StringRule) =
//        apply {
//            add {
//                rule(StringRule(RuleGroup.named("mapping", description, RuleGroup.Category.FIELD, group)))
//                    .validate(it?.mapping.toList())
//            }
//        }
}
