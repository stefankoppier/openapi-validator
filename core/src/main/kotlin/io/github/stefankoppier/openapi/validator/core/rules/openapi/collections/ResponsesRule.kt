package io.github.stefankoppier.openapi.validator.core.rules.openapi.collections

import io.github.stefankoppier.openapi.validator.core.rules.RuleGroup
import io.github.stefankoppier.openapi.validator.core.rules.primitives.IterableValidationRule
import io.swagger.v3.oas.models.responses.ApiResponse

class ResponsesRule(group: RuleGroup) : IterableValidationRule<Pair<String, ApiResponse>>(group) {

}
