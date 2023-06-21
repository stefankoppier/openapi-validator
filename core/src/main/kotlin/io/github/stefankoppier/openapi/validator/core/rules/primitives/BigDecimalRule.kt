package io.github.stefankoppier.openapi.validator.core.rules.primitives

import io.github.stefankoppier.openapi.validator.core.rules.RuleGroup
import io.github.stefankoppier.openapi.validator.core.rules.ValidationRule
import java.math.BigDecimal

class BigDecimalRule internal constructor(group: RuleGroup = RuleGroup.unknown()) : ValidationRule<BigDecimal>(group) {

    /**
     * Validate that the element is greater than or equal to [value].
     *
     * @return The rule on which this method has been invoked.
     */
    fun min(value: BigDecimal) =
        holds({ "Was supposed to be greater than or equal to '$value' but is '$it'" }) {
            it == null || it >= value
        }

    /**
     * Validate that the element is less than or equal to [value].
     *
     * @return The rule on which this method has been invoked.
     */
    fun max(value: BigDecimal) =
        holds({ "Was supposed to be less than or equal to '$value' but is '$it'" }) {
            it == null || it <= value
        }

    /**
     * Validate that the element is (inclusive) between [min] and [max].
     *
     * @return The rule on which this method has been invoked.
     */
    fun between(min: BigDecimal, max: BigDecimal) =
        holds({ "Was supposed to be between '$min' and '$max' but is '$it'" }) {
            it == null || it in min..max
        }
}
