package io.github.stefankoppier.openapi.validator.core.rules.primitives

import io.github.stefankoppier.openapi.validator.core.ValidationFailure
import io.github.stefankoppier.openapi.validator.core.ValidationResult
import io.github.stefankoppier.openapi.validator.core.rules.RuleGroup
import io.github.stefankoppier.openapi.validator.core.rules.ValidationRule
import java.net.URI

class URIRule internal constructor(group: RuleGroup = RuleGroup.unknown()) : ValidationRule<URI>(group) {

    /**
     * Execute the given rule(s) on the [fixture].
     *
     * @param fixture The object to validate the rule(s) against.
     *
     * @return The [ValidationResult] possibly containing failures.
     */
    fun validate(fixture: String?): ValidationResult {
        return if (fixture != null) {
            runCatching { URI(fixture) }
                .map { super.validate(it) }
                .getOrElse {
                    val message = "Was required to be in the form of a URI but was '$fixture'"
                    ValidationResult.failure(ValidationFailure(group, message))
                }
        } else {
            super.validate(null)
        }
    }

    /**
     * Validate that the element should be equal to [value] *given that it is set*.
     *
     * @return The rule on which this method has been invoked.
     */
    fun <R : ValidationRule<URI>> R.exactly(value: String) =
        holds({ "Was supposed to be '$value' but is '$it'" }) {
            it == run { URI(value) }
        }
}
