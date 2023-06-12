package io.github.stefankoppier.openapi.validator.core.rules.openapi.collections

import io.github.stefankoppier.openapi.validator.core.rules.RuleGroup
import io.github.stefankoppier.openapi.validator.core.rules.openapi.HeaderRule
import io.github.stefankoppier.openapi.validator.core.rules.primitives.IterableValidationRule
import io.swagger.v3.oas.models.headers.Header

class HeadersRule(group: RuleGroup) : IterableValidationRule<Pair<String, Header>>(group)  {

    fun header(description: String = "", named: String, rule: HeaderRule.() -> HeaderRule) =
        apply {
            add { examples ->
                val example = examples?.find { it.first == named }
                rule(HeaderRule(RuleGroup.named("header '$named'", description, RuleGroup.Category.OBJECT, group)))
                    .validate(example?.second)
            }
        }
}