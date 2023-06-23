package io.github.stefankoppier.openapi.validator.core.rules.openapi.collections

import io.github.stefankoppier.openapi.validator.core.rules.RuleGroup
import io.github.stefankoppier.openapi.validator.core.rules.openapi.ExampleRule
import io.github.stefankoppier.openapi.validator.core.rules.primitives.IterableValidationRule
import io.swagger.v3.oas.models.examples.Example

class ExamplesRule internal constructor(group: RuleGroup = RuleGroup.unknown()) : IterableValidationRule<Pair<String, Example>>(group) {

    fun all(description: String = "", rule: ExampleRule.() -> ExampleRule) =
        all { example ->
            rule(ExampleRule(RuleGroup.named("example '${example.first}'", RuleGroup.Category.OBJECT, description, group)))
                .validate(example.second)
        }

    fun example(description: String = "", named: String, rule: ExampleRule.() -> ExampleRule) =
        apply {
            add { examples ->
                val example = examples?.find { it.first == named }
                rule(ExampleRule(RuleGroup.named("example '$named'", RuleGroup.Category.OBJECT, description, group)))
                    .validate(example?.second)
            }
        }
}
