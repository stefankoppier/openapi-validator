package io.github.stefankoppier.openapi.validator.core.rules.openapi.collections

import io.github.stefankoppier.openapi.validator.core.rules.RuleGroup
import io.github.stefankoppier.openapi.validator.core.rules.primitives.IterableValidationRule
import io.swagger.v3.oas.models.security.SecurityRequirement

// TODO: needs another layer as SecurityRequirement is a map itself
class SecurityRequirementsRule(group: RuleGroup) : IterableValidationRule<SecurityRequirement>(group)
