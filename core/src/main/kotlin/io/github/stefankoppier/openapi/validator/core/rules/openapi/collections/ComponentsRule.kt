package io.github.stefankoppier.openapi.validator.core.rules.openapi.collections

import io.github.stefankoppier.openapi.validator.core.rules.RuleGroup
import io.github.stefankoppier.openapi.validator.core.rules.ValidationRule
import io.github.stefankoppier.openapi.validator.core.rules.openapi.MediaTypeRule
import io.swagger.v3.oas.models.Components

// TODO
//class ComponentsRule internal constructor(group: RuleGroup = RuleGroup.unknown()) : ValidationRule<Components>(group) {
//
//    fun component(description: String = "", named: String, rule: MediaTypeRule.() -> MediaTypeRule) =
//        apply {
//            add { encodings ->
//                val encoding = encodings?.find { it.first == named }
//                rule(MediaTypeRule(RuleGroup.named("mediaType '$named'", description, RuleGroup.Category.OBJECT, group)))
//                    .validate(encoding?.second)
//            }
//        }
//}