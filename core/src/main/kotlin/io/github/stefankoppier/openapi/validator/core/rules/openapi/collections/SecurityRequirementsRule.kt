package io.github.stefankoppier.openapi.validator.core.rules.openapi.collections

import io.github.stefankoppier.openapi.validator.core.rules.RuleGroup
import io.github.stefankoppier.openapi.validator.core.rules.primitives.IterableRule
import io.swagger.v3.oas.models.security.SecurityRequirement

class SecurityRequirementsRule internal constructor(group: RuleGroup = RuleGroup.unknown()) : IterableRule<SecurityRequirement>(group) {

    // TODO
}
