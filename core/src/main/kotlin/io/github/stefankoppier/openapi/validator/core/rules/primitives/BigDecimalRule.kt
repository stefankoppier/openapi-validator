package io.github.stefankoppier.openapi.validator.core.rules.primitives

import io.github.stefankoppier.openapi.validator.core.rules.RuleGroup
import io.github.stefankoppier.openapi.validator.core.rules.ValidationRule
import java.math.BigDecimal

class BigDecimalRule(group: RuleGroup) : ValidationRule<BigDecimal>(group) {

    fun min(value: BigDecimal) =
        holds( { "Was supposed to be greater than or equal to '$value' but is '$it'" } ) {
            it == null || it >= value
        }

    fun max(value: BigDecimal) =
        holds( { "Was supposed to be less than or equal to '$value' but is '$it'" } ) {
            it == null || it <= value
        }

    fun between(min: BigDecimal, max: BigDecimal) =
        holds( { "Was supposed to be between '$min' and '$max' but is '$it'" } ) {
            it == null || it in min..max
        }
}