package io.github.stefankoppier.openapi.validator.rules



class ValidationResult internal constructor(val failures: MutableList<ValidationFailure>) {

    val isSuccess: Boolean
        get() = failures.isEmpty()

    val isFailure: Boolean
        get() = failures.isNotEmpty()

    infix fun merge(other: ValidationResult): ValidationResult {
        return apply { failures.addAll(other.failures) }
    }

    companion object {
        fun success(): ValidationResult {
            return ValidationResult(mutableListOf())
        }

        fun failure(failure: ValidationFailure): ValidationResult {
            return ValidationResult(mutableListOf(failure))
        }

        fun condition(failure: ValidationFailure, condition: () -> Boolean) =
            if (condition()) success() else failure(failure)
    }
}

data class ValidationFailure(val group: RuleGroup, val message: String)