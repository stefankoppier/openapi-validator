package io.github.stefankoppier.openapi.validator.core.rules.openapi

import io.github.stefankoppier.openapi.validator.core.rules.RuleGroup
import io.github.stefankoppier.openapi.validator.core.rules.ValidationRule
import io.swagger.v3.oas.models.responses.ApiResponses

class ResponsesRule(group: RuleGroup) : ValidationRule<ApiResponses>(group) {

}
