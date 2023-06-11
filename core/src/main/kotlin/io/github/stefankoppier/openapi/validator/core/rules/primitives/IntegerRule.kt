package io.github.stefankoppier.openapi.validator.core.rules.primitives

import io.github.stefankoppier.openapi.validator.core.rules.RuleGroup
import io.github.stefankoppier.openapi.validator.core.rules.ValidationRule

class IntegerRule(group: RuleGroup) : ValidationRule<Int>(group) {

    fun min(value: Int) =
        holds( { "Was supposed to be greater than or equal to '$value' but is '$it'" } ) {
            it == null || it >= value
        }

    fun max(value: Int) =
        holds( { "Was supposed to be less than or equal to '$value' but is '$it'" } ) {
            it == null || it <= value
        }

    fun between(min: Int, max: Int) =
        holds( { "Was supposed to be between '$min' and '$max' but is '$it'" } ) {
            it == null || it in min..max
        }
}