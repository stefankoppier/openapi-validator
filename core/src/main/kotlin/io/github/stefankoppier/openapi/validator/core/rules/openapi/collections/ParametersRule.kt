package io.github.stefankoppier.openapi.validator.core.rules.openapi.collections

import io.github.stefankoppier.openapi.validator.core.rules.RuleGroup
import io.github.stefankoppier.openapi.validator.core.rules.RuleGroupCategory
import io.github.stefankoppier.openapi.validator.core.rules.openapi.ParameterRule
import io.github.stefankoppier.openapi.validator.core.rules.primitives.IterableValidationRule
import io.swagger.v3.oas.models.parameters.Parameter

class ParametersRule(group: RuleGroup) : IterableValidationRule<Parameter>(group) {

    fun parameter(description: String = "", named: String, rule: ParameterRule.() -> ParameterRule) =
        apply {
            add { parameters ->
                val parameter = parameters?.find { it.name == named }
                rule(ParameterRule(RuleGroup.named("parameter '$named'", description, RuleGroupCategory.OBJECT, group)))
                    .validate(parameter)
            }
        }
}
