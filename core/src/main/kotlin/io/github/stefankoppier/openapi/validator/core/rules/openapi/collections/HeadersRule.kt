package io.github.stefankoppier.openapi.validator.core.rules.openapi.collections

import io.github.stefankoppier.openapi.validator.core.rules.RuleGroup
import io.github.stefankoppier.openapi.validator.core.rules.openapi.HeaderRule
import io.github.stefankoppier.openapi.validator.core.rules.primitives.IterableRule
import io.swagger.v3.oas.models.headers.Header

class HeadersRule internal constructor(group: RuleGroup = RuleGroup.unknown()) : IterableRule<Pair<String, Header>>(group) {

    fun all(description: String = "", rule: HeaderRule.() -> HeaderRule) =
        addForAll { header ->
            rule(HeaderRule(RuleGroup.named("header '${header.first}'", RuleGroup.Category.GROUP, description, group)))
                .validate(header.second)
        }

    fun header(description: String = "", named: String, rule: HeaderRule.() -> HeaderRule) =
        add { examples ->
            val example = examples?.find { it.first == named }
            rule(HeaderRule(RuleGroup.named("header '$named'", RuleGroup.Category.GROUP, description, group)))
                .validate(example?.second)
        }
}
