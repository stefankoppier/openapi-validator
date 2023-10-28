package io.github.stefankoppier.openapi.validator.core.rules.openapi.collections

import io.github.stefankoppier.openapi.validator.core.rules.RuleGroup
import io.github.stefankoppier.openapi.validator.core.rules.openapi.LinkRule
import io.github.stefankoppier.openapi.validator.core.rules.primitives.IterableRule
import io.swagger.v3.oas.models.links.Link

class LinksRule internal constructor(group: RuleGroup = RuleGroup.unknown()) : IterableRule<Pair<String, Link>>(group) {

    fun all(description: String = "", rule: LinkRule.() -> LinkRule) =
        addForEach { link ->
            rule(LinkRule(RuleGroup.named("link '${link.first}'", RuleGroup.Category.GROUP, description, group)))
                .validate(link.second)
        }

    fun link(description: String = "", named: String, rule: LinkRule.() -> LinkRule) =
        add { links ->
            val link = links?.find { it.first == named }
            rule(LinkRule(RuleGroup.named("link '$named'", RuleGroup.Category.GROUP, description, group)))
                .validate(link?.second)
        }
}
