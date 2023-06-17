package io.github.stefankoppier.openapi.validator.core.rules.openapi

import io.github.stefankoppier.openapi.validator.core.rules.RuleGroup
import io.github.stefankoppier.openapi.validator.core.rules.primitives.IterableValidationRule

class SecurityRequirementRule(group: RuleGroup) : IterableValidationRule<Pair<String, List<String>>>(group) {

}