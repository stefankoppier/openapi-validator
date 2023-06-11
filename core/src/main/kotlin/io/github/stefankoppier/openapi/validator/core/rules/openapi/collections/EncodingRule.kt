package io.github.stefankoppier.openapi.validator.core.rules.openapi.collections

import io.github.stefankoppier.openapi.validator.core.rules.RuleGroup
import io.github.stefankoppier.openapi.validator.core.rules.primitives.IterableValidationRule
import io.swagger.v3.oas.models.media.Encoding

class EncodingRule(group: RuleGroup) : IterableValidationRule<Pair<String, Encoding>>(group) {
}