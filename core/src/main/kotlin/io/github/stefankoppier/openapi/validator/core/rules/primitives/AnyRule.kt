package io.github.stefankoppier.openapi.validator.core.rules.primitives

import io.github.stefankoppier.openapi.validator.core.rules.RuleGroup
import io.github.stefankoppier.openapi.validator.core.rules.ValidationFailure
import io.github.stefankoppier.openapi.validator.core.rules.ValidationResult
import io.github.stefankoppier.openapi.validator.core.rules.ValidationRule
import kotlin.reflect.KClass

class AnyRule(group: RuleGroup) : ValidationRule<Any>(group) {

    fun <T : Any> instanceof(clazz: KClass<T>): AnyRule {
        add {
            val message = "Was supposed to be of type '${clazz.simpleName} but is of type '${it?.javaClass?.kotlin?.simpleName}"
            ValidationResult.condition(ValidationFailure(group, message)) {
                it == null || clazz.isInstance(it)
            }
        }
        return this
    }
}