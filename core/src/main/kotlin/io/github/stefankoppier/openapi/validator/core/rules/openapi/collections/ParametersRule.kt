package io.github.stefankoppier.openapi.validator.core.rules.openapi.collections

import io.github.stefankoppier.openapi.validator.core.rules.RuleGroup
import io.github.stefankoppier.openapi.validator.core.rules.RuleGroupCategory
import io.github.stefankoppier.openapi.validator.core.rules.openapi.OperationRule
import io.github.stefankoppier.openapi.validator.core.rules.primitives.IterableValidationRule
import io.swagger.v3.oas.models.parameters.Parameter

class ParametersRule(group: RuleGroup) : IterableValidationRule<Parameter>(group)

fun OperationRule.parameters(description: String = "", rule: ParametersRule.() -> ParametersRule): ParametersRule {
    return rule(ParametersRule(RuleGroup.named("parameters", description, RuleGroupCategory.OBJECT, group)))
}