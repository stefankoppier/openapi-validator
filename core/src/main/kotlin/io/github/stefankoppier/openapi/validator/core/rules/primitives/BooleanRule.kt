package io.github.stefankoppier.openapi.validator.core.rules.primitives

import io.github.stefankoppier.openapi.validator.core.rules.RuleGroup
import io.github.stefankoppier.openapi.validator.core.rules.ValidationRule

class BooleanRule internal constructor(group: RuleGroup = RuleGroup.unknown()) : ValidationRule<Boolean>(group) {

    fun isTrue() = exactly(true)

    fun isFalse() = exactly(false)
}
