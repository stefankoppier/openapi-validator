package io.github.stefankoppier.openapi.validator.core.rules.openapi.collections

import io.github.stefankoppier.openapi.validator.core.rules.RuleGroup
import io.github.stefankoppier.openapi.validator.core.rules.openapi.ExampleRule
import io.github.stefankoppier.openapi.validator.core.rules.primitives.IterableRule
import io.swagger.v3.oas.models.examples.Example

class ExamplesRule internal constructor(group: RuleGroup = RuleGroup.unknown()) : IterableRule<Pair<String, Example>>(group) {

    fun all(description: String = "", rule: ExampleRule.() -> ExampleRule) =
        addForEach { example ->
            rule(ExampleRule(RuleGroup.named("example '${example.first}'", RuleGroup.Category.GROUP, description, group)))
                .validate(example.second)
        }

    fun example(description: String = "", named: String, rule: ExampleRule.() -> ExampleRule) =
        add { examples ->
            val example = examples?.find { it.first == named }
            rule(ExampleRule(RuleGroup.named("example '$named'", RuleGroup.Category.GROUP, description, group)))
                .validate(example?.second)
        }
}
