package io.github.stefankoppier.openapi.validator.core.rules.primitives

import io.github.stefankoppier.openapi.validator.core.rules.RuleGroup
import io.github.stefankoppier.openapi.validator.core.rules.ValidationRule
import kotlin.reflect.KClass

class AnyRule(group: RuleGroup) : ValidationRule<Any>(group) {

    fun <T : Any> instanceof(clazz: KClass<T>) =
        holds({ "Was supposed to be of type '${clazz.simpleName} but is of type '${it?.javaClass?.kotlin?.simpleName}"}) {
            it == null || clazz.isInstance(it)
        }
}