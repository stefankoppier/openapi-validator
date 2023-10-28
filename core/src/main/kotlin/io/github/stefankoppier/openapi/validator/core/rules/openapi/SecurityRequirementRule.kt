package io.github.stefankoppier.openapi.validator.core.rules.openapi

import io.github.stefankoppier.openapi.validator.core.rules.RuleGroup
import io.github.stefankoppier.openapi.validator.core.rules.primitives.IterableRule

// TODO
class SecurityRequirementRule(group: RuleGroup) : IterableRule<Pair<String, List<String>>>(group)
