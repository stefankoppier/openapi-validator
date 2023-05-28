package io.github.stefankoppier.openapi.validator.core.rules.openapi

import io.github.stefankoppier.openapi.validator.core.rules.RuleGroup
import io.github.stefankoppier.openapi.validator.core.rules.ValidationRule
import io.swagger.v3.oas.models.parameters.Parameter

class ParametersRule(group: RuleGroup) : ValidationRule<List<Parameter>>(group) {

}
