package io.github.stefankoppier.openapi.validator.rules.openapi

import io.github.stefankoppier.openapi.validator.rules.RuleGroup
import io.github.stefankoppier.openapi.validator.rules.ValidationRule
import io.swagger.v3.oas.models.Operation

class OperationRule(group: RuleGroup) : ValidationRule<Operation>(group) {

}
