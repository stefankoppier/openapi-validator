package io.github.stefankoppier.openapi.validator.core.rules.primitives

import io.github.stefankoppier.openapi.validator.core.rules.RuleGroup
import io.github.stefankoppier.openapi.validator.core.rules.ValidationRule

class EnumRule <T : Enum<*>> internal constructor(group: RuleGroup = RuleGroup.unknown()): ValidationRule<T>(group)