package io.github.stefankoppier.openapi.validator.core.rules.primitives

import io.github.stefankoppier.openapi.validator.core.rules.RuleGroup
import io.github.stefankoppier.openapi.validator.core.rules.ValidationRule

class StringRule(group: RuleGroup = RuleGroup.unknown()) : ValidationRule<String>(group) {

    fun matches(regex: Regex) =
        holds( { "Was supposed to match '$regex' but is '$it'" } ) {
            it == null || regex.matches(it)
        }

    fun lowercase() =
        holds( { "Was supposed to be lowercase but is '$it'" } ) {
            it == null || it.all { c -> c.isLowerCase() }
        }

    fun uppercase() =
        holds( { "Was supposed to be uppercase but is '$it'" } ) {
            it == null || it.all { c -> c.isUpperCase() }
        }

    fun alpha() =
        holds( { "Was supposed to be alphabetic but is '$it'" } ) {
            it == null || it.all { c -> c.isLetter() }
        }

    fun numeric() =
        holds( { "Was supposed to be numeric but is '$it'" } ) {
            it == null || it.all { c -> c.isDigit() }
        }

    fun alphanumeric() =
        holds( { "Was supposed to be alphanumeric but is '$it'" } ) {
            it == null || it.all { c -> c.isLetterOrDigit() }
        }

    fun kebabcase() =
        holds( { "Was supposed to be kebab case but is '$it'" } ) {
            it == null || it.all { c -> c.isLetterOrDigit() || c == '-' }
        }

    fun snakecase() =
        holds( { "Was supposed to be snake case but is '$it'" } ) {
            it == null || it.all { c -> c.isLetterOrDigit() || c == '_' }
        }
}