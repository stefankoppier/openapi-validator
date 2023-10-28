package io.github.stefankoppier.openapi.validator.core.rules.openapi.collections

import io.github.stefankoppier.openapi.validator.core.rules.RuleGroup
import io.github.stefankoppier.openapi.validator.core.rules.openapi.ParameterRule
import io.github.stefankoppier.openapi.validator.core.rules.primitives.IterableRule
import io.swagger.v3.oas.models.parameters.Parameter

class ParametersRule internal constructor(group: RuleGroup = RuleGroup.unknown()) : IterableRule<Parameter>(group) {

    fun all(description: String = "", rule: ParameterRule.() -> ParameterRule) =
        all { parameter ->
            rule(
                ParameterRule(
                    RuleGroup.named(
                        "parameter '${parameter.name}'",
                        RuleGroup.Category.GROUP,
                        description,
                        group,
                    ),
                ),
            )
                .validate(parameter)
        }

    fun parameter(description: String = "", named: String, rule: ParameterRule.() -> ParameterRule) =
        add { parameters ->
            val parameter = parameters?.find { it.name == named }
            rule(ParameterRule(RuleGroup.named("parameter '$named'", RuleGroup.Category.GROUP, description, group)))
                .validate(parameter)
        }
}
